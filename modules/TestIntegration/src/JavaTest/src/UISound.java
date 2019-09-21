import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ambiance.AmbianceSonore;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class UISound extends JFrame implements Runnable{
	
	
	private AmbianceSonore mySound;
	
	private JButton search,play,stop;
	
	private JPanel panel1,panel2,panel;
	
	private boolean onPlay = false;
	
	private Thread t; 
	
	private PlaySound currentPlay;
	
	public UISound()
	{
		super ("Listen Songs");
		
		
	}
	
	public void initialise()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		this.setSize(500, 100);
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		this.setLayout(new GridLayout(2, 3));
		search = new JButton("Search");
		search.setSize(new Dimension(10,10));
		play = new JButton("Play");
		play.setSize(new Dimension(10,10));
		stop = new JButton("Stop");
		stop.setSize(new Dimension(10,10));
		
		panel.add(search);
		panel1.add(play);
		panel2.add(stop);
		
		this.add(panel);
		this.add(panel1);
		this.add(panel2);
		Search actionSearch = new Search();
		search.addActionListener(actionSearch);
		play.addActionListener(new Play());
		search.removeActionListener(actionSearch);
		this.setVisible(true);
	}
		
		private void createError1()
		{
			String string2 = "Erreur de Lecture";
			JOptionPane.showMessageDialog(this, "Aucun fichier n'a été ajouté",
					string2, JOptionPane.WARNING_MESSAGE);
		}
		
		
		private void addPath()
		{
			this.add(new JLabel(mySound.getPath()));
			this.validate();
		}
	
	
	
	public class Play implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (mySound==null)
			{
				createError1();
			}
			else
			{
				currentPlay = new PlaySound();
				t = new Thread(currentPlay);
				play.setEnabled(false);
				t.start();
				
			}
			
		}
		
		
	}
	
	public class Search implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int result = fc.showOpenDialog(null);
			if (result== JFileChooser.APPROVE_OPTION)
			{
				mySound = new AmbianceSonore(fc.getSelectedFile().getAbsolutePath());	
				addPath();
				onPlay = true;
			}
			
		}
		
	}
	
	public class Stop implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (t!=null)
			{
				currentPlay.stopThread();
				play.setEnabled(true);
			}
			
		}
		
	}
	
	
	
	public class PlaySound implements Runnable
	{
		private boolean isPlayed = false;
		private void play()
		{
		
		while (isPlayed)
		{
			
		try
		{
			
			File file = new File(mySound.getPath());
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			try
			{
				Player player = new Player(bis);
				player.play();
				
			}catch (JavaLayerException e )
			{
				System.out.println("can not open the file");
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		

		}
		}
		@Override
		public void run() {
			isPlayed = true;
			this.play();
			
		}
		public synchronized void stopThread()
		{
			this.isPlayed = false;
		}
		
	}
	
	

	@Override
	public void run() {
		initialise();
		
	}
	
	

}
