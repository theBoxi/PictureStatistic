package ch.boxi.pictureStatistic.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import ch.boxi.pictureStatistic.loader.notification.ObservableNotificator;
import ch.boxi.pictureStatistic.loader.notification.ObservableNotificatorMsg;

public class LoaderGUI extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private JProgressBar progressBar;
	private JLabel progressText;
	private int fileCounter = 0;
	private int maxFileCount = 0;
	
	public LoaderGUI(ObservableNotificator noti){
		noti.addObserver(this);
		init();
	}
	
	private void init(){
		setLayout(new GridBagLayout());
		GridBagConstraints g;
		
		progressBar = new JProgressBar();
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 0;
		g.weightx = 1.0;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.CENTER;
		g.insets = new Insets(50, 50, 10, 50);
		add(progressBar, g);
		
		progressText = new JLabel("Vortschritt");
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 1;
		g.weightx = 1.0;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.CENTER;
		g.insets = new Insets(0, 50, 50, 50);
		add(progressText, g);
	}
	
	@Override
	public void update(Observable arg0, Object msgObj) {
		ObservableNotificatorMsg msg = (ObservableNotificatorMsg) msgObj;
		if(msg.action == ObservableNotificatorMsg.Action.FileCount){
			progressBar.setMinimum(0);
			progressBar.setMaximum(msg.fileCount);
			progressBar.setValue(0);
			progressText.setText("0 von " + msg.fileCount);
			maxFileCount = msg.fileCount;
		} else if(msg.action == ObservableNotificatorMsg.Action.BeginReadFile){
			fileCounter++;
			progressBar.setValue(fileCounter);
			progressText.setText(fileCounter + " von " + maxFileCount);
		}

	}

}
