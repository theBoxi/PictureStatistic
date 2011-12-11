package ch.boxi.pictureStatistic.loader;

public class Objectiv {
	private int fromZoom;
	private int toZoom;
	
	public Objectiv(){
		super();
	}
	
	@Override
	public String toString(){
		return fromZoom + "-" + toZoom;
	}

	public int getFromZoom() {
		return fromZoom;
	}

	public void setFromZoom(int fromZoom) {
		this.fromZoom = fromZoom;
	}

	public int getToZoom() {
		return toZoom;
	}

	public void setToZoom(int toZoom) {
		this.toZoom = toZoom;
	}
	
	@Override
	public boolean equals(Object ob){
		try{
			Objectiv o = (Objectiv) ob;
			return o.fromZoom == fromZoom 
				&& o.toZoom == toZoom;
		} catch (ClassCastException e) {
			return false;
		}
		
	}
}
