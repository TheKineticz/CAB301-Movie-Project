package com.kineticz.videostoremanager.movies;

/**
 * Binary search tree object for storing and managing added movies
 */
public class MovieCollection {

    Node root;

    /**
     * Create an empty movie collection
     */
    public MovieCollection() {
        root = null;
    }

    /**
     * Create a new movie collection and populate with an array of movies
     *
     * @param movies An array of movies
     */
    public MovieCollection(Movie[] movies) {
        root = null;

        for (Movie movie : movies) {
            addMovie(movie);
        }
    }

    /**
     * Internal function for recursively adding nodes
     *
     * @param current The current node in the recursion
     * @param movie The movie object to be added to the tree
     * @return The current node in the recursion
     */
    private Node addMovieRecursively(Node current, Movie movie) {
        if (current == null) {
            return new Node(movie);
        }

        int lexicalComparison = movie.title.compareTo(current.movie.title);

        if (lexicalComparison < 0) {
            current.left = addMovieRecursively(current.left, movie);

        } else if (lexicalComparison > 0) {
            current.right = addMovieRecursively(current.right, movie);

        } else {
            return current;
        }

        return current;
    }

    /**
     * External function for adding a new movie
     *
     * @param movie The movie to be added
     */
    public void addMovie(Movie movie) {
        root = addMovieRecursively(root, movie);
    }

    /**
     * Internal function for searching recursively through nodes to find a movie
     *
     * @param current The current node in the recursion
     * @param title The title of the movie to find
     * @return The current node in the recursion
     */
    private Node findMovieRecursively(Node current, String title) {
        if (current == null) {
            return null;
        }

        if (current.movie.title.equals(title)) {
            return current;
        }

        if (title.compareTo(current.movie.title) < 0) {
            return findMovieRecursively(current.left, title);

        } else {
            return findMovieRecursively(current.right, title);
        }
    }

    /**
     * Finds a movie object, given the title of that movie
     *
     * @param title The title of the movie to search for
     * @return The movie object, or null if no match is found
     */
    public Movie findMovie(String title) {
        Node movieNode = findMovieRecursively(root, title);

        if (movieNode != null) {
            return movieNode.movie;
        } else {
            return null;
        }
    }

    /**
     * Shorthand function to check if the collection contains a given movie
     *
     * @param title The title of the movie to search for
     * @return Returns true if the movie is found, false otherwise
     */
    public boolean containsMovie(String title) {
        return findMovie(title) != null;
    }

    /**
     * Traverse a tree from a given root node to the minimum value from that root node, and return it's movie
     *
     * @param root The root node
     * @return The movie at the minimal node
     */
    private Movie getSubtreeMinimum(Node root) {
        if (root.left == null) {
            return root.movie;
        } else {
            return getSubtreeMinimum(root.left);
        }
    }

    /**
     * Internal function for deleting a node recursively
     *
     * @param current The current node in the recursion
     * @param title The title of the movie to be deleted
     * @return The current node in the recursion
     */
    private Node deleteMovieRecursively(Node current, String title) {
        if (current == null) {
            return null;
        }

        if (title.equals(current.movie.title)) {
            //Case where the node has no children
            if (current.left == null && current.right == null) {
                return null;

            //Cases where node has one child
            } else if (current.left == null) {
                return current.right;

            } else if (current.right == null) {
                return current.left;

            //Case where node has two children
            } else {
                //Replace the found node with the minimum node of the found node's right subtree
                Movie replacement = getSubtreeMinimum(current.right);

                current.movie = replacement;
                current.right = deleteMovieRecursively(current.right, replacement.title);

                return current;
            }
        }

        if (title.compareTo(current.movie.title) < 0) {
            current.left = deleteMovieRecursively(current.left, title);
        } else {
            current.right = deleteMovieRecursively(current.right, title);
        }

        return current;
    }

    /**
     * Deletes a movie from the tree
     *
     * @param title The title of the movie to be deleted
     */
    public void deleteMovie(String title) {
        root = deleteMovieRecursively(root, title);
    }
}

/**
 * Container class for each BST node
 */
class Node {
    Movie movie;
    Node left;
    Node right;

    /**
     * Construct a BST node with a movie object
     *
     * @param movie The movie contained in the node
     */
    Node(Movie movie) {
        this.movie = movie;
        left = null;
        right = null;
    }
}