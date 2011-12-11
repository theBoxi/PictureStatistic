package ch.boxi.pictureStatistic.data.hsqldb;

public class FocalLengthDataComperator implements Comparable<FocalLengthDataComperator>{
	private Integer focalLength;
	
	public FocalLengthDataComperator(int focalLength){
		this.focalLength = focalLength;
	}
	
	@Override
	public String toString(){
		return focalLength + " mm";
	}

	@Override
	public int compareTo(FocalLengthDataComperator other) {
		int ret =focalLength.compareTo(other.focalLength); 
		return ret;
	}
}
