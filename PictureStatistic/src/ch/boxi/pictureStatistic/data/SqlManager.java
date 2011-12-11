package ch.boxi.pictureStatistic.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;


public abstract class SqlManager implements writeableDataMemory, DataMemory{
	private Connection connection;
	private String jdbcDriverClassName;
	private String dbUrl;
	private String user;
	private String pass;
	
	protected Collection<Constraint> constraints = new LinkedList<Constraint>();
	protected ZAxis zAxis = ZAxis.Objectiv;
	protected int roundTo = 1;

	public SqlManager(String jdbcDriverClassName, String dbUrl, String user,
			String pass) throws ClassNotFoundException, SQLException {
		super();
		this.jdbcDriverClassName = jdbcDriverClassName;
		this.dbUrl = dbUrl;
		this.user = user;
		this.pass = pass;
		
		Class.forName(this.jdbcDriverClassName);
		connection = DriverManager.getConnection(this.dbUrl, this.user, this.pass);
		
		createTables();
	}
	
	public Statement getStatement() throws SQLException{
		return connection.createStatement();
	}
	
	public void setConstraints(Collection<Constraint> constraints){
		if(constraints != null){
			this.constraints = constraints;
		}
	}
	
	public Collection<Constraint> getConstraints(){
		return constraints;
	}
	
	public void setZAxis(ZAxis axis){
		zAxis = axis;
	}
	
	public ZAxis getZAxis(){
		return zAxis;
	}
	
	public int getRoundTo() {
		return roundTo;
	}

	public void setRoundTo(int roundTo) {
		this.roundTo = roundTo;
	}
	
	protected abstract void createTables() throws SQLException;
	 
}
