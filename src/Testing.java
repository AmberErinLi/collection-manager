import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.Region;

// This class tests that the GolfBall and CollectionManager classes work as expected.
public class Testing {
    
    @Test
    @DisplayName("Test GolfBall constructor")
    public void testGolfBallConstructor() {
        GolfBall test = new GolfBall("white", 2025, "test golf ball");
        String expectedGolfBall = "test golf ball | Color: white | Year Obtained: 2025";
        String actualGolfBall = test.toString();
        assertEquals(expectedGolfBall, actualGolfBall);
        assertThrows(IllegalArgumentException.class, () -> {
            new GolfBall("turquoise", 2025, "test golf ball");
        });
    }

    @Test
    @DisplayName("Test GolfBall toString")
    public void testGolfBallToString() {
        GolfBall test = new GolfBall("white", 2025, "test golf ball");
        String expectedString = "test golf ball | Color: white | Year Obtained: 2025";
        String actualString = test.toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test compareTo")
    public void testCompareTo() {
        GolfBall golfBall1 = new GolfBall("white", 2025, "test golf ball");
        GolfBall golfBall2 = new GolfBall("white", 2025, "test golf ball");
        GolfBall golfBall3 = new GolfBall("white", 2025, "aaa");
        GolfBall golfBall4 = new GolfBall("white", 2024, "test golf ball");
        GolfBall golfBall5 = new GolfBall("red", 2024, "test golf ball");
        assertTrue(golfBall1.compareTo(golfBall2) == 0);
        assertTrue(golfBall1.compareTo(golfBall3) > 0);
        assertTrue(golfBall4.compareTo(golfBall1) < 0);
        assertTrue(golfBall5.compareTo(golfBall4) > 0);
    }

    @Test
    @DisplayName("Test getColorIndex")
    public void testGetColorIndex() {
        assertEquals(1, GolfBall.getColorIndex("red"));
        assertEquals(-1, GolfBall.getColorIndex("turquoise"));
    }

    @Test
    @DisplayName("Test getColor")
    public void testGetColor() {
        GolfBall test = new GolfBall("white", 2025, "test golf ball");
        assertEquals("white", test.getColor());
    }

    @Test
    @DisplayName("Test getYear")
    public void testGetYear() {
        GolfBall test = new GolfBall("white", 2025, "test golf ball");
        assertEquals(2025, test.getYear());
    }

    @Test
    @DisplayName("Test getDescription")
    public void testGetDescription() {
        GolfBall test = new GolfBall("white", 2025, "test golf ball");
        assertEquals("test golf ball", test.getDescription());
    }

    @Test
    @DisplayName("Test equals")
    public void testEquals() {
        GolfBall golfBall1 = new GolfBall("white", 2025, "test golf ball");
        GolfBall golfBall2 = new GolfBall("red", 2023, "test golf ball 2");
        GolfBall golfBall3 = new GolfBall("white", 2025, "test golf ball");
        List<String> other = new ArrayList<>();
        assertTrue(golfBall1.equals(golfBall1));
        assertTrue(golfBall1.equals(golfBall3));
        assertFalse(golfBall1.equals(golfBall2));
        assertFalse(golfBall1.equals(other));
    }

    @Test
    @DisplayName("Test hashCode")
    public void testHashCode() {
        GolfBall test = new GolfBall("white", 2025, "test");
        int colorHash = "white".hashCode();
        int yearHash = 2025;
        int descriptionHash = "test".hashCode();
        int expectedHash = 31 * descriptionHash + 31 * (yearHash + 31 * colorHash);
        int actualHash1 = test.hashCode();
        int actualHash2 = test.hashCode();
        assertEquals(expectedHash, actualHash1);
        assertEquals(actualHash1, actualHash2);
    }

    @Test
    @DisplayName("Test no argument CollectionManager constructor")
    public void testCollectionConstructor1() {
        CollectionManager test = new CollectionManager();
        assertEquals("", test.toString());
    }

    @Test
    @DisplayName("Test Scanner CollectionManager constructor")
    public void testCollectionConstructor2() throws FileNotFoundException {
        Scanner input = new Scanner(new File("small.txt"));
        CollectionManager test = new CollectionManager(input);
        String expected = "WJGA State Championship | Color: white | Year Obtained: 2016\n"
                        + "WJGA State Championship | Color: white | Year Obtained: 2017\n"
                        + "country flags | Color: red | Year Obtained: 2016\n"
                        + "red and white soccer ball | Color: red | Year Obtained: 2016\n"
                        + "black and white soccer ball | Color: black | Year Obtained: 2016\n";
        String actual = test.toString();
        assertEquals(expected, actual);
        assertThrows(IllegalArgumentException.class, () -> {
            new CollectionManager(null);
        });
    }

    @Test
    @DisplayName("Test add")
    public void testAdd() throws FileNotFoundException {
        Scanner input = new Scanner(new File("small.txt"));
        CollectionManager test1 = new CollectionManager();
        CollectionManager test2 = new CollectionManager(input);
        test1.add(new GolfBall("black", 2016, "black and white soccer ball"));
        test1.add(new GolfBall("white", 2016, "WJGA State Championship"));
        test1.add(new GolfBall("red", 2016, "red and white soccer ball"));
        test1.add(new GolfBall("red", 2016, "country flags"));
        test1.add(new GolfBall("white", 2017, "WJGA State Championship"));
        assertEquals(test2.toString(), test1.toString());
        assertThrows(IllegalArgumentException.class, () -> {
            test1.add(null);
        });
    }

    @Test
    @DisplayName("Test contains")
    public void testContains() {
        CollectionManager test = new CollectionManager();
        GolfBall golfBall1 = new GolfBall("white", 2025, "test golf ball");
        GolfBall golfBall2 = new GolfBall("white", 2025, "test golf ball 2");
        test.add(golfBall1);
        assertTrue(test.contains(golfBall1));
        assertTrue(test.contains(new GolfBall("white", 2025, "test golf ball")));
        assertFalse(test.contains(golfBall2));
        assertThrows(IllegalArgumentException.class, () -> {
            test.contains(null);
        });
    }

    @Test
    @DisplayName("Test CollectionManager toString")
    public void testCollectionManagerToString() throws FileNotFoundException {
        Scanner input = new Scanner(new File("small.txt"));
        CollectionManager test = new CollectionManager(input);
        String expected = "WJGA State Championship | Color: white | Year Obtained: 2016\n"
                        + "WJGA State Championship | Color: white | Year Obtained: 2017\n"
                        + "country flags | Color: red | Year Obtained: 2016\n"
                        + "red and white soccer ball | Color: red | Year Obtained: 2016\n"
                        + "black and white soccer ball | Color: black | Year Obtained: 2016\n";
        String actual = test.toString();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test save")
    public void testSave() throws FileNotFoundException {
        Scanner input = new Scanner(new File("large.txt"));
        CollectionManager test1 = new CollectionManager(input);
        File testFile = new File("test.txt");
        PrintStream output = new PrintStream(testFile);
        test1.save(output);
        Scanner input2 = new Scanner(testFile);
        CollectionManager test2 = new CollectionManager(input2);
        assertEquals(test1.toString(), test2.toString());
        assertThrows(IllegalArgumentException.class, () -> {
            test1.save(null);
        });
    }

    @Test
    @DisplayName("Test filter")
    public void testFilter() throws FileNotFoundException {
        Scanner input = new Scanner(new File("medium.txt"));
        CollectionManager test = new CollectionManager(input);
        List<GolfBall> expected = new ArrayList<>();
        expected.add(new GolfBall("pink", 2015, "hot pink golf ball"));
        expected.add(new GolfBall("pink", 2016, "light pink golf ball"));
        assertEquals(expected, test.filter("pink"));
        assertEquals(new ArrayList<GolfBall>(), test.filter("orange"));
    }
}
