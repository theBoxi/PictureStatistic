package ch.boxi.sample.exif;
import java.io.File;
import java.util.Iterator;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class test {

	/**
	 * @param args
	 * @throws JpegProcessingException 
	 */
	public static void main(String[] args) throws JpegProcessingException {
		// TODO Auto-generated method stub
		File jpegFile = new File("F:\\BoxisHome\\Eigene Bilder\\2007\\3 Tägige Wanderung\\DSCN1502.JPG"); 
		Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
		
		Iterator directories = metadata.getDirectoryIterator(); 
		while (directories.hasNext()) { 
			Directory directory = (Directory)directories.next(); 
			// iterate through tags and print to System.out  
			Iterator tags = directory.getTagIterator(); 
			while (tags.hasNext()) { 
				Tag tag = (Tag)tags.next(); 
				// use Tag.toString()  
				System.out.println(tag);  
			}
		}
	}

}
