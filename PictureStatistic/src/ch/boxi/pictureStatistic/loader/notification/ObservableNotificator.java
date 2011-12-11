package ch.boxi.pictureStatistic.loader.notification;

import java.io.File;
import java.util.Observable;

import ch.boxi.pictureStatistic.loader.Camera;
import ch.boxi.pictureStatistic.loader.Objectiv;

public class ObservableNotificator extends Observable implements
		LoaderNotificator {

	@Override
	public void beginToLoadFile(File f) {
		ObservableNotificatorMsg msg = new ObservableNotificatorMsg();
		msg.action = ObservableNotificatorMsg.Action.BeginReadFile;
		msg.file = f;
		setChanged();
		notifyObservers(msg);
	}

	@Override
	public void cameraFound(Camera c) {
		ObservableNotificatorMsg msg = new ObservableNotificatorMsg();
		msg.action = ObservableNotificatorMsg.Action.CameraFound;
		msg.camera = c.toString();
		setChanged();
		notifyObservers(msg);
	}

	@Override
	public void objectivFound(Objectiv o) {
		ObservableNotificatorMsg msg = new ObservableNotificatorMsg();
		msg.action = ObservableNotificatorMsg.Action.ObjectivFound;
		msg.objectiv = o.toString();
		setChanged();
		notifyObservers(msg);
	}

	@Override
	public void setFileCount(int count) {
		ObservableNotificatorMsg msg = new ObservableNotificatorMsg();
		msg.action = ObservableNotificatorMsg.Action.FileCount;
		msg.fileCount = count;
		setChanged();
		notifyObservers(msg);
	}
}
