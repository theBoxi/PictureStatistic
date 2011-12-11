package ch.boxi.pictureStatistic.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import ch.boxi.pictureStatistic.data.hsqldb.HsqldbManager;
import ch.boxi.pictureStatistic.loader.Loader;
import ch.boxi.pictureStatistic.loader.notification.ObservableNotificator;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 84111406317549274L;

	private HsqldbManager dbMgt;
	private ReloadMediator mediator = new ReloadMediator();
	private MyChartPanel chartPanel;
	private DataLimiterPanel dataLimiterPanel; 
	
	public MainFrame() throws ClassNotFoundException, SQLException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dbMgt = new HsqldbManager();
		createMenu();
		init();
		pack();
	}
	
	private void init(){
		setTitle("PictureStatistic By Boxi (www.boxi.ch)");
		dataLimiterPanel = new DataLimiterPanel(dbMgt, mediator);
		chartPanel = new MyChartPanel(dbMgt);
		
		JSplitPane splitt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chartPanel, dataLimiterPanel);
		splitt.setResizeWeight(1);
		
		add(splitt);
		
		mediator.setChartPanel(chartPanel);
		mediator.setDataLimiterPanel(dataLimiterPanel);
	}
	
	private void createMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu editMenu = new JMenu("Bearbeiten");
		JMenuItem addFolderItem = new JMenuItem("Ordner Hinzufügen");
		addFolderItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JFileChooser fc = new JFileChooser();
            	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            	fc.setMultiSelectionEnabled(true);
            	int state = fc.showOpenDialog(null);
            	
        		ObservableNotificator oNoti = new ObservableNotificator();
        		
        		LoaderGUI loaderGui = new LoaderGUI(oNoti);
        		JFrame loaderFrame = new JFrame();
        		loaderFrame.add(loaderGui);
        		loaderFrame.pack();
        		loaderFrame.setVisible(true);
        		
            	if ( state == JFileChooser.APPROVE_OPTION ){
					File[] dirs = fc.getSelectedFiles();
					Collection<File> files = new LinkedList<File>();
					for(File f: dirs){
						files.add(f);
					}
					Loader l = new Loader(files, dbMgt, oNoti);
					l.countFiles();
					l.loadFiles(new AfterLoadWorker(mediator, loaderFrame));
                } else
                	System.out.println( "Auswahl abgebrochen" );
            	}
            });
		editMenu.add(addFolderItem);
		
		menuBar.add(editMenu);
		
		setJMenuBar(menuBar);
	}
}
