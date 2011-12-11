package ch.boxi.pictureStatistic.loader.notification;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import ch.boxi.pictureStatistic.loader.Camera;
import ch.boxi.pictureStatistic.loader.Objectiv;

public class ConsoleNotificator implements LoaderNotificator {

	private Collection<Camera> cams = new LinkedList<Camera>();
	private Collection<Objectiv> objsctivs = new LinkedList<Objectiv>();
	
	@Override
	public void beginToLoadFile(File f) {
		System.out.println("NOTI: read file " + f);
	}

	@Override
	public void cameraFound(Camera c) {
		if(!cams.contains(c)){
			System.out.println("NOTI: cam found " + c);
			cams.add(c);
		}
	}

	@Override
	public void objectivFound(Objectiv o) {
		if(!objsctivs.contains(o)){
			System.out.println("NOTI: Obj found " + o);
			objsctivs.add(o);
		}
	}

	@Override
	public void setFileCount(int count) {
		System.out.println("NOTI: " + count + " files found");
	}
}
