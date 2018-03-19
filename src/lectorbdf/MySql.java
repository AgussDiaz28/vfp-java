package lectorbdf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class MySql {

	public Connection Connect;

	public MySql() {
		try {
			this.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}
	
	public void addProductRecord(String nombre_producto,float categoria_id,float precio_unitario, double maxirest_id) throws Exception {
		
		Statement statement = null;
		
		try {
			nombre_producto = nombre_producto.trim();
			statement = this.Connect.createStatement();
			String select = ("SELECT precio_unitario FROM producto where maxirest_id = " + (int) maxirest_id );
			String insert = ("INSERT INTO producto (nombre,categoria_id,precio_unitario,maxirest_id) VALUES ('"+nombre_producto+"',"+ categoria_id +","+ precio_unitario +","+ maxirest_id+")");
			statement.execute(select);
			ResultSet rs = statement.getResultSet();
			if (!rs.next()) { //if i couldn't find the product in the data base i insert this new product
				statement.execute(insert); 
			}else {
				float valor_db = (float) rs.getInt(1);
				if	( valor_db != precio_unitario ) {
					String update = ("UPDATE producto set precio_unitario = " + precio_unitario + "where maxirest_id = " + (int) maxirest_id );
					statement.execute(update);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Connection getConnection() throws Exception{
		
        try{ 
           String url = "jdbc:mysql://localhost/comandas?";
           String username = "user=root&";
           String password = "password=mysqlpass&autoReconnect=true&useSSL=false";

           try {
               Class.forName("com.mysql.jdbc.Driver");
           } catch (ClassNotFoundException e) {
               throw new IllegalStateException("Cannot find the driver in the classpath!", e);
           }           
           
           try {
               this.Connect = DriverManager.getConnection(url + username + password);
           } catch (SQLException e) {
               throw new IllegalStateException("Cannot connect the database!", e);
           }

        }catch (Exception e ){System.out.println(e);}
        return null;
 }

}
