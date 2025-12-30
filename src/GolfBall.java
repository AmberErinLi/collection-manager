import java.util.Scanner;

// Represents a golf ball object with various features such as color, year obtained,
// and a description.
public class GolfBall implements Comparable<GolfBall> {
    private static final String[] COLORS = {"white", "red", "pink", "orange", "yellow",
        "green", "blue", "purple", "brown", "black"};
    private final String color;
    private final int year;
    private final String description;

    // Behavior:
    //   - Creates a GolfBall object with the given color, year, and description
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given color is invalid
    // Parameters:
    //   - String color - the color of the golf ball
    //   - int year - the year the golf ball was collected
    //   - String description - a description of the golf ball
    public GolfBall(String color, int year, String description) {
        if (!GolfBall.checkColor(color.toLowerCase())) {
            throw new IllegalArgumentException("Invalid color: " + color
                                                + " is not present in COLORS");
        }
        this.color = color.toLowerCase();
        this.year = year;
        this.description = description;
    }

    // Behavior: 
    //   - Checks if the given color is valid for a GolfBall
    // Returns: 
    //   - true if the color is valid
    //   - false if the color is invalid
    // Parameters:
    //   - String color - the color to check for validity
    private static boolean checkColor(String color) {
        for (String currColor : COLORS) {
            if (currColor.equals(color)) {
                return true;
            }
        }
        return false;
    }

    // Behavior:
    //   - Prompts the user for input to create a new GolfBall object
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given input is null
    // Parameters:
    //   - Scanner input - the Scanner used to read user input
    // Returns:
    //   - A new GolfBall with the characteristics provided by the user
    public static GolfBall parse(Scanner input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        System.out.print("What color is your golf ball? ");
        String color = input.nextLine();

        System.out.print("When did you get your golf ball? ");
        int year = Integer.parseInt(input.nextLine());

        System.out.print("Give a description of your golf ball: ");
        String description = input.nextLine();

        return new GolfBall(color, year, description);
    }

    // Returns:
    //   - A String representation of this GolfBall, including its description, color,
    //     and year obtained
    public String toString() {
        return this.description + " | Color: " + this.color + " | Year Obtained: " + year;
    }

    // Behavior:
    //   - Compares this GolfBall to another GolfBall by color, year, and description.
    //     A golf ball is considered less than another golf ball if it comes earlier in
    //     the rainbow, if it was obtained in an earlier year, or if its description
    //     comes first alphabetically, in that order of priority.
    // Returns:
    //   - A negative integer if this GolfBall is less than the other GolfBall, zero if
    //     this GolfBall is equal to the other GolfBall, and a positive integer if this
    //     GolfBall is greater than the other GolfBall
    // Parameters:
    //   - GolfBall other - the other GolfBall that this GolfBall is being compared to
    public int compareTo(GolfBall other) {
        if (!this.color.equals(other.color)) {
            return GolfBall.getColorIndex(this.color) - GolfBall.getColorIndex(other.color);
        } else if (this.year != other.year) {
            return this.year - other.year;
        } else {
            return this.description.compareTo(other.description);
        }
    }

    // Returns:
    //   - The index of the given color in the array of valid colors or -1 if the color
    //     is invalid
    // Parameters:
    //   - String color - the color to find the index of
    public static int getColorIndex(String color) {
        color = color.toLowerCase();
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
                return i;
            }
        }
        return -1;
    }

    // Returns:
    //   - The color of this GolfBall
    public String getColor() {
        return this.color;
    }

    // Returns:
    //   - The year this GolfBall was acquired
    public int getYear() {
        return this.year;
    }

    // Returns:
    //   - The description of this GolfBall
    public String getDescription() {
        return this.description;
    }

    // Behavior:
    //   - Checks if this GolfBall is equal to the given other object. Two GolfBalls
    //     are equal if they have the same color, year, and description.
    // Returns:
    //   - true if this GolfBall and the other object are equal
    //   - false if this GolfBall and the other object are not equal
    // Parameters:
    //   - Object o - the object to compare to this GolfBall
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof GolfBall) {
            GolfBall other = (GolfBall) o;
            return this.color.equals(other.color)
                    && this.year == other.year
                    && this.description.equals(other.description);
        } else {
            return false;
        }
    }

    // Returns:
    //   - A unique hash code value for this GolfBall
    public int hashCode() {
        return 31 * this.description.hashCode() + 31 * (this.year + 31 * this.color.hashCode());
    }
}
