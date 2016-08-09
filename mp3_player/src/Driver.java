import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Driver extends JPanel {
	
	private JButton playButton, stopButton, exitButton, loadMp3Button, saveButton, openButton;
	private JTable table = null;
	Library library = new Library();
	boolean buttonClicked = false;




	public static void main(String[] args) {
		JFrame frame = new JFrame ("Mp3 player");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		Driver panel  = new Driver();
		panel.setPreferredSize(new Dimension(400,400));
		frame.getContentPane().add (panel);


		frame.pack();
		frame.setVisible(true);
	}


	Driver() {
	
	this.setLayout(new BorderLayout());
	
	// buttonPanelTop contains the top row of buttons:
	// load mp3 files, save and open
	JPanel buttonPanelTop = new JPanel();
	buttonPanelTop.setLayout(new GridLayout(1,3));
	loadMp3Button = new JButton("Load mp3");
	saveButton = new JButton("Save Library");
	openButton = new JButton("Load Library");

	loadMp3Button.addActionListener(new MyButtonListener());
	saveButton.addActionListener(new MyButtonListener());
	openButton.addActionListener(new MyButtonListener());

	buttonPanelTop.add(loadMp3Button);
	
	buttonPanelTop.add(saveButton);
	buttonPanelTop.add(openButton);
	this.add(buttonPanelTop, BorderLayout.NORTH);
		
	
	// Bottom panel with panels: Play, Stop, Exit buttons
	JPanel buttonPanelBottom = new JPanel();
	buttonPanelBottom.setLayout(new GridLayout(1,3));
	playButton = new JButton("Play");
	stopButton = new JButton("Stop");
	exitButton = new JButton("Exit");

	playButton.addActionListener(new MyButtonListener());
	stopButton.addActionListener(new MyButtonListener());
	exitButton.addActionListener(new MyButtonListener());

	buttonPanelBottom.add(playButton);
	buttonPanelBottom.add(stopButton);
	buttonPanelBottom.add(exitButton);
	this.add(buttonPanelBottom, BorderLayout.SOUTH);
	
}
	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// The function that does something whenever each
			// button is pressed
	
			if (e.getSource() == loadMp3Button) 
			{
				//debugging.
				System.out.println("Load mp3 button");
				
				
				library.loadLibrary(new File("/Users/Tim/Documents/cs112/Project4/mp3"));
				library.sortSongs();
				updateUI();

				// FILL IN CODE - make the calls to other methods/classes, do NOT place all the code here
				
				// 1) read all the mp3 files from mp3 directory
				// 2) extract tags and load songs into the array of songs
				// 3) Create a JTable from the songs and add it to the panel
				// Replace the code below with the actual song data: 
				// right now it is hard coded and just demonstrates how to use JTable
				String[][] tableElems = new String[10][2];
				String[] columnNames = {"Title", "Artist"};
				
				tableElems[0][0] = library.getSongs().get(0).getTitle();
				tableElems[0][1] = library.getSongs().get(0).getArtist();
				tableElems[1][0] = library.getSongs().get(1).getTitle();
				tableElems[1][1] = library.getSongs().get(1).getArtist();
				tableElems[2][0] = library.getSongs().get(2).getTitle();
				tableElems[2][1] = library.getSongs().get(2).getArtist();
				
				tableElems[3][0] = library.getSongs().get(3).getTitle();
				tableElems[3][1] = library.getSongs().get(3).getArtist();
				tableElems[4][0] = library.getSongs().get(4).getTitle();
				tableElems[4][1] = library.getSongs().get(4).getArtist();
				tableElems[5][0] = library.getSongs().get(5).getTitle();
				tableElems[5][1] = library.getSongs().get(5).getArtist();
				tableElems[6][0] = library.getSongs().get(6).getTitle();
				tableElems[6][1] = library.getSongs().get(6).getArtist();
				tableElems[7][0] = library.getSongs().get(7).getTitle();
				tableElems[7][1] = library.getSongs().get(7).getArtist();
				
				tableElems[8][0] = library.getSongs().get(8).getTitle();
				tableElems[8][1] = library.getSongs().get(8).getArtist();
				tableElems[9][0] = library.getSongs().get(9).getTitle();
				tableElems[9][1] = library.getSongs().get(9).getArtist();
				
				// you do not need to change the code below
				table = new JTable(tableElems, columnNames );
				JScrollPane scrollPane = new JScrollPane( table );
				add( scrollPane, BorderLayout.CENTER );
				
				updateUI();
			
			}
			else if (e.getSource() == saveButton) {
				// FILL IN CODE :  make the calls to other methods/classes, do NOT place all the code here
				// save the song catalog into a file called "songCatalog". 
				// The format is described in the handout.
				
				updateUI();
				//Library object to call saving method.
				
				//saves the songs from the ArrayList songs.
				library.saving();
			}
			else if (e.getSource() == openButton) {
				// FILL IN  CODE:  make the calls to other methods/classes, do NOT place all the code here
				// open the file songCatalog and load songs
				// into the arraylist of songs
				
				
				
				try {
					library.loadLibrary();
					library.sortSongs();
					updateUI();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == playButton) {
					
				int selectedSong = table.getSelectedRow();
				
				library.playSongs( selectedSong );
			
				// FILL IN CODE: make the calls to other methods/classes, do NOT place all the code here
				// find the selected song in the arraylist of songs
				// create a thread to play it as described in the handout.
			}
			else if (e.getSource() == stopButton) {
				// FILL IN CODE: make the calls to other methods/classes, do NOT place all the code here
				// stop the current running thread
				
				boolean buttonClicked = true;
				
				library.stopSong( buttonClicked );
			}
			else if (e.getSource() == exitButton) {
				// Exit the program
				System.exit(0);
			}
		}
	}
}