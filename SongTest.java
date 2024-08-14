// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** Represents test class for Song Class
 * 
 * @author himabathula
 * @version Aug 9, 2024
 */

public class SongTest
    extends TestCase
{
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Song song5;
    private Song song7;

    /**
     * sets up several song objects with different genreSets and suggested
     * playlist names
     */

    @Override
    public void setUp()
    {
        song1 = new Song("Song 1", 40, 50, 60, "Daily Mix 1");
        song2 = new Song("Song 1", 40, 50, 60, "Daily Mix 1");
        song3 = new Song("Song 2", 20, 30, 40, "");
        song4 = new Song("Song 1", 10, 20, 30, "Daily Mix 2");
        song5 = new Song("Song 3", 20, 30, 40, null);
        song7 = new Song("Song 1", 40, 50, 60, "Daily Mix 3");

    }


    /**
     * gets the playlist name of the song
     */

    public void testGetPlaylistName()
    {

        assertEquals("Daily Mix 1", song1.getPlaylistName());
        // assertTrue(song3.getPlaylistName().equals(""));
    }


    /**
     * gets the name of the song public void testGetname() { assertEquals("Song
     * 1", song1.getName()); assertEquals("Song 2", song3.getName()); } /** gets
     * the GenreSet of the songs
     */

    public void testGetGenreSet()
    {
        GenreSet genreSet = song2.getGenreSet();
        assertEquals(40, genreSet.getPop());
        assertEquals(50, genreSet.getRock());
        assertEquals(60, genreSet.getCountry());
    }


    /**
     * returns the string of the song object includes the name, genres, and
     * suggested name if any
     */

    public void testToString()
    {

        // Sample output: "The Final Countdown Pop:30 Rock:45 Country:3
        // Suggested: p1"
        // or "No-Playlist Our Song Pop:24 Rock:14 Country:50"
        assertEquals(
            "Song 1 Pop:40 Rock:50 Country:60 Suggested: Daily Mix 1",
            song1.toString());
        assertEquals(
            "No-Playlist Song 2 Pop:20 Rock:30 Country:40",
            song3.toString());

    }


    /**
     * equals method is being tested using song1 against other songs and other
     * objects
     */

    public void testEquals()
    {
        assertTrue(song1.equals(song1));
        assertTrue(song1.equals(song2));
        assertFalse(song1.equals(song3));
        assertFalse(song1.equals(song4));
        assertFalse(song1.equals(song5));
        assertFalse(song1.equals(song7));
        assertFalse(song1.equals(null));
        assertFalse(song1.equals(new Object()));
    }

}
