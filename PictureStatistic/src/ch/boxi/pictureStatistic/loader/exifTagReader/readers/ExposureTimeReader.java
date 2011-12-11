package ch.boxi.pictureStatistic.loader.exifTagReader.readers;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.exifTagReader.ExifTagReader;

public class ExposureTimeReader implements ExifTagReader {

	private static final String tagName = "Exposure Time";
	
	@Override
	public boolean canReadTag(Tag tag) {
		try{
			tag.getDescription();
			return true;
		} catch(MetadataException e){
			return false;
		}
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	@Override
	public void readTag(Tag tag, FileData data) throws MetadataException {
		String speed = tag.getDescription();
		data.setShutterSpeed(speed);
	}

}
