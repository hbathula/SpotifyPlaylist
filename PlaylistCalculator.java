// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)
package dailymixes;

import java.util.Arrays;
import list.AList;

/**
 * // -------------------------------------------------------------------------
 * /** checks the validity of the addition of song into playlist
 * 
 * @author himabathula
 * @version Aug 8, 2024
 */

public class PlaylistCalculator
{

    private static Playlist[] playlists;
    /**
     * represents number of playlists
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * minimum percents
     */
    public static final int MIN_PERCENT = 0;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;

    /**
     * max percent of genre
     */
    public static final int MAX_PERCENT = 100;

    /**
     * constructor
     * 
     * @param songQueue
     *            represents the song Queue
     * @param playlists
     *            represents the array of playlists
     */
    public PlaylistCalculator(ArrayQueue<Song> songQueue, Playlist[] playlists)
    {
        if (songQueue == null)
        {
            throw new IllegalArgumentException("Queue cannot be null");
        }

        this.songQueue = songQueue;
        PlaylistCalculator.playlists = playlists;
        this.rejectedTracks = new AList<>();
    }


    /**
     * checks whether playlist is full and whether a qualified song can be added
     * 
     * @param playlist
     *            song playlist
     * @param Song
     *            song to be added
     * @return whether song can be added
     */

    private boolean canAccept(Playlist playlist, Song song)
    {
        if (playlist.getCapacity() <= 0)
        {
            return false;
        }

        return playlist.isQualified(song);
    }


    /**
     * @param playlist
     *            playlist
     * @param song
     *            song
     * @return validity of previous method
     */
    public boolean canAcceptTetser(Playlist playlist, Song song)
    {
        return canAccept(playlist, song);
    }


    /**
     * @param aSong
     *            represents the maximum capacity of provided song
     * @return returns the maximum capacity of the song
     */

    private Playlist getPlayListWithMaximumCapacity(Song aSong)
    {

        // copy of playlists array
        Playlist[] sortedPlaylists = Arrays.copyOf(playlists, playlists.length);

        // sort the copied array
        Arrays.sort(sortedPlaylists);

        for (Playlist playlist : sortedPlaylists)
        {

            if (canAccept(playlist, aSong))
            {
                return playlist;

            }
        }

        return null;

    }


    /**
     * public access to previous private method
     * 
     * @param aSong
     *            song
     * @return returns the capacity of song
     */

    public Playlist getPlayListWithMaximumCapacityTester(Song aSong)
    {
        return getPlayListWithMaximumCapacity(aSong);
    }


    /**
     * @return whether song can be added to playlist
     */

    public boolean addSongToPlaylist()
    {
        if (songQueue.isEmpty())
        {
            return false;
        }

        Song nextSong = songQueue.getFront();

        Playlist targetPlaylist = getPlaylistForSong(nextSong);

        if (targetPlaylist != null)
        {
            targetPlaylist.addSong(nextSong);
            songQueue.dequeue();
            return true;
        }

        return false;

    }


    /**
     * returns the index of the playlist
     * 
     * @param playlistName
     *            the name of playlist
     * @return returns the index of playlist
     */

    public int getPlaylistIndex(String playlistName)
    {
        if (playlistName == null)
        {
            return -1;
        }

        for (int i = 0; i < playlists.length; i++)
        {
            if (playlists[i].getName().equals(playlistName))
            {
                return i;
            }
        }

        return -1;

    }


    /**
     * determines whether next song can be added to playlist
     * 
     * @param nextSong
     *            the next song to be shown to user
     * @return returns the playlist for the provided song
     */
    public Playlist getPlaylistForSong(Song nextSong)
    {
        if (nextSong == null)
        {
            return null;
        }

        // determine song's suggested playlist
        int suggestedPlaylistIndex =
            getPlaylistIndex(nextSong.getPlaylistName());

        if (suggestedPlaylistIndex != -1)
        {
            Playlist suggestedPlaylist = playlists[suggestedPlaylistIndex];

            if (canAccept(suggestedPlaylist, nextSong))
            {
                return suggestedPlaylist;
            }

        }

        return getPlayListWithMaximumCapacity(nextSong);
    }


    /**
     * @return returns songQueue
     */

    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    /**
     * @return the playlist array of playlists
     */

    public Playlist[] getPlaylists()
    {
        return playlists;
    }


    /**
     * rejects song, if user selects
     */

    public void reject()
    {
        if (!songQueue.isEmpty())
        {
            Song rejected = songQueue.dequeue();
            rejectedTracks.add(rejected);
        }
    }

}
