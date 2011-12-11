package ch.boxi.pictureStatistic.loader.exifTagReader.readers;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.exifTagReader.ExifTagReader;

public class IsoReader implements ExifTagReader {

	public static final String tagName = "ISO Speed Ratings";
	@Override
	public boolean canReadTag(Tag tag) {
		try{
			String isoStr = tag.getDescription();
			Integer.parseInt(isoStr);
			return true;
		} catch(Exception e){
			return false;
		}
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	@Override
	public void readTag(Tag tag, FileData data) throws MetadataException {
		String isoStr = tag.getDescription();
		int iso = Integer.parseInt(isoStr);
		data.setIso(iso);
	}

}
