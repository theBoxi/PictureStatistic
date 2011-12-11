package ch.boxi.pictureStatistic.loader.exifTagReader;

import java.util.HashMap;
import java.util.Map;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.CameraModelReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.DateReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.FNumberReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.FocalLengthReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.IsoReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.ObjectivMaxFocusReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.ObjectivMinFocusReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.ProgrammReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.ShutterSpeedReader;
import ch.boxi.pictureStatistic.loader.exifTagReader.readers.WhiteBalanceReader;

public class TagRaderManager {
	private static Map<String, ExifTagReader> readers = new HashMap<String, ExifTagReader>();
	
	static{
		ExifTagReader camReader = new CameraModelReader();
		ExifTagReader focalLengthReader = new FocalLengthReader();
		ExifTagReader objectivMinReader = new ObjectivMinFocusReader();
		ExifTagReader objectivMaxReader = new ObjectivMaxFocusReader();
		ExifTagReader fNumberReader = new FNumberReader();
		ExifTagReader programmReader = new ProgrammReader();
		ExifTagReader isoReader = new IsoReader();
		ExifTagReader shutterSpeedReader = new ShutterSpeedReader();
		ExifTagReader whiteBalanceReader = new WhiteBalanceReader();
		ExifTagReader dateReader = new DateReader();
		
		readers.put(camReader.getTagName(), camReader);
		readers.put(focalLengthReader.getTagName(), focalLengthReader);
		readers.put(objectivMinReader.getTagName(), objectivMinReader);
		readers.put(objectivMaxReader.getTagName(), objectivMaxReader);
		readers.put(fNumberReader.getTagName(), fNumberReader);
		readers.put(programmReader.getTagName(), programmReader);
		readers.put(isoReader.getTagName(), isoReader);
		readers.put(shutterSpeedReader.getTagName(), shutterSpeedReader);
		readers.put(whiteBalanceReader.getTagName(), whiteBalanceReader);
		readers.put(dateReader.getTagName(), dateReader);
	}
	
	public static void readTag(Tag tag, FileData data) throws MetadataException{
		ExifTagReader reader = readers.get(tag.getTagName());
		if(reader != null){
			reader.readTag(tag, data);
		}
	}
}
