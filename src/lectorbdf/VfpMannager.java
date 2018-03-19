package lectorbdf;

import java.io.*;
import com.linuxense.javadbf.*;

public class VfpMannager {
	
	public static final String VFP_FILE_PATH = "/home/superq/Escritorio/DATOS/mxart.dbf";
	
	public VfpMannager() {
		
	}
	
	public static DBFReader get_file_data() {
		
		DBFReader reader = null;
		
		File file = new File(VFP_FILE_PATH);
		
		try {
			reader = new DBFReader(new FileInputStream(file));
		} catch (DBFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // create a DBFReader object
		
		return reader;
	}

	public static void main(String args[]) {
		
		MySql mysql = new MySql();

		Object[] rowObjects;	 // Now, lets us start reading the rows
		
		DBFReader reader = VfpMannager.get_file_data();
	
		try {
			while ((rowObjects = reader.nextRecord()) != null) {				
				try {
					String nombre_producto = (String) rowObjects[2];
					float categoria_id = (float) 6; //default category
					float precio_unitario = (float) 0;
					if	(rowObjects[12] != null) {
						precio_unitario = (float) rowObjects[12]; 
					}
					double maxirest_id = (double) rowObjects[0];
					mysql.addProductRecord(nombre_producto,categoria_id,precio_unitario,maxirest_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (DBFException e) {
			e.printStackTrace();
		}			
	}
}

