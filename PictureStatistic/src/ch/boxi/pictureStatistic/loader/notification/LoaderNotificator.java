package ch.boxi.pictureStatistic.loader.notification;

import java.io.File;

import ch.boxi.pictureStatistic.loader.Camera;
import ch.boxi.pictureStatistic.loader.Objectiv;

public interface LoaderNotificator {
	public void setFileCount(int count);
	public void objectivFound(Objectiv o);
	public void cameraFound(Camera c);
	public void beginToLoadFile(File f);
}
