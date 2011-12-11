package ch.boxi.pictureStatistic.data;

import java.util.Collection;
import java.util.Vector;

import org.jfree.data.xy.XYDataset;

public interface DataMemory {
	public Vector<String> getObjectives();	
	public Vector<String> getFolders();
	public Vector<String> getCameras();
	public XYDataset getData();
	public void setConstraints(Collection<Constraint> constraints);
	public Collection<Constraint> getConstraints();
	public void setZAxis(ZAxis axis);
	public ZAxis getZAxis();
	public int getRoundTo();
	public void setRoundTo(int roundTo);
}
