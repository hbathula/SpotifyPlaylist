// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

import student.TestCase;

/**
 * Tests the GenreSet Class
 * 
 * @author himabathula
 * @version Aug 8, 2024
 */

public class GenreSetTest
    extends TestCase
{
    private GenreSet genreSet1;
    private GenreSet genreSet2;
    private GenreSet genreSet3;
    private GenreSet genreSet4;
    private GenreSet genreSet5;
    private GenreSet genreSet6;
    private GenreSet genreSet7;
    private GenreSet genreSet8;
    private GenreSet genreSet9;
    private GenreSet genreSet10;
    private GenreSet genreSet11;
    private GenreSet genreSet12;
    private GenreSet genreSet13;
    private GenreSet genreSet14;

    private GenreSet genreSetMin;
    private GenreSet genreSetMax;
    private GenreSet genreSetMy1;
    private GenreSet genreSetMy2;
    private GenreSet genreSetMy3;
    private GenreSet differentCountrySet;

    /**
     * sets up genresets to be checked inside the test methods
     */

    @Override
    public void setUp()
    {   // <=
        genreSet1 = new GenreSet(30, 40, 50);
        genreSet2 = new GenreSet(30, 40, 50);
        genreSet3 = new GenreSet(40, 50, 60);
        genreSet4 = new GenreSet(20, 30, 40);
        genreSet5 = new GenreSet(30, 30, 30);

        genreSet6 = new GenreSet(40, 30, 30);

        genreSet7 = new GenreSet(20, 50, 60);

        genreSet8 = new GenreSet(20, 30, 60);

        genreSet9 = new GenreSet(40, 50, 40);

        genreSet10 = new GenreSet(40, 20, 60);
        genreSet11 = new GenreSet(40, 20, 50);
        genreSet12 = new GenreSet(20, 60, 50);
        genreSet13 = new GenreSet(20, 20, 20);
        genreSet14 = new GenreSet(60, 60, 60);
        // - tft
        genreSetMin = new GenreSet(5, 5, 5);
        genreSetMax = new GenreSet(10, 10, 10);
        genreSetMy1 = new GenreSet(6, 6, 6);
        genreSetMy2 = new GenreSet(2, 2, 2);
        genreSetMy3 = new GenreSet(11, 11, 11);

        differentCountrySet = new GenreSet(30, 40, 60);

    }


    /**
     * checks whether genreSet is getting appropriate genre values
     */

    public void testGetters()
    {
        assertEquals(30, genreSet1.getPop());
        assertEquals(40, genreSet2.getRock());
        assertEquals(60, genreSet3.getCountry());
    }


    /**
     * tests whether one genre set is less than or equal to another. checks
     * playlist1 parameters with all other genreSets to ensure valid coverage of
     * method
     */

    public void testIsLessThanOrEqualsTo()
    {
        assertTrue(genreSet1.testIsLessThanOrEqualTo(genreSet2));
        assertTrue(genreSet1.testIsLessThanOrEqualTo(genreSet3));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet4));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet5));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet6));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet7));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet8));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet9));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet9));
        assertFalse(genreSet1.testIsLessThanOrEqualTo(genreSet10));

    }


    /**
     * checks whether genreSets are within the minimum and maxium ranges also
     * checks for when ranges are equal
     */

    public void testIsWithinRange()
    {
        assertTrue(genreSetMy1.isWithinRange(genreSetMin, genreSetMax));
        assertFalse(genreSetMy2.isWithinRange(genreSetMin, genreSetMax));
        assertFalse(genreSetMy3.isWithinRange(genreSetMin, genreSetMax));
        assertTrue(genreSetMy1.isWithinRange(genreSetMy1, genreSetMy1));
    }


    /**
     * tests whether one genre set is equal to another. checks playlist1
     * parameters with all other genreSets to ensure valid coverage of method.
     */

    public void testEquals()
    {
        assertTrue(genreSet1.equals(genreSet2));
        assertFalse(genreSet1.equals(genreSet3));
        assertFalse(genreSet1.equals(genreSet4));
        assertFalse(genreSet1.equals(genreSet5));
        assertFalse(genreSet1.equals(genreSet6));
        assertFalse(genreSet1.equals(genreSet7));
        assertFalse(genreSet1.equals(genreSet8));
        assertFalse(genreSet1.equals(genreSet9));
        assertFalse(genreSet1.equals(genreSet10));
        assertFalse(genreSet1.equals(genreSet11));
        assertFalse(genreSet1.equals(genreSet12));
        assertFalse(genreSet1.equals(genreSet13));
        assertFalse(genreSet1.equals(genreSet14));

        assertFalse(genreSet1.equals(differentCountrySet));

        assertFalse(genreSet3.equals(null));
        assertFalse(genreSet3.equals(new Object()));
        assertTrue(genreSet1.equals(genreSet1));

    }


    /**
     * checks the comparison of the summation of the smaller and larger index by
     * comparing the int results (-1,1,0).
     */
    public void testCompareTo()
    {
        // Test when thisSum < otherSum
        GenreSet smallerSet = new GenreSet(20, 30, 40); // Sum = 90
        GenreSet largerSet = new GenreSet(40, 50, 60); // Sum = 150
        assertEquals(smallerSet.compareTo(largerSet), -1);

        assertEquals(largerSet.compareTo(smallerSet), 1);

        GenreSet equalSet1 = new GenreSet(30, 40, 50);
        GenreSet equalSet2 = new GenreSet(50, 30, 40);
        assertEquals(0, equalSet1.compareTo(equalSet2));
        assertEquals(0, smallerSet.compareTo(smallerSet));
    }


    /**
     * appends the genreSet criteria and checks result is correct
     */

    public void testToString()
    {
        GenreSet genreSet20 = new GenreSet(30, 40, 50);
        assertEquals("Pop:30 Rock:40 Country:50", genreSet1.toString());
    }

}
