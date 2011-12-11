package ch.boxi.pictureStatistic.loader.exifTagReader.readers;

import java.util.StringTokenizer;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.exifTagReader.ExifTagReader;

public class FocalLengthReader implements ExifTagReader {
	
	private static final String tagName = "Focal Length";
	
	@Override
	public boolean canReadTag(Tag tag) {
		try{
			String desc = tag.getDescription();
			desc = desc.replace("mm", "");
			desc = desc.replace(" ", "");
			StringTokenizer st = new StringTokenizer(desc, ".");
			desc = st.nextToken();
			Integer.parseInt(desc);
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
		String desc = tag.getDescription();
		desc = desc.replace("mm", "");
		desc = desc.replace(" ", "");
		StringTokenizer st = new StringTokenizer(desc, ".");
		desc = st.nextToken();
		int focalLength = Integer.parseInt(desc);
		data.setBrennweite(focalLength);
	}

}
