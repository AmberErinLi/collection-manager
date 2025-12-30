import java.io.*;
import java.util.*;

// This class represents a collection of golf balls.
public class CollectionManager {
    private GolfBallNode overallRoot;

    // Behavior:
    //   - Creates a new empty collection of golf balls.
    public CollectionManager() {
        overallRoot = null;
    }

    // Behavior:
    //   - Creates a new collection of golf balls based on the given input in sorted
    //     order. Sorted order means golf balls at the beginning of the rainbow come 
    //     first, and ties are broken chronologically by year first, and then alphabetically
    //     by description.
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given input is null
    // Parameters:
    //   - Scanner input - the Scanner used to read input about what golf balls 
    //     to add to the collection
    public CollectionManager(Scanner input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        buildCollection(input);
    }

    // Behavior:
    //   - Builds a collection of golf balls in sorted order using the given input
    // Parameters: 
    //   - Scanner input - the Scanner used to read input about what golf balls
    //     to add to the collection
    private void buildCollection(Scanner input) {
        if (input.hasNextLine()) {
            String color = input.nextLine();
            int year = Integer.parseInt(input.nextLine());
            String description = input.nextLine();
            GolfBall newGolfBall = new GolfBall(color, year, description);
            add(newGolfBall);
            buildCollection(input);
        }
    }

    // Behavior:
    //   - Adds the given golf ball to the collection if it is not already in the
    //     collection, preserving sorted order
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given golf ball is null
    // Parameters:
    //   - GolfBall golfBall - the golf ball to add to the collection
    public void add(GolfBall golfBall) {
        if (golfBall == null) {
            throw new IllegalArgumentException("Golf ball cannot be null.");
        }
        if (!contains(golfBall)) {
            this.overallRoot = add(golfBall, overallRoot);
        }
    }

    // Behavior:
    //   - Adds the given golf ball to the collection if it is not already in the
    //     collection, preserving sorted order
    // Returns:
    //   - A GolfBallNode representing the modified collection after adding the
    //     golf ball
    // Parameters:
    //   - GolfBall golfBall - the golf ball to add to the collection
    //   - GolfBallNode currentRoot - the current GolfBallNode being compared to
    //     the golf ball to be added
    private GolfBallNode add(GolfBall golfBall, GolfBallNode currentRoot) {
        if (currentRoot == null) {
            return new GolfBallNode(golfBall);
        } else if (golfBall.compareTo(currentRoot.golfBall) < 0) {
            currentRoot.left = add(golfBall, currentRoot.left);
        } else {
            currentRoot.right = add(golfBall, currentRoot.right);
        }
        return currentRoot;
    }

    // Behavior:
    //   - Determines whether the collection contains the given golf ball
    // Exceptions: 
    //   - Throws an IllegalArgumentException if the given golf ball is null
    // Returns:
    //   - true if the given golf ball is in the collection
    //   - false if the given golf ball is not in the collection
    // Parameters:
    //   - GolfBall golfBall - the golf ball to be checked for its presence in the
    //     collection
    public boolean contains(GolfBall golfBall) {
        if (golfBall == null) {
            throw new IllegalArgumentException("Golf ball cannot be null");
        }
        return contains(golfBall, overallRoot);
    }

    // Behavior:
    //   - Determines whether the collection contains the given golf ball
    // Returns:
    //   - true if the given golf ball is in the collection
    //   - false if the given golf ball is not in the collection
    // Parameters:
    //   - GolfBall golfBall - the golf ball to be checked for its presence in the
    //     collection
    //   - GolfBallNode currentRoot - the current GolfBallNode being checked if it
    //     matches the target golf ball
    private boolean contains(GolfBall golfBall, GolfBallNode currentRoot) {
        if (currentRoot == null) {
            return false;
        } else if (currentRoot.golfBall.equals(golfBall)) {
            return true;
        } else if (golfBall.compareTo(currentRoot.golfBall) < 0) {
            return contains(golfBall, currentRoot.left);
        } else {
            return contains(golfBall, currentRoot.right);
        }
    }

    // Returns:
    //   - A String representation of the collection, including the color, year, and
    //     description of each golf ball in sorted order.
    public String toString() {
        return toString(overallRoot, "");
    }

    // Returns:
    //   - A String representation of the collection, including the color, year, and
    //     description of each golf ball in sorted order.
    // Parameters:
    //   - GolfBallNode currentRoot - the current GolfBallNode having its information
    //     added to the String
    //   - String soFar - the in-progress String representation of the collection
    private String toString(GolfBallNode currentRoot, String soFar) {
        if (currentRoot != null) {
            soFar = toString(currentRoot.left, soFar);
            soFar += currentRoot.golfBall.toString() + "\n";
            soFar = toString(currentRoot.right, soFar);
        }
        return soFar;
    }

    // Behavior:
    //   - Saves the collection to the given output destination in a pre-order fashion
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given output is null
    // Parameters:
    //   - PrintStream output - the output destination the collection will be saved to
    public void save(PrintStream output) {
        if (output == null) {
            throw new IllegalArgumentException("Output cannot be null");
        }
        save(output, overallRoot);
    }

    // Behavior:
    //   - Saves the collection to the given output destination in a pre-order fashion
    // Parameters:
    //   - PrintStream output - the output destination the collection will be saved to
    //   - GolfBallNode currentRoot - the current GolfBallNode having its golf ball
    //     printed to the output destination
    private void save(PrintStream output, GolfBallNode currentRoot) {
        if (currentRoot != null) {
            output.println(currentRoot.golfBall.getColor());
            output.println(currentRoot.golfBall.getYear());
            output.println(currentRoot.golfBall.getDescription());
            save(output, currentRoot.left);
            save(output, currentRoot.right);
        }
    }

    // Returns:
    //   - A list of golf balls in the collection that are of the given color
    // Parameters:
    //   - String color - the color of golf balls to include in the returned list
    public List<GolfBall> filter(String color) {
        List<GolfBall> golfBalls = new ArrayList<>();
        filter(GolfBall.getColorIndex(color), overallRoot, golfBalls);
        return golfBalls;
    }

    // Behavior:
    //   - Adds all the golf balls in the collection of the specified color to a list
    // Parameters:
    //   - int targetColor - represents the color of the golf balls to add to the list
    //   - GolfBallNode currentRoot - the current GolfBallNode having its golf ball's color
    //     checked
    //   - List<GolfBall> golfBalls - the list of golf balls of the specified color
    private void filter(int targetColor, GolfBallNode currentRoot, List<GolfBall> golfBalls) {
        if (currentRoot != null) {
            int currColor = GolfBall.getColorIndex(currentRoot.golfBall.getColor());
            if (targetColor == currColor) {
                golfBalls.add(currentRoot.golfBall);
            }
            if (targetColor <= currColor) {
                filter(targetColor, currentRoot.left, golfBalls);
            }
            if (targetColor >= currColor) {
                filter(targetColor, currentRoot.right, golfBalls);
            }
        }
    }

    // Represents a golf ball within a collection
    private static class GolfBallNode {
        public final GolfBall golfBall;
        public GolfBallNode left;
        public GolfBallNode right;

        // Behavior:
        //   - Creates a new GolfBallNode that represents the given golf ball and has
        //     no children.
        // Parameters:
        //   - GolfBall golfBall - the golf ball represented by this node
        public GolfBallNode(GolfBall golfBall) {
            this.golfBall = golfBall;
            this.left = null;
            this.right = null;
        }
    }
}
