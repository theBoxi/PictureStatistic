package ch.boxi.pictureStatistic.GUI;

import javax.swing.JFrame;

import ch.boxi.pictureStatistic.loader.Worker;

public class AfterLoadWorker implements Worker {
	
	private ReloadMediator mediator;
	private JFrame frameTohide;
	
	public AfterLoadWorker(ReloadMediator mediator, JFrame frameToHide){
		this.mediator = mediator;
		this.frameTohide = frameToHide;
	}
	
	public void doIt(){
		mediator.reload();
		frameTohide.setVisible(false);
	}
}
