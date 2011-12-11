package ch.boxi.pictureStatistic.data;

import java.util.Vector;

public enum ZAxis {
	    Objectiv("objectiv", "Objektive")
	  , Folder("folder", "Verzeichnisse")
	  , Camera("camera", "Kameras")
	  , None(null, "Keine");
	    
	    private String fieldName;
	    private String title;
	    
	    private ZAxis(String fieldName, String title){
	    	this.fieldName = fieldName;
	    	this.title = title;
	    }
	    
	    public String getFieldName(){
	    	return fieldName;
	    }
	    
	    public String getTitle(){
	    	return title;
	    }
	    
	    public static ZAxis getByTitle(String title){
	    	if(title.equalsIgnoreCase("Objektive")){
	    		return Objectiv;
	    	} else if(title.equalsIgnoreCase("Verzeichnisse")){
	    		return Folder;
	    	} else if(title.equalsIgnoreCase("Kameras")){
	    		return Camera;
	    	} else if(title.equalsIgnoreCase("Keine")){
	    		return None;
	    	} else{
	    		return None;
	    	}
	    }
	    
	    public static Vector<String> getTitles(){
	    	Vector<String> titles = new Vector<String>();
	    	titles.add(Objectiv.getTitle());
	    	titles.add(Folder.getTitle());
	    	titles.add(Camera.getTitle());
	    	titles.add(None.getTitle());
	    	
	    	return titles;
	    }
}
