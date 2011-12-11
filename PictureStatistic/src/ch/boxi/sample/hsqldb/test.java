package ch.boxi.sample.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.Server;
import org.hsqldb.ServerConfiguration;
import org.hsqldb.persist.HsqlProperties;

public class test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		//startServer();
		Class.forName("org.hsqldb.jdbcDriver");
		
		//Connection c = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "sa", "");
		//InMemory
		Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		Statement s = c.createStatement();
		s.execute("CREATE SEQUENCE test_seq");
		s.execute("CREATE TABLE test (myID INT PRIMARY KEY, Name VARCHAR);");
		s.execute("INSERT INTO Test (myID, Name) VALUES (NEXT VALUE FOR test_seq,'Test');");
		ResultSet res = s.executeQuery("SELECT * FROM test");
		while(res.next()){
			System.out.println("Id:\t" + res.getInt("myID"));
			System.out.println("Name:\t" + res.getString("Name"));
		}
		s.close();
		c.close();
	}
	
	private static void startServer(){
		String[] options = new String[] { "-database.0 file:./test/db/VendorMgt", "-dbname.0", "VendorMgt" };
		HsqlProperties hsqlproperties = HsqlProperties.argArrayToProps(options, "server");
		ServerConfiguration.translateDefaultDatabaseProperty(hsqlproperties);
		ServerConfiguration.translateDefaultNoSystemExitProperty(hsqlproperties);
		Server server = new Server();
		server.setProperties(hsqlproperties);
		server.start();
	}

}
