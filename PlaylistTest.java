package dailymixes;

import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** Represents the test class for the playlist
 * 
 * @author himabathula
 * @version Aug 9, 2024
 */

public class PlaylistTest
    extends TestCase
{
    private Playlist playlist;
    private Playlist playlist2;
    private Playlist playlist3;
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;

    /**
     * sets up the playlists, songs, and min/max genre sets
     */

    @Override
    public void setUp()
    {
        playlist = new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 80, 3);
        playlist2 = new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 80, 3);
        playlist3 = new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 80, 3);
        song1 = new Song("Song1", 24, 37, 48, "Playlist1");
        song2 = new Song("Song1", 50, 55, 60, "Playlist1");
        song3 = new Song("Song1", 65, 50, 75, "Playlist1");
        song4 = new Song("Song4", 65, 50, 75, "Playlist1");

        minGenreSet = new GenreSet(20, 30, 40);
        maxGenreSet = new GenreSet(70, 60, 80);

    }


    /**
     * checks the get spaces left when number of songs is first 0 then updates
     * after one song gets added to playlist
     */

    public void testGetSpacesLeft()
    {
        assertEquals(3, playlist.getSpacesLeft());
        playlist.addSong(song1);
        assertEquals(2, playlist.getSpacesLeft());
    }


    /**
     * gets the max genre sets of one of the playlists
     */

    public void testGetMaxGenreSetl()
    {
        assertEquals(maxGenreSet, playlist.getMaxGenreSet());
    }


    /**
     * gets the number of songs added to one of the playlists
     */

    public void testGetNumberOfSongs()
    {
        assertEquals(0, playlist.getNumberOfSongs());
        playlist.addSong(song1);
        assertEquals(1, playlist.getNumberOfSongs());
    }


    /**
     * gets the length of the songs objects as stored in the Song array
     */

    public void testGetSongs()
    {
        Song[] songs = playlist.getSongs();
        assertEquals(3, songs.length);
        assertNull(songs[0]);
    }


    /**
     * checks whether the song is "qualified" based on whether it can fit within
     * the min and max genre sets of the playlist
     */

    public void testIsQualified()
    {
        assertTrue(playlist.isQualified(song1));
        Song invalid = new Song("invalid", 10, 10, 10, "Daily Mix 1");
        assertFalse(playlist.isQualified(invalid));
    }


    /**
     * gets the playlist name of the playlist
     */

    public void testGetName()
    {
        assertEquals("Daily Mix 1", playlist.getName());
    }


    /**
     * checks whether playlist gets full after its capacity is met with songs
     */

    public void testIsFull()
    {
        assertFalse(playlist.isFull());
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        assertTrue(playlist.isFull());
    }


    /**
     * tests whether playlist has reached capacity after songs get added
     */

    public void testGetCapacity()
    {
        assertEquals(3, playlist.getCapacity());
    }


    /**
     * checks the playlist size after one song gets added checks whether invalid
     * song can get added to playllist checks when max songs get added and
     * playlist gets full checks when invalid songs gets added
     */

    public void testAddSong()
    {
        assertTrue(playlist.addSong(song1));
        assertEquals(1, playlist.getNumberOfSongs());

        Song invalid = new Song("invalid", 10, 10, 10, "Daily Mix 1");
        assertFalse(playlist.addSong(invalid));
        assertEquals(1, playlist.getNumberOfSongs());

        playlist.addSong(song2);
        playlist.addSong(song3);
        assertTrue(playlist.isFull());
        Song qualified = new Song("qualified", 30, 40, 50, "Daily Mix 1");
        assertFalse(playlist.addSong(qualified));
        assertEquals(3, playlist.getNumberOfSongs());
        assertFalse(playlist.addSong(invalid));
        assertEquals(3, playlist.getNumberOfSongs());
    }


    /**
     * checks whether playlist attributes are concatenated properly
     */

    public void testToString()
    {
        Playlist string =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 80, 3);
        String expected = "Playlist: Daily Mix 1, # of songs: "
            + "0 (cap: 3), Requires: Pop:20%-70%, Rock:30%-60%, Country:40%-80%";
        assertEquals(expected, string.toString());
    }


    /**
     * tests whether playlist matches with its minimum genre set of playlist
     */

    public void testGetMinGenreSet()
    {
        assertEquals(minGenreSet, playlist.getMinGenreSet());
    }


    /**
     * tests whether the playlist name of the selected playlist returns the
     * right thing
     */

    public void testSetName()
    {
        playlist.setName("Playlist Name");
        assertEquals("Playlist Name", playlist.getName());

    }


    /**
     * tests the equals method for playlist object with the name, genreSets,
     * capacity
     */

    public void testEquals()
    {
        assertTrue(playlist.equals(playlist));
        assertTrue(playlist.equals(playlist2));
        assertFalse(playlist.equals(null));
        Playlist diffName =
            new Playlist("Diff Name", 20, 30, 40, 70, 60, 80, 3);
        assertFalse(playlist.equals(diffName));
        Playlist diffMinGenre =
            new Playlist("Daily Mix 1", 10, 30, 40, 70, 60, 80, 3);
        assertFalse(playlist.equals(diffMinGenre));
        Playlist diffMaxGenre =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 3);
        assertFalse(playlist.equals(diffMaxGenre));
        Playlist fewerSongs =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 80, 3);

        Playlist sameName =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 4);
        assertFalse(sameName.equals(diffMaxGenre));

        Playlist sameName2 =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 4);

        Playlist sameName3 =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 4);

        playlist3.addSong(song1);
        playlist3.addSong(song2);
        playlist3.addSong(song3);
        fewerSongs.addSong(song1);
        sameName.addSong(song4);
        sameName2.addSong(song3);
        sameName3.addSong(song3);
        assertFalse(playlist3.equals(fewerSongs));
        assertFalse(playlist.equals(null));
        assertFalse(playlist.equals(new Object()));
        assertFalse(sameName.equals(sameName2));
        assertTrue(sameName2.equals(sameName3));

    }


    /**
     * tets the compareTo method with several varrying attributes different
     * name, genres, same names with different sets
     */

    public void testCompareTo()
    {
        Playlist diffName =
            new Playlist("Diff Name", 20, 30, 40, 70, 60, 80, 3);

        Playlist diffMinGenre =
            new Playlist("Daily Mix 1", 10, 30, 40, 70, 60, 80, 3);

        Playlist diffMaxGenre =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 3);

        Playlist fewerSongs =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 80, 3);

        Playlist sameName =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 4);

        Playlist sameName2 =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 4);

        Playlist sameName3 =
            new Playlist("Daily Mix 1", 20, 30, 40, 70, 60, 90, 4);

        sameName2.addSong(song2);
        sameName2.addSong(song3);
        sameName3.addSong(song3);

        // capacity
        assertTrue(0 == playlist2.compareTo(playlist3));
        assertTrue(0 > diffMaxGenre.compareTo(sameName));
        assertTrue(0 < sameName.compareTo(diffMaxGenre));

        // spacesleft
        assertFalse(0 > sameName2.compareTo(sameName3));

    }

}
