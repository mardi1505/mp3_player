
public class Mp3 implements Comparable
{
	private String artist, title, album, path;
	
	public String getFileName()
	{
		return path;
	}

	public void setFileName(String fileName)
	{
		this.path = fileName;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getTags()
	{
		return album;
	}

	public void setTags(String tags)
	{
		this.album = tags;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Mp3(String artist, String title, String album, String path)
	{
		this.artist = artist;
		this.title = title;
		this.album = album;
		this.path = path;
	}

	public String getTitle()
	{
		// TODO Auto-generated method stub
		return title;
	}

	public String getAlbum()
	{
		// TODO Auto-generated method stub
		return album;
	}

	public String getPath()
	{
		// TODO Auto-generated method stub
		return path;
	}

	public int compareTo(Object o) 
	{
		Mp3 song = (Mp3)(o);
		String song1 = this.getTitle();
		String song2 = song.getTitle();
		int result = song1.compareTo(song2);
		return result;		
	}
}
