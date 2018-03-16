package lectorbdf;

import java.io.*;
import java.sql.Connection;

import com.linuxense.javadbf.*;

public class VfpMannager {
	
	protected Connection Connect;
	
	public VfpMannager() {
		
	}

	public static void main(String args[]) {
		
		MySql mysql = new MySql();
		try {
			mysql.Connect = mysql.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		DBFReader reader = null;
		
		try {
			
			File file = new File("/tmp/mxart.dbf");
			
			reader = new DBFReader(new FileInputStream(file)); // create a DBFReader object

			int numberOfFields = reader.getFieldCount(); // get the field count if you want for some reasons like the following

			for (int i = 0; i < numberOfFields; i++) {

				DBFField field = reader.getField(i);
				System.out.println(field.getName());
			}

			Object[] rowObjects;	 // Now, lets us start reading the rows
			
			while ((rowObjects = reader.nextRecord()) != null) {
					System.out.println(rowObjects[0]); //Codigo
					System.out.println(rowObjects[2]); //Nombre
					if	(rowObjects[12] != null) {
						System.out.println(rowObjects[12]); //Precio
					}else {
						System.out.println(0); //Precio
					}
					
			}
		

		} catch (DBFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

