// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class ProjectRunner
{
    public ProjectRunner()
    {

    }


    public static void main(String[] args)
        throws ParseException,
        DailyMixDataException,
        FileNotFoundException
    {
        if (args.length == 2)
        {
            new PlaylistReader(args[0], args[1]);
        }

        else
        {
            new PlaylistReader(
                "/Users/himabathula/Desktop/eclipse_workspace_2114/SpotifyPlaylist/input.txt",
                "/Users/himabathula/Desktop/eclipse_workspace_2114/SpotifyPlaylist/playlists.txt");
        }
    }
}
