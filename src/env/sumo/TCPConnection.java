package sumo;

import java.io.*;
import java.net.*;

public class TCPConnection {
	
	public void saveProtocol(String protocol, String fileName){ 
		// Método para gravar arquivo de saída
		try{
			String file = "D:\\Data\\Documents\\EclipseProjects\\mapsOpen\\SumoFiles\\" + fileName;
			FileWriter fileWriter =
	                new FileWriter(file, true);

	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter);
	            bufferedWriter.write(protocol);
                bufferedWriter.newLine();
                bufferedWriter.close();
			
			} catch (IOException ex) {
				System.out.println(
		                "Error writing to file '"
		                + fileName + "'");
		            ex.printStackTrace();
			}
	}
	
	public void connect(String protocol) {		
	    try {
	    	// Create new socket
	    	Socket skt = new Socket("localhost", 1234);
	         
	        PrintWriter out = new PrintWriter(skt.getOutputStream(), true);

	        // Saves file
	        //saveProtocol(protocol, "protocolMaps.txt");
	        
	        // Sends the protocol to Middleware
	        out.print(protocol);
	        
	        // Close communication
	        out.close();
	        skt.close();
	        
	    }catch(Exception e) {
	    	System.out.print("Sumo desconectado!\n");
	    }
	}
}
