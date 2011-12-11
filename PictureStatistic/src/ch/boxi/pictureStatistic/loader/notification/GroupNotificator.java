package ch.boxi.pictureStatistic.loader.notification;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import ch.boxi.pictureStatistic.loader.Camera;
import ch.boxi.pictureStatistic.loader.Objectiv;

public class GroupNotificator implements LoaderNotificator {

	private Collection<LoaderNotificator> notis = new LinkedList<LoaderNotificator>();
	
	public GroupNotificator(Collection<LoaderNotificator> notis){
		if(notis != null){
			this.notis = notis;
		}
	}
	
	public void add(LoaderNotificator noti){
		notis.add(noti);
	}
	
	@Override
	public void beginToLoadFile(File f) {
		for(LoaderNotificator noti: notis){
			noti.beginToLoadFile(f);
		}
	}

	@Override
	public void cameraFound(Camera c) {
		for(LoaderNotificator noti: notis){
			noti.cameraFound(c);
		}
	}

	@Override
	public void objectivFound(Objectiv o) {
		for(LoaderNotificator noti: notis){
			noti.objectivFound(o);
		}
	}

	@Override
	public void setFileCount(int count) {
		for(LoaderNotificator noti: notis){
			noti.setFileCount(count);
		}
	}

}
