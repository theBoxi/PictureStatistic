package ch.boxi.pictureStatistic.loader;

import java.io.File;
import java.util.Date;


public class FileData {
	private File f;
	private Objectiv o;
	private Camera c;
	private String blende;
	private int brennweite;
	private String program;
	private int iso;
	private String shutterSpeed;
	private String weissabgleich;
	private Date d;
	
	@Override
	public String toString(){
		return "File: " + f
				+ "\tObj.: " + o
				+ "\tCam.: " + c
				+ "\tf: " + blende
				+ "\tp: " + program
				+ "\tiso: " + iso
				+ "\tt: " + shutterSpeed
				+ "\tdate: " + d;
	}
	
	public File getFile() {
		return f;
	}
	public void setFile(File f) {
		this.f = f;
	}
	public Objectiv getO() {
		return o;
	}
	public void setO(Objectiv o) {
		this.o = o;
	}
	public Camera getC() {
		return c;
	}
	public void setC(Camera c) {
		this.c = c;
	}
	public String getBlende() {
		return blende;
	}
	public void setBlende(String blende) {
		this.blende = blende;
	}
	public int getBrennweite() {
		return brennweite;
	}
	public void setBrennweite(int brennweite) {
		this.brennweite = brennweite;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public File getF() {
		return f;
	}
	public void setF(File f) {
		this.f = f;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public int getIso() {
		return iso;
	}
	public void setIso(int iso) {
		this.iso = iso;
	}
	public String getShutterSpeed() {
		return shutterSpeed;
	}
	public void setShutterSpeed(String shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
	}
	public String getWeissabgleich() {
		return weissabgleich;
	}
	public void setWeissabgleich(String weissabgleich) {
		this.weissabgleich = weissabgleich;
	}
}
