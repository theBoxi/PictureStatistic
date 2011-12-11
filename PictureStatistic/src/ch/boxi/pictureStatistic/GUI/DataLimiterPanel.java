package ch.boxi.pictureStatistic.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ch.boxi.pictureStatistic.data.Constraint;
import ch.boxi.pictureStatistic.data.DataMemory;
import ch.boxi.pictureStatistic.data.ZAxis;

public class DataLimiterPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private DataMemory memory;
	private ReloadMediator mediator;
	private JLabel axisLabel;
	private JComboBox axisSelector;
	private JLabel roundLabel;
	private JCheckBox roundCheckBox;
	private JTextField roundFactor;
	private JLabel camLabel;
	private JList camList;
	private JLabel objectivLabel;
	private JList objectivList;
	private JButton repaintButton;
	private JLabel folderLabel;
	private JList folderList;
	
	public DataLimiterPanel(DataMemory memory, ReloadMediator mediator){
		this.mediator = mediator;
		this.memory = memory;
		init();
	}

	private void init() {		
		setLayout(new GridBagLayout());
		GridBagConstraints g;
		
		axisLabel = new JLabel("Achsen:");
		Font boldFont = axisLabel.getFont().deriveFont(Font.BOLD);
		axisLabel.setFont(boldFont);
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 0;
		g.gridwidth = 2;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(10, 10, 10, 10);
		add(axisLabel, g);
		
		axisSelector = new JComboBox(ZAxis.getTitles());
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 2;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 25, 10, 10);
		add(axisSelector, g);
		
		roundLabel = new JLabel("Runden:");
		roundLabel.setFont(boldFont);
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 2;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 10, 10, 10);
		add(roundLabel, g);
		
		roundCheckBox = new JCheckBox("Runden auf:", false);
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 3;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 25, 10, 10);
		add(roundCheckBox, g);
		roundCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	boolean selected = roundCheckBox.isSelected();
            	roundFactor.setEnabled(selected);
            	if(!selected){
            		roundFactor.setText("1");
            	}
            }	
		});
		
		roundFactor = new JTextField("1");
		roundFactor.setEnabled(false);
		g = new GridBagConstraints();
		g.gridx = 1;
		g.gridy = 3;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 0, 10, 10);
		add(roundFactor, g);
		
		camLabel = new JLabel("Kameras:");
		camLabel.setFont(boldFont);
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 4;
		g.gridwidth = 2;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 10, 10, 10);
		add(camLabel, g);
		
		camList = new JList(memory.getCameras());
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 5;
		g.gridwidth = 2;
		g.weightx = 1;
		g.weighty = 0.15;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 25, 10, 10);
		add(new JScrollPane(camList), g);
		
		objectivLabel = new JLabel("Objective:");
		objectivLabel.setFont(boldFont);
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 6;
		g.gridwidth = 2;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 10, 10, 10);
		add(objectivLabel, g);
		
		objectivList = new JList(memory.getObjectives());
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 7;
		g.gridwidth = 2;
		g.weightx = 1;
		g.weighty = 0.15;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 25, 10, 10);
		add(new JScrollPane(objectivList), g);
		
		folderLabel = new JLabel("Verzeichnisse:");
		folderLabel.setFont(boldFont);
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 8;
		g.gridwidth = 2;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 10, 10, 10);
		add(folderLabel, g);
		
		folderList = new JList(memory.getFolders());
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 9;
		g.gridwidth = 2;
		g.weightx = 1;
		g.weighty = 0.7;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.NORTHWEST;
		g.insets = new Insets(0, 25, 10, 10);
		add(new JScrollPane(folderList), g);
		
		repaintButton = new JButton("Chart erstellen");
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 10;
		g.gridwidth = 2;
		g.anchor = GridBagConstraints.SOUTHEAST;
		g.insets = new Insets(10, 10, 10, 10);
		add(repaintButton, g);
		repaintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	Object[] cams = camList.getSelectedValues();
            	Constraint camCon = new Constraint("camera");
            	for(Object camString: cams){
            		camCon.addInListItem((String) camString);
            	}
            	
            	Object[] objectives = objectivList.getSelectedValues();
            	Constraint objCon = new Constraint("objectiv");
            	for(Object objString: objectives){
            		objCon.addInListItem((String) objString);
            	}
            	
            	Object[] folders = folderList.getSelectedValues();
            	Constraint folderCon = new Constraint("folder");
            	for(Object folderString: folders){
            		folderCon.addInListItem((String) folderString);
            	}
            	
            	Collection<Constraint> constraints = new LinkedList<Constraint>();
            	constraints.add(camCon);
            	constraints.add(objCon);
            	constraints.add(folderCon);
            	
            	ZAxis axis = ZAxis.getByTitle( (String) axisSelector.getSelectedItem());
            	memory.setZAxis(axis);
            	memory.setRoundTo(Integer.parseInt(roundFactor.getText()));
            	memory.setConstraints(constraints);
            	
            	mediator.reload();
            }	
		});
	}
	
	public void reload(){
		camList.setListData(memory.getCameras());
		objectivList.setListData(memory.getObjectives());
		folderList.setListData(memory.getFolders());
		
		for(Constraint con: memory.getConstraints()){
			if(con.getFieldName().equals("camera")){
				Vector<String> cams = new Vector<String>(con.getIsInList());
				int[] camIndexesToSelect = new int[cams.size()];
				int pointer = 0;
				for(String cam: cams){
					int index = memory.getCameras().indexOf(cam);
					camIndexesToSelect[pointer++] = index;
				}
				camList.setSelectedIndices(camIndexesToSelect);
			} else if(con.getFieldName().equals("objectiv")){
				Vector<String> objs = new Vector<String>(con.getIsInList());
				int[] objIndexesToSelect = new int[objs.size()];
				int pointer = 0;
				for(String obj: objs){
					int index = memory.getObjectives().indexOf(obj);
					objIndexesToSelect[pointer++] = index;
				}
				objectivList.setSelectedIndices(objIndexesToSelect);
			} else if(con.getFieldName().equals("folder")){
				Vector<String> folders = new Vector<String>(con.getIsInList());
				int[] foldersIndexesToSelect = new int[folders.size()];
				int pointer = 0;
				for(String folder: folders){
					int index = memory.getFolders().indexOf(folder);
					foldersIndexesToSelect[pointer++] = index;
				}
				folderList.setSelectedIndices(foldersIndexesToSelect);
			}			
		}
	}
}
