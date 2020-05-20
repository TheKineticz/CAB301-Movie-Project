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
     * @param movie The movie to find
     * @return The current node in the recursion
     */
    private boolean findMovieRecursively(Node current, Movie movie) {
        if (current == null) {
            return false;
        }

        if (current.movie.title.equals(movie.title)) {
            return true;
        }

        if (movie.title.compareTo(current.movie.title) < 0) {
            return findMovieRecursively(current.left, movie);

        } else {
            return findMovieRecursively(current.right, movie);
        }
    }

    /**
     * Check to see if the collection contains a movie
     *
     * @param movie The movie to search for
     * @return Returns true if the movie is found, false otherwise
     */
    public boolean containsMovie(Movie movie) {
        return findMovieRecursively(root, movie);
    }

    /**
     * Traverse the tree from a given root node to the minimum value from that root node, and return it's movie
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
     * @param movie The movie to be deleted
     * @return The current node in the recursion
     */
    private Node deleteMovieRecursively(Node current, Movie movie) {
        if (current == null) {
            return null;
        }

        if (movie.title.equals(current.movie.title)) {
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
                current.right = deleteMovieRecursively(current.right, replacement);

                return current;
            }
        }

        if (movie.title.compareTo(current.movie.title) < 0) {
            current.left = deleteMovieRecursively(current.left, movie);
        } else {
            current.right = deleteMovieRecursively(current.right, movie);
        }

        return current;
    }

    /**
     * Deletes a movie from the tree
     *
     * @param movie The movie to be deleted
     */
    public void deleteMovie(Movie movie) {
        root = deleteMovieRecursively(root, movie);
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