// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

/**
 * // -------------------------------------------------------------------------
 * This class contains the song name, genre set, and suggested playlist
 * information
 * 
 * @author himabathula
 * @version Aug 8, 2024
 */

public class Song

{
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    /**
     * constructor sets up the song object
     * 
     * @param name
     *            name of song
     * @param pop
     *            percent pop
     * @param rock
     *            percent rock
     * @param country
     *            percent country
     * @param suggested
     *            represents the suggested playlist the song belongs too
     */

    Song(String name, int pop, int rock, int country, String suggested)
    {
        this.name = name;
        this.genreSet = new GenreSet(pop, rock, country);
        this.suggestedPlaylist = suggested;
    }


    /**
     * @return returns the suggested playlist of the song
     */
    public String getPlaylistName()
    {
        return suggestedPlaylist;
    }


    /**
     * @return returns the name of the song
     */

    public String getName()
    {
        return name;
    }


    /**
     * @return returns the genreSet of the song
     */

    public GenreSet getGenreSet()
    {
        return genreSet;
    }


    /**
     * concatenates the attributes of the song name of song, suggested playlist
     * if there is one, and the percent genre breakdown // Sample output: "The
     * Final Countdown Pop:30 Rock:45 Country:3 Suggested: p1" // or
     * "No-Playlist Our Song Pop:24 Rock:14 Country:50"
     */

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();

        if (suggestedPlaylist.isEmpty())
        {
            string.append("No-Playlist ");
            string.append(name).append(" Pop:").append(genreSet.getPop())
                .append(" Rock:").append(genreSet.getRock()).append(" Country:")
                .append(genreSet.getCountry());

        }

        else
        {
            string.append(name).append(" Pop:").append(genreSet.getPop())
                .append(" Rock:").append(genreSet.getRock()).append(" Country:")
                .append(genreSet.getCountry());
            string.append(" Suggested: ").append(suggestedPlaylist);
        }

        return string.toString();

    }


    /**
     * equals method compares if two songs are equal when their name, genreset,
     * and suggested playlist
     * 
     * @param obj
     *            represents an object of type song
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

        Song song = (Song)obj;
        return (this.name.equals(song.name)
            && this.genreSet.equals(song.genreSet)
            && this.suggestedPlaylist.equals(song.suggestedPlaylist));

    }
}
