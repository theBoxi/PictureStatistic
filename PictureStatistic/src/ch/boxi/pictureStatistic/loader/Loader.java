package ch.boxi.pictureStatistic.loader;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

import ch.boxi.pictureStatistic.data.writeableDataMemory;
import ch.boxi.pictureStatistic.loader.exifTagReader.TagRaderManager;
import ch.boxi.pictureStatistic.loader.notification.ConsoleNotificator;
import ch.boxi.pictureStatistic.loader.notification.LoaderNotificator;

public class Loader implements Runnable{
	private Collection<File> filesToLoad = new LinkedList<File>();
	private Collection<File> rootDirs;
	private writeableDataMemory memory;
	private LoaderNotificator noti = new ConsoleNotificator();
	private Worker alfterLoadWorker;
	
	public Loader(Collection<File> rootDirs, writeableDataMemory memory, LoaderNotificator noti){
		this.rootDirs = rootDirs;
		this.memory = memory;
		if(noti != null){
			this.noti = noti;
		}
	}
	
	public int countFiles(){
		for(File f: rootDirs){
			addJpgs(f);
		}
		noti.setFileCount(filesToLoad.size());
		return filesToLoad.size();
	}
	
	private void addJpgs(File f){
		if(f.isDirectory()){
			for(File subFile: f.listFiles()){
				addJpgs(subFile);
			}
		} else{
			String fileName = f.getName();
			int indexOfPoint = fileName.lastIndexOf(".");
			if(indexOfPoint > 0){
				String fileEnd = fileName.substring(indexOfPoint, fileName.length());
				if(fileEnd.equalsIgnoreCase(".jpg")){
					filesToLoad.add(f);
				}
			}
		}
	}
	
	public void loadFiles(Worker afterLoadWorker){
		this.alfterLoadWorker = afterLoadWorker;
		Thread t = new Thread(this);
		t.start();
	}
	
	private FileData loadFile(File f) throws JpegProcessingException, MetadataException{
		Metadata metadata = JpegMetadataReader.readMetadata(f);
		
		FileData data = new FileData();
		data.setFile(f);
		
		Iterator directories = metadata.getDirectoryIterator(); 
		while (directories.hasNext()) { 
			Directory directory = (Directory)directories.next(); 
			Iterator tags = directory.getTagIterator(); 
			while (tags.hasNext()) { 
				Tag tag = (Tag)tags.next(); 
				TagRaderManager.readTag(tag, data);
			}
		}
		
		return data;
	}

	@Override
	public void run() {
		Collection<Camera> foundCams = new LinkedList<Camera>();
		Collection<Objectiv> foundObjectivs = new LinkedList<Objectiv>();
		
		for(File f: filesToLoad){
			noti.beginToLoadFile(f);
			FileData data;
			try {
				data = loadFile(f);
				memory.addData(data);
				
				if( !foundCams.contains(data.getC())){
					noti.cameraFound(data.getC());
				}
				if( !foundObjectivs.contains(data.getO())){
					noti.objectivFound(data.getO());
				}
			} catch (Exception e) {
				System.err.println("couldn't read file: " + f);
			}
		}
		alfterLoadWorker.doIt();
	}
}
