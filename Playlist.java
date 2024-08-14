// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

/**
 * // -------------------------------------------------------------------------
 * /** Represents the playlists for the songs to be added into. Includes
 * information of song and genreSet classes, but also implements min and max
 * genreSets and capacity of playlist
 * 
 * @author himabathula
 * @version Aug 9, 2024
 */

public class Playlist
    implements Comparable<Playlist>
{
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    /**
     * sets up playlist parameters. collects genreSet information from genreSet
     * class. also the number of songs in playlist and the capacity.
     * 
     * @param playlistName
     *            name of playlist
     * @param minPop
     *            minimum threshold for pop
     * @param minRock
     *            minimum threshold for rock
     * @param minCountry
     *            minimum threshold for country
     * @param maxPop
     *            maximum threshold for pop
     * @param maxRock
     *            maximum threshold for rock
     * @param maxCountry
     *            maximum threshold for country
     * @param playlistCap
     *            capacity of playlist
     */

    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        this.name = playlistName;
        this.minGenreSet = new GenreSet(minPop, minRock, minCountry);
        this.maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        this.capacity = playlistCap;
        this.songs = new Song[capacity];
        this.numberOfSongs = 0;

    }


    /**
     * @return returns the spaces left in the playlist
     */
    public int getSpacesLeft()
    {
        return capacity - numberOfSongs;
    }


    /**
     * @return the maxGenreSet of the songs
     */

    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    /**
     * @return the number of songs in playlist
     */

    public int getNumberOfSongs()
    {
        return numberOfSongs;
    }


    /**
     * @return the songs of the playlist
     */

    public Song[] getSongs()
    {
        return songs;
    }


    /**
     * @return the capacity of the playlist
     */

    public int getCapacity()
    {
        return capacity;
    }


    /**
     * @return returns the name of the playlist
     */

    public String getName()
    {
        return name;
    }


    /**
     * checks whether the playlist has reached its capacity
     * 
     * @return true or false if capacity of playlist and songs number reached
     *             same amount
     */

    public boolean isFull()
    {
        return numberOfSongs == capacity;

    }


    /**
     * @return checks whether song object is qualified by checking if its in the
     *             range of min and max genre sets
     * @param song
     *            represents the song object
     */

    public boolean isQualified(Song song)
    {
        return song.getGenreSet().isWithinRange(minGenreSet, maxGenreSet);
    }


    /**
     * @return whether song can successfully be added
     * @param newSong
     *            represents a new song to be added to the playlist
     */

    public boolean addSong(Song newSong)
    {
        if (isFull() || !isQualified(newSong))
        {
            return false;
        }

        songs[numberOfSongs++] = newSong;
        return true;
    }


    /**
     * @return minimum genreSet
     */

    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    /**
     * sets the name of the playlist
     * 
     * @param name
     *            name of the playlist
     */

    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * appends all the playlist information and returns the string
     */

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(name).append(", # of songs: ")
            .append(numberOfSongs).append(" (cap: ").append(capacity)
            .append("), Requires: ").append("Pop:").append(minGenreSet.getPop())
            .append("%-").append(maxGenreSet.getPop()).append("%, ")
            .append("Rock:").append(minGenreSet.getRock()).append("%-")
            .append(maxGenreSet.getRock()).append("%, ").append("Country:")
            .append(minGenreSet.getCountry()).append("%-")
            .append(maxGenreSet.getCountry()).append("%");
        return sb.toString();
    }


    /**
     * checked whether the name, capacity, min and max genre sets equal to
     * another object of type playlist
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

        Playlist other = (Playlist)obj;
        if (!name.equals(other.name) || capacity != other.capacity
            || !minGenreSet.equals(other.minGenreSet)
            || !maxGenreSet.equals(other.maxGenreSet)
            || numberOfSongs != other.numberOfSongs)
        {
            return false;
        }

        for (int i = 0; i < numberOfSongs; i++)
        {
            if (!songs[i].equals(other.songs[i]))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * orders the playlist based on capacity
     */

    @Override
    public int compareTo(Playlist other)
    {
        if (this.capacity != other.capacity)
        {
            return Integer.compare(this.capacity, other.capacity);
        }

        if (this.getSpacesLeft() != other.getSpacesLeft())
        {
            return Integer.compare(this.getSpacesLeft(), other.getSpacesLeft());
        }

        int minCompare = this.minGenreSet.compareTo(other.minGenreSet);
        if (minCompare != 0)
        {
            return minCompare;
        }

        int maxCompare = this.maxGenreSet.compareTo(other.maxGenreSet);
        if (maxCompare != 0)
        {
            return maxCompare;
        }

        return this.name.compareTo(other.name);
    }
}
