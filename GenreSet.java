// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

/**
 * // -------------------------------------------------------------------------
 * /** Class creating genre percent composition (0 to 100) for songs and
 * playlists
 * 
 * @author himabathula
 * @version Aug 8, 2024
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    private int pop;
    private int rock;
    private int country;

    /**
     * Represents the percent breakdown for pop, rock, and country genres
     * 
     * @param popPercent
     *            percent of pop in song or playlist
     * @param rockPercent
     *            percent of rock in song or playlist
     * @param countryPercent
     *            percent of country in song or playlist
     */

    public GenreSet(int popPercent, int rockPercent, int countryPercent)
    {
        this.pop = popPercent;
        this.rock = rockPercent;
        this.country = countryPercent;
    }


    /**
     * @return returns the rock percent
     */

    public int getRock()
    {

        return rock;

    }


    /**
     * @return returns the pop percent
     */

    public int getPop()
    {
        return pop;
    }


    /**
     * @return the country percent
     */

    public int getCountry()
    {
        return country;
    }


    /**
     * Checks whether one genreSet is less than another genreSet
     * 
     * @param other
     *            represents an object of type GenreSet
     * @return true or false based on the comparison
     */

    private boolean isLessThanOrEqualsTo(GenreSet other)
    {
        return this.pop <= other.pop && this.rock <= other.rock
            && this.country <= other.country;
    }


    /**
     * public access to private method
     * 
     * @param other
     *            represents an object of type GenreSet
     * @return the validity of if one genreSet is less than or equal to another
     */

    public boolean testIsLessThanOrEqualTo(GenreSet other)
    {
        return isLessThanOrEqualsTo(other);
    }


    /**
     * Checks whether genreSet is within minimum and maximum range
     * 
     * @param minGenreSet
     *            genreSet representing the lower threshold for pop, rock,
     *            country
     * @param maxGenreSet
     *            genreSet representing the upper threshold for pop, cok,
     *            country
     * @return True or False if genreSet is within minimum and max sets
     */

    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {
        return this.isLessThanOrEqualsTo(maxGenreSet)
            && minGenreSet.isLessThanOrEqualsTo(this);
    }


    /**
     * Checks whether another object is of type genreSet and compares
     * 
     * @param obj
     *            represents another object to be compared to
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        GenreSet genreSet = (GenreSet)obj;
        return this.pop == genreSet.pop && this.rock == genreSet.rock
            && this.country == genreSet.country;
    }


    /**
     * returns negative, positive, or 0 depending on the sum of genre set,
     * compared to another
     * 
     * @param other
     *            other genreSet object
     */
    @Override
    public int compareTo(GenreSet other)
    {
        int thisSum = this.pop + this.rock + this.country;
        int otherSum = other.pop + other.rock + other.country;

        if (thisSum < otherSum)
        {
            return -1;
        }
        else if (thisSum > otherSum)
        {
            return 1;
        }

        else
        {
            return 0;
        }

    }


    /**
     * Appends the genreSet contents of a song or playlist
     */

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Pop:").append(pop).append(" Rock:").append(rock)
            .append(" Country:").append(country);
        return str.toString();
    }

}
