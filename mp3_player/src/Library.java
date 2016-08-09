import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class Library {
	ArrayList<Mp3> songs = new ArrayList<Mp3>();
	Play play;
	
	// getter for ArrayList of songs.
	public ArrayList<Mp3> getSongs() {
		return songs;
	}

	// setter for ArrayList of songs.
	public void setSongs(ArrayList<Mp3> songs) {
		this.songs = songs;
	}

	// this method loads the songs from multiple directories in the file system.
	public void loadLibrary(File node) {
		// allocate audio file name.
		AudioFile f;

		// detects if current path is a directory.
		if (node.isDirectory()) {
			String[] children = node.list();
			for (int i = 0; children != null && i < children.length; i++) {
				loadLibrary(new File(node, children[i]));
			}
		}
		// detects if current path is a file.
		if (node.isFile()) {
			// detects if there is a file that ends with mp3.
			if (node.getName().endsWith(".mp3")) {
				try {
					// assign and reads audio file.
					f = AudioFileIO.read(node);

					// grabs all of the informations of the audio file.
					Tag tag = f.getTag();

					// grabs meta data from tag of mp3 file of type artist.
					String artist = tag.getFirst(FieldKey.ARTIST);

					// grabs meta data from tag of mp3 file of type title.
					String title = tag.getFirst(FieldKey.TITLE);

					// grabs meta data from tag of mp3 file of type tags.
					String album = tag.getFirst(FieldKey.ALBUM);

					// grabs meta data from tag of mp3 file of type tags.
					String path = node.getPath();

					// creates a new Mp3 object called song.
					Mp3 song = new Mp3(artist, title, album, path);

					// adds song to the ArrayList songs.
					System.out.println(song.getTitle());
					songs.add(song);
				}
				// catch block.
				catch (CannotReadException e) {
					// debugging if there is a catch.
					e.printStackTrace();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (TagException e) {
					e.printStackTrace();
				} catch (ReadOnlyFileException e) {
					e.printStackTrace();
				} catch (InvalidAudioFrameException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// this method saves the mp3 files to the file songs in the song directory.
	public void saving() {
		// debugging
		System.out.println("Just called saving()");

		// set path and file to write songs to.
		Path target = Paths
				.get("/Users/Tim/Documents/cs112/Project4/songs/songs.txt");

		// define input scheme for data.
		Charset charset = Charset.forName("US-ASCII");

		// now starting try catch for i/o.
		try (BufferedWriter writer = Files.newBufferedWriter(target, charset)) {

			// gets the number of songs.
			writer.write(songs.size() + "\n");

			for (Mp3 song : songs) {
				writer.write(song.getTitle() + "\n");
				writer.write(song.getArtist() + "\n");
				writer.write(song.getAlbum() + "\n");
				writer.write(song.getPath() + "\n" + "\n");
			}

			// finishes saving all data.
			writer.flush();

			// terminates connection.
			writer.close();

			// debugging.
			System.out.println("Finished calling saving()");
			System.out.println("Saved " + songs.size() + " songs.");
		}
		// catch block.
		catch (Exception e) {
			// debugging.c
			System.out.println(e.getMessage());
		}
	}

	// this method reads the songs from the song file.
	public void loadLibrary() throws IOException {
		// create buffered reader
		BufferedReader in = new BufferedReader(
				new FileReader(
						"/Users/Tim/Documents/cs112/Project4/songs/songs.txt"));
		// /buddered reader.read line

		String data = in.readLine();

		// for loop
		for (int i = 0; i < Integer.parseInt(data); i++) {
			String title = in.readLine();
			String artist = in.readLine();
			String album = in.readLine();
			String path = in.readLine();
			String blank = in.readLine();
			Mp3 song = new Mp3(title, artist, album, path);
			songs.add(song);
			System.out.println("added " + title + " to arraylist.");
		}

	}

	// this method sorts the songs alphabetically by title.
	public void sortSongs() {

		for (int i = 0; i < songs.size() - 1; i++) { // start of i loop.
			// used to set the found min.
			// then set i = currentMin when found
			Mp3 jj, ii;
			ii = songs.get(i);
			
			for (int j = i + 1; j < songs.size() ; j++) { // start of j loop.
				
				int result = songs.get(i).compareTo(songs.get(j));

				if (result > 0) // swap
																// objects.
				{
					jj = songs.get(j);

					songs.set(i, jj);
					songs.set(j, ii);
					
					
					int confirmation = songs.get( i ).compareTo(songs.get( j ) );

					if ( confirmation <= result ){
						sortSongs();
					}
					else
					{
						break;
					}
				

				}

				
			} // end of for loop with j.

		} // end of for loop with i.
		System.out.println(" The songs arraylist is now: "); // debug code.

		

		for (int k = 0; k < songs.size(); k++) {
			System.out.println("Song i = " + songs.get(k).getTitle());
		}
	} // end of sortSongs().

	// this method plays the songs.

	public void playSongs( int selectedRow )

	{
		Mp3 selected = songs.get( selectedRow );
		
		 play = new Play( selected.getPath()  );

	}
	
	public void stopSong( boolean buttonClicked )
	{
		play.stop();
	}
}

