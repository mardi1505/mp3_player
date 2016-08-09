import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Play {

	PlayerThread currThread;
	
	Play( String song)
	{
		currThread = new PlayerThread( song ); 
		currThread.start();
	}

	public void stop( )
	{
		currThread.close();
	}
	
	
	class PlayerThread extends Thread 
	{ 
		Player pl;
	PlayerThread(String filename) 
	{
	FileInputStream file; try 
	{
	// filename here contains mp3 you want to play 
		file = new FileInputStream(filename);
				
		pl = new Player( file );
	} 
		catch (FileNotFoundException e) 
	{ 
		e.getMessage();
	}
		catch (JavaLayerException e) 
	{
	          e.getMessage();
	}
	}
	public void close( )
	{
		pl.close();
	}
	
	
	public void run() 
	{
		try 
		{ 
			pl.play();
		}

		
	catch(Exception e) 
	{
	    e.getMessage();
	}
	} 
	}
}
