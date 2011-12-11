package ch.boxi.pictureStatistic.loader;

public class Camera {
	private String name;
	
	public Camera(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}

	public Camera(){
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o){
		try{
			Camera c = (Camera) o;
			return c.getName().equalsIgnoreCase(name);
		} catch (ClassCastException e) {
			return false;
		}
	}
}
