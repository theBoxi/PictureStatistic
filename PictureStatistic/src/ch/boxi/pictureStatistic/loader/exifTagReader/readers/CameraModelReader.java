package ch.boxi.pictureStatistic.loader.exifTagReader.readers;

import ch.boxi.pictureStatistic.loader.Camera;
import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.exifTagReader.ExifTagReader;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

public class CameraModelReader implements ExifTagReader {
	
	private static final String tagName = "Model";
	@Override
	public void readTag(Tag tag, FileData data) throws MetadataException {
		Camera cam = new Camera(tag.getDescription());
		data.setC(cam);
	}

	@Override
	public boolean canReadTag(Tag tag) {
		return tag.getTagName().equalsIgnoreCase(tagName);
	}

	@Override
	public String getTagName() {
		return tagName;
	}

}
