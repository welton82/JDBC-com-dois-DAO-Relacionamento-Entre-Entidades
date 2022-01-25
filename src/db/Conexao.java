package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
	private static Connection cnn = null;
	
	public static Properties loadProperties() {
		Properties props = new Properties();
		try(FileInputStream fs = new FileInputStream("db.properties")){
			
			props.load(fs);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
		} 
		
		return props;
	}
	
	public static Connection getConnection() {
		
		try {
			Properties prop = loadProperties();
			String url = prop.getProperty("url");
			cnn = DriverManager.getConnection(url,prop);
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		return cnn;
	}
	
}
