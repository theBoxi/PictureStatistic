package ch.boxi.pictureStatistic.loader.exifTagReader.readers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.exifTagReader.ExifTagReader;

public class DateReader implements ExifTagReader {
	
	private static final String tagName = "Date/Time";
	@Override
	public boolean canReadTag(Tag tag) {
		DateFormat df = new SimpleDateFormat();
		try {
			df.parse(tag.getDescription());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	@Override
	public void readTag(Tag tag, FileData data) throws MetadataException {
		//2006:05:25 18:16:30
		DateFormat df = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
		try {
			Date d = df.parse(tag.getDescription());
			data.setD(d);
		} catch (ParseException e) {
			throw new MetadataException("Can not parse Date", e);
		}
	}

}
