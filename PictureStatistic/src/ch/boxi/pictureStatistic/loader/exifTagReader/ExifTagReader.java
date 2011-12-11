package ch.boxi.pictureStatistic.loader.exifTagReader;

import ch.boxi.pictureStatistic.loader.FileData;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

public interface ExifTagReader {
	public String getTagName();
	public void readTag(Tag tag, FileData data) throws MetadataException;
	public boolean canReadTag(Tag tag);
}
