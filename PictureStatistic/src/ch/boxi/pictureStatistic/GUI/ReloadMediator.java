package ch.boxi.pictureStatistic.GUI;

public class ReloadMediator {

	private MyChartPanel chartPanel;
	private DataLimiterPanel dataLimiterPanel;
	
	public ReloadMediator(){
	}
	
	public ReloadMediator(MyChartPanel chartPanel,
			DataLimiterPanel dataLimiterPanel) {
		super();
		this.chartPanel = chartPanel;
		this.dataLimiterPanel = dataLimiterPanel;
	}
	
	public void reload(){
		chartPanel.repaintChart();
		dataLimiterPanel.reload();
	}

	public void setChartPanel(MyChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public void setDataLimiterPanel(DataLimiterPanel dataLimiterPanel) {
		this.dataLimiterPanel = dataLimiterPanel;
	}
}
