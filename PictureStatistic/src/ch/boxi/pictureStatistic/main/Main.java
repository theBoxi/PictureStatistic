package ch.boxi.pictureStatistic.main;

import javax.swing.UIManager;

import ch.boxi.pictureStatistic.GUI.MainFrame;

public class Main {
	public static void main(String[] args) throws Exception{
		
		try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	      System.out.println("Error setting native LAF: " + e);
	    }
		    
		MainFrame theFrame = new MainFrame();
		theFrame.setVisible(true);
	}
}
