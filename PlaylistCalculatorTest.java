// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

import list.AList;
import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** rhandles the responsibility of adding or rejecting songs and ensuring
 * everything is satisfied before a song can be added to the playlist
 * 
 * @author himabathula
 * @version Aug 8, 2024
 */

public class PlaylistCalculatorTest
    extends TestCase
{

    private PlaylistCalculator playlistCalculator;
    private ArrayQueue<Song> songQueue;
    private Playlist[] playlists;
    private AList<Song> rejectedTracks;
    /**
     * represents the playlist1 object
     */
    private Playlist playlist1;

    /**
     * represents playlist2 object
     */
    private Playlist playlist2;

    /**
     * represents playlist3 object
     */
    private Playlist playlist3;

    /**
     * sets up the playlist, Queue for songs, and a rejected tracks list
     */

    @Override
    public void setUp()
    {
        playlists = new Playlist[3];
        playlists[0] = new Playlist("Daily Mix 1", 20, 20, 20, 80, 80, 80, 10);
        playlists[1] = new Playlist("Daily Mix 2", 30, 30, 30, 90, 90, 90, 5);
        playlists[2] = new Playlist("Daily Mix 3", 10, 10, 10, 70, 70, 70, 8);
        playlist1 = playlists[0];
        playlist2 = playlists[1];
        playlist3 = playlists[2];

        songQueue = new ArrayQueue<>(10);
        rejectedTracks = new AList<>();

        playlistCalculator = new PlaylistCalculator(songQueue, playlists);

    }


    /**
     * tests what happens when songQueue is null catches excpetion uses playlist
     * calculator to check whehther song queue is the correct queue being
     * reffered to
     */

    public void testGetQueue()
    {
        Exception exception = null;
        try
        {
            new PlaylistCalculator(null, playlists);
        }

        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        assertEquals(songQueue, playlistCalculator.getQueue());

    }


    /**
     * gets the playlist from the playlist calculator
     */

    public void testGetPlaylists()
    {
        assertEquals(playlists, playlistCalculator.getPlaylists());
    }


    /**
     * rejects song which is invalid from the song queue then the song queue is
     * empty
     */

    public void testReject()
    {
        Song song = new Song("Test song", 30, 40, 30, "Daily Mix 1");
        songQueue.enqueue(song);

        playlistCalculator.reject();
        assertTrue(songQueue.isEmpty());
        rejectedTracks.clear();
        playlistCalculator.reject();
        assertTrue(rejectedTracks.isEmpty());
    }


    /**
     * tests whether suggested playlist can expect song
     */

    public void testCanAcceptTester()
    {
        Song song = new Song("Test song", 30, 40, 30, "Daily Mix 1");
        boolean canAccept = playlistCalculator.canAcceptTetser(playlist1, song);

        Playlist fullPlaylist =
            new Playlist("Full Playlist", 20, 20, 20, 80, 80, 80, 0);
        canAccept = playlistCalculator.canAcceptTetser(fullPlaylist, song);
        assertFalse(canAccept);

    }


    /**
     * tests the maximum capacity of the song
     */

    public void testGetPlayListWithMaximumCapacity()
    {
        Song song = new Song("Song 1", 25, 35, 25, "Daily Mix 1");

        Playlist result =
            playlistCalculator.getPlayListWithMaximumCapacityTester(song);
        assertEquals(playlist3, result);

        playlist1 = new Playlist("Daily Mix 1", 50, 50, 50, 80, 80, 80, 10);
        playlist2 = new Playlist("Daily Mix 1", 50, 50, 50, 80, 80, 80, 10);
        playlist3 = new Playlist("Daily Mix 1", 50, 50, 50, 80, 80, 80, 10);

        playlists[0] = playlist1;
        playlists[1] = playlist2;
        playlists[2] = playlist3;

        playlistCalculator = new PlaylistCalculator(songQueue, playlists);

        Song noMatch = new Song("not qualified", 20, 20, 20, "Daily Mix 1");

        Playlist res =
            playlistCalculator.getPlayListWithMaximumCapacityTester(noMatch);

        assertNotNull(result);

    }


    /**
     * tests whether song can be added to playlist
     */

    public void testAddSongToPlaylist()
    {

        assertTrue(songQueue.isEmpty());
        boolean res = playlistCalculator.addSongToPlaylist();
        assertFalse(res);

        Song song = new Song("Song 1", 25, 35, 24, "Daily Mix 1");
        songQueue.enqueue(song);

        res = playlistCalculator.addSongToPlaylist();
        assertTrue(res);
        assertTrue(songQueue.isEmpty());
        assertEquals(1, playlist1.getNumberOfSongs());

    }


    /**
     * tests what happens when song added to playlist is null
     */

    public void testAddSongToPlaylistNull()
    {
        ArrayQueue<Song> songQueue1 = new ArrayQueue<>(10);
        rejectedTracks = new AList<>();

        Song song1 = new Song("Song null", 0, 0, 0, null);
        songQueue1.enqueue(song1);
        playlistCalculator = new PlaylistCalculator(songQueue1, playlists);
        boolean res1 = playlistCalculator.addSongToPlaylist();
        assertFalse(res1);
    }


    /**
     * tests the index of the song if index is invalid for the song, then the
     * song cannot be added
     */

    public void testGetPlaylistIndex()
    {
        int result = playlistCalculator.getPlaylistIndex(null);
        assertEquals(-1, result);

        int result1 = playlistCalculator.getPlaylistIndex("Daily Mix 1");
        assertEquals(0, result1);

        int result2 = playlistCalculator.getPlaylistIndex("Daily Mix 2");
        assertEquals(1, result2);

        int result3 = playlistCalculator.getPlaylistIndex("Daily Mix 3");
        assertEquals(2, result3);

        int result4 =
            playlistCalculator.getPlaylistIndex("Non-Existing Playlist");

        assertEquals(-1, result);

    }


    /**
     * checks whether suggested playlist is valid for song to get added into
     */

    public void testGetPlaylistForSong()
    {

        Playlist result = playlistCalculator.getPlaylistForSong(null);
        assertNull(result);

        Song song1 = new Song("qualified", 25, 35, 25, "Daily Mix 1");
        Playlist result1 = playlistCalculator.getPlaylistForSong(song1);
        assertEquals(playlist1, result1);

        Song song2 = new Song("unqualified", 90, 90, 90, "Daily Mix 1");
        Playlist result2 = playlistCalculator.getPlaylistForSong(song2);
        assertNull(result);

    }

}
