package ch.boxi.pictureStatistic.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;

import ch.boxi.pictureStatistic.data.DataMemory;

public class MyChartPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static JFreeChart chart;
	private DataMemory memory;
	private ChartPanel chartPanel;
	private JPanel sumPanel;
	private XYDataset data;
	
	public MyChartPanel(DataMemory memory){
		this.memory = memory;
		init();
	}
	
	private void init(){
		loadNewJFreeChart();		
		chartPanel = new ChartPanel(chart);
		setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.BOTH;
		g.weightx = 1;
		g.weighty = 1;
		add(chartPanel, g);
		
		sumPanel = new JPanel();
		sumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.weightx = 1;
		add(sumPanel, g);
	}
	
	private void loadNewJFreeChart(){
		data = memory.getData();
		ValueAxis catAxis = new NumberAxis("Brennweite [mm]");
		ValueAxis valueAxis = new NumberAxis("Anzahl");
		XYItemRenderer rederer = new DefaultXYItemRenderer();
		
		XYPlot plot = new XYPlot(data, catAxis, valueAxis, rederer);
		
		chart = new JFreeChart(plot);
	}
	
	public void repaintChart(){
		loadNewJFreeChart();
		reloadSumPanel();
		chartPanel.setChart(chart);
	}
	
	public void reloadSumPanel(){
		sumPanel.removeAll();
		int seriesCount = data.getSeriesCount();
		int countSum = 0;
		for(int serieIndex = 0; serieIndex < seriesCount; serieIndex++){
			String serieName = data.getSeriesKey(serieIndex).toString();
			int count = 0;
			int itemCount = data.getItemCount(serieIndex);
			for(int itemIndex = 0; itemIndex < itemCount; itemIndex++){
				count += data.getYValue(serieIndex, itemIndex);
			}
			countSum += count;
			JLabel serieNameLbl = new JLabel(serieName + ": ");
			Font f = serieNameLbl.getFont().deriveFont(Font.BOLD);
			serieNameLbl.setFont(f);
			sumPanel.add(serieNameLbl);
			JLabel serieCount = new JLabel(Integer.toString(count));
			sumPanel.add(serieCount);
		}
		JLabel serieNameLbl = new JLabel("Summe: ");
		Font f = serieNameLbl.getFont().deriveFont(Font.BOLD);
		serieNameLbl.setFont(f);
		sumPanel.add(serieNameLbl);
		JLabel serieCount = new JLabel(Integer.toString(countSum));
		sumPanel.add(serieCount);
		sumPanel.validate();
		sumPanel.repaint();
		validate();
		repaint();
	}
	
	
}
