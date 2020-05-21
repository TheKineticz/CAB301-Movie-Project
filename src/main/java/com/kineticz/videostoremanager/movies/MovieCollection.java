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
     * Internal function for traversing recursively to add a node
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
     * Adds a new movie
     *
     * @param movie The movie to be added
     */
    public void addMovie(Movie movie) {
        root = addMovieRecursively(root, movie);
    }

    /**
     * Internal function for traversing recursively through nodes to find a movie
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
     * Internal function for traversing the tree to delete a node
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

    /**
     * Internal function for traversing recursively to find the size of the tree
     *
     * @param node The current node in the recursion
     * @return The current sum of the size
     */
    private int getSizeRecursively(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSizeRecursively(node.left) + getSizeRecursively(node.right);
        }
    }

    /**
     * Gets the size of the tree
     *
     * @return The size of the tree
     */
    public int getSize() {
        return getSizeRecursively(root);
    }

    /**
     * Internal recursive function for traversing the tree in-order and populating an array with the tree values
     *
     * @param current The current node in the recursion
     * @param array The array object being populated
     * @param i The current index of the array value to be populated
     */
    private int populateArray_InOrder(Node current, Movie[] array, int i) {
        if (current == null) {
            return i;
        }

        i = populateArray_InOrder(current.left, array, i);
        array[i++] = current.movie;
        i = populateArray_InOrder(current.right, array, i);

        return i;
    }

    /**
     * Convert the tree to an array using in-order traversal
     *
     * @return The tree in array form
     */
    public Movie[] toArray() {
        Movie[] movies = new Movie[getSize()];
        populateArray_InOrder(root, movies, 0);

        return movies;
    }

    /**
     * Gets the top 10 most frequently borrowed movies
     *
     * @return An array of 10 movies in order of how many times they have been borrowed
     */
    public Movie[] getTop10Borrowed() {
        Movie[] allMovies = toArray();
        int size = Math.min(allMovies.length, 10);

        MovieSorter.sortByBorrowFrequencyDescending(allMovies);

        Movie[] top10 = new Movie[size];
        System.arraycopy(allMovies, 0, top10, 0, size);

        return top10;
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

/**
 * Interface for a class to quantitatively compare one movie to another
 */
interface MovieComparator {

    /**
     * Compares some value of a movie to another, returning a is smaller, larger or equal to b
     * @param a The first movie
     * @param b The second movie
     * @return Returns -1 if b is greater, 1 if a is greater, or 0 if equal
     */
    int compare(Movie a, Movie b);
}

/**
 * Comparator class for comparing the amount of times two movies have been borrowed
 */
class BorrowFrequencyDescendingComparator implements MovieComparator {

    /**
     * Compares how many times two movies have been borrowed
     *
     * @param a The first movie
     * @param b The second movie
     * @return Returns -1 if b is has been borrowed more times, 1 if a has been borrowed more times, or 0 if equal
     */
    public int compare(Movie a, Movie b) {
        return Integer.compare(b.getTimesBorrowed(), a.getTimesBorrowed());
    }
}

/**
 * Container class for the different movie sorting algorithms
 */
class MovieSorter {

    /**
     * Recursive merge sort function for sorting an array of movies
     *
     * @param array The array to be sorted
     * @param low The current minimum index
     * @param high The current maximum index
     * @param comparator The comparator object for comparing the movies, passed to merge
     */
    private static void mergeSort(Movie[] array, int low, int high, MovieComparator comparator) {
        if (high <= low) {
            return;
        }

        int mid = (low + high) / 2;

        mergeSort(array, low, mid, comparator);
        mergeSort(array, mid + 1, high, comparator);
        merge(array, low, mid, high, comparator);
    }

    /**
     * Mergesort merge helper method
     *
     * @param array The array to be sorted
     * @param low The current minimum index
     * @param mid The current middle index
     * @param high The current maximum index
     * @param comparator The comparator object for comparing the movies
     */
    private static void merge(Movie[] array, int low, int mid, int high, MovieComparator comparator) {
        int leftLen = mid - low + 1;
        int rightLen = high - mid;

        Movie[] left = new Movie[leftLen];
        System.arraycopy(array, low, left, 0, leftLen);
        Movie[] right = new Movie[rightLen];
        System.arraycopy(array, mid + 1, right, 0, rightLen);

        int li = 0;
        int ri = 0;

        //Copy left and right back into the main array
        for (int i = low; i < high + 1; i++) {
            //Copy in the minimum value in right and left if there are still uncopied values in both
            if (li < leftLen && ri < rightLen) {
                if (comparator.compare(left[li], right[ri]) < 0) {
                    array[i] = left[li];
                    li++;

                } else {
                    array[i] = right[ri];
                    ri++;
                }

            //Copy in from left if right is empty
            } else if (li < leftLen) {
                array[i] = left[li];
                li++;

            //Copy in from right if left is empty
            } else if (ri < rightLen) {
                array[i] = right[ri];
                ri++;
            }
        }
    }

    /**
     * Shorthand function for sorting an array of movies based on how often they have been borrowed
     *
     * @param array The array to be sorted
     */
    public static void sortByBorrowFrequencyDescending(Movie[] array) {
        MovieComparator comparator = new BorrowFrequencyDescendingComparator();
        mergeSort(array, 0, array.length - 1, comparator);
    }
}