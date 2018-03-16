package lectorbdf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

	public Connection Connect;

	public MySql() {
		
	}
	
	public Connection getConnection() throws Exception{
		
        try{ 
           String url = "jdbc:mysql://localhost:8889/comandas?";
           String username = "user=root&";
           String password = "password=root";
 
           System.out.println("Loading driver...");

           try {
               Class.forName("com.mysql.jdbc.Driver");
               System.out.println("Driver loaded!");
           } catch (ClassNotFoundException e) {
               throw new IllegalStateException("Cannot find the driver in the classpath!", e);
           }
          
           System.out.println("Connecting database...");
           try (Connection connection = DriverManager.getConnection(url + username + password)) {
               System.out.println("Database connected!");
           } catch (SQLException e) {
	        	   System.out.println("SQLException: " + e.getMessage());
               throw new IllegalStateException("Cannot connect the database!", e);
           }

        }catch (Exception e ){System.out.println(e);}
        return null;
 }

}
