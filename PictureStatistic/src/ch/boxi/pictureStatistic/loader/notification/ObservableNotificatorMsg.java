package ch.boxi.pictureStatistic.loader.notification;

import java.io.File;

public class ObservableNotificatorMsg {
	public enum Action{BeginReadFile, CameraFound, ObjectivFound, FileCount};
	
	public Action action;
	public File file;
	public String camera;
	public String objectiv;
	public int fileCount;
}
