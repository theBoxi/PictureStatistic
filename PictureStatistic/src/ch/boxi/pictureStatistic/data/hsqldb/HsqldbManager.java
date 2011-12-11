package ch.boxi.pictureStatistic.data.hsqldb;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import ch.boxi.pictureStatistic.data.Constraint;
import ch.boxi.pictureStatistic.data.SqlManager;
import ch.boxi.pictureStatistic.data.ZAxis;
import ch.boxi.pictureStatistic.loader.FileData;

public class HsqldbManager extends SqlManager{
	
	public HsqldbManager() throws ClassNotFoundException, SQLException{
		super("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:aname", "sa", "");
	}
	
	@Override
	protected void createTables() throws SQLException {
		Statement s = getStatement();
		
		s.execute("CREATE SEQUENCE theSeq");
		s.execute("CREATE TABLE picData (" +
					"id INT PRIMARY KEY," +
					"objectiv VARCHAR," +
					"camera VARCHAR," +
					"f_number VARCHAR," +
					"focalLength INT," +
					"program VARCHAR," +
					"iso INT," +
					"shutterSpeed VARCHAR," +
					"whiteBalance VARCHAR," +
					"folder VARCHAR," +
					"file VARCHAR," +
					"picDate DATE)");
		s.close();
	}

	@Override
	public void addData(FileData data) {
		String sql = "INSERT INTO picData (id,objectiv,camera,f_number,focalLength," +
			"program,iso,shutterSpeed,whiteBalance,folder,file,picDate)" +
			"VALUES (NEXT VALUE FOR theSeq," +
			"'" + data.getO() + "'," +
			"'" + data.getC() + "'," +
			"'" + data.getBlende() + "'," +
			      data.getBrennweite() + "," +
			"'" + data.getProgram() + "'," +
			      data.getIso() + "," +
			"'" + data.getShutterSpeed() + "'," +
			"'" + data.getWeissabgleich() + "'," +
			"'" + data.getF().getParentFile() + "'," +
			"'" + data.getF() + "'," +
			"'" + new Date(data.getD().getTime()) + "')";
		try {
			Statement s = getStatement();
			s.execute(sql);
		} catch (SQLException e) {
			System.err.println("can't write to DB");
			System.err.println("sql: " + sql);
			e.printStackTrace();
		}	
	}
	
	public Vector<String> getObjectives(){
		String sql = "SELECT objectiv from picData GROUP BY objectiv ORDER BY objectiv ASC";
		Vector<String> objectives = new Vector<String>();
		try{
			Statement s = getStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				String obj = rs.getString("objectiv");
				objectives.add(obj);
			}
		}catch(SQLException e){
			System.err.println("Can't read Objectives (sql: " + sql + ")");
			e.printStackTrace();
		}
		return objectives;
	}
	
	public Vector<String> getFolders(){
		String sql = "SELECT folder from picData GROUP BY folder ORDER BY folder ASC";
		Vector<String> folders = new Vector<String>();
		try{
			Statement s = getStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				String folder = rs.getString("folder");
				folders.add(folder);
			}
		}catch(SQLException e){
			System.err.println("Can't read folders (sql: " + sql + ")");
			e.printStackTrace();
		}
		return folders;
	}
	
	public Vector<String> getCameras(){
		String sql = "SELECT camera from picData GROUP BY camera";
		Vector<String> cameras = new Vector<String>();
		try{
			Statement s = getStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				String camera = rs.getString("camera");
				cameras.add(camera);
			}
		}catch(SQLException e){
			System.err.println("Can't read cameras (sql: " + sql + ")");
			e.printStackTrace();
		}
		return cameras;
	}
	
	public Vector<String> getAxisElements(ZAxis axis){
		if(axis == ZAxis.Camera){
			return getCameras();
		} else if(axis == ZAxis.Folder){
			return getFolders();
		} else if(axis == ZAxis.Objectiv){
			return getObjectives();
		} else{ //axis == ZAxis.None
			return null;
		}
	}
	
	public XYDataset getData(){
		Vector<String> axisElements = getAxisElements(zAxis);
		DefaultXYDataset data = new DefaultXYDataset();
		try{
			if(zAxis == ZAxis.None){
				addSqlLineToDataSet(data, "", "alle");
			} else{
				for(String axisElement: axisElements){
					if(isAxisElementIncludetInConstraints(zAxis.getFieldName(), axisElement)){
						addSqlLineToDataSet(data, zAxis.getFieldName() + " = '" + axisElement + "' ", axisElement);
					}
				}
			}
		} catch(SQLException e){
			System.err.println("Can't read (generate) data for JFreeChart");
			e.printStackTrace();
		}
		return data;
	}
	
	private void addSqlLineToDataSet(DefaultXYDataset data, String mainConstraint, String lineName) throws SQLException{
		boolean firstConstraint = true;
		String sql = "SELECT ROUND(focalLength/" + roundTo + ",1)*" + roundTo + " AS xAxis, COUNT(focalLength) AS yAxis FROM picData ";
		if( !mainConstraint.equals("") || areThereSomeConstraints()){
			sql += "WHERE " + mainConstraint + " ";
		}
		for(Constraint con: constraints){
			if(con.hasAConstraint()){
				if(firstConstraint && mainConstraint == ""){
					sql += con.getSqlConstraint() + " ";
					firstConstraint = false;
				} else{
					sql += "AND " + con.getSqlConstraint() + " ";
				}
			}
		}
		sql += "GROUP BY xAxis ORDER BY xAxis";
		Map<Integer, Integer> values = new TreeMap<Integer, Integer>();
		Statement s = getStatement();
		ResultSet rs = s.executeQuery(sql);
		while(rs.next()){
			Integer focalLength = rs.getInt("xAxis");
			int count = rs.getInt("yAxis");
			values.put(focalLength, count);
		}
		Set<Integer> focalLengths = values.keySet();
		double[][] dataValues = new double[2][focalLengths.size()];
		int counter = 0;
		for(Integer focalLength: focalLengths){
			dataValues[0][counter] = focalLength.doubleValue();
			dataValues[1][counter] = values.get(focalLength).doubleValue();
			counter++;
		}
		data.addSeries(lineName, dataValues);
	}
	
	private boolean isAxisElementIncludetInConstraints(String fieldName, String axisElement){
		for(Constraint con: constraints){
			if(con.hasAConstraint()){
				if(con.getFieldName().equalsIgnoreCase(fieldName)){
					return con.getIsInList().contains(axisElement);
				}
			}
		}
		return true;
	}
	
	private boolean areThereSomeConstraints(){
		for(Constraint con: constraints){
			if(con.hasAConstraint()){
				return true;
			}
		}
		return false;
	}
}
