package ch.boxi.pictureStatistic.loader.exifTagReader.readers;

import java.util.StringTokenizer;

import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.loader.FileData;
import ch.boxi.pictureStatistic.loader.Objectiv;
import ch.boxi.pictureStatistic.loader.exifTagReader.ExifTagReader;

public class ObjectivMinFocusReader implements ExifTagReader {

	private static final String tagName = "Short Focal Length";
	
	@Override
	public boolean canReadTag(Tag tag) {
		try{
			String desc = tag.getDescription();
			StringTokenizer st = new StringTokenizer(desc, " ");
			if(st.hasMoreTokens()){
				String focalLengthFloat = st.nextToken();
				StringTokenizer stInt = new StringTokenizer(focalLengthFloat, ".");
				if(stInt.hasMoreTokens()){
					String focalLength = stInt.nextToken();
					Integer.parseInt(focalLength);
					return true;
				}
			}
			return false;
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
		StringTokenizer st = new StringTokenizer(desc, " ");
		if(st.hasMoreTokens()){
			String focalLengthFloat = st.nextToken();
			StringTokenizer stInt = new StringTokenizer(focalLengthFloat, ".");
			int minFocalLength = 0;
			if(stInt.hasMoreTokens()){
				String focalLength = stInt.nextToken();
				minFocalLength = Integer.parseInt(focalLength);
			}
			Objectiv o = data.getO();
			if(o == null){
				o = new Objectiv();
			}
			o.setFromZoom(minFocalLength);
			data.setO(o);
		}
	}
}
