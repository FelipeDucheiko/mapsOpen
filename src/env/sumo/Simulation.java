// CArtAgO artifact code for project mAPS

package sumo;

import cartago.*;

public class Simulation extends Artifact {
	
	// Creates a new connection
	TCPConnection conn = new TCPConnection();
	
	// Opens the connection
	void init(String initialValue) {
		System.out.println("----- Conexão com SUMO aberta -----");
		defineObsProperty("teste", initialValue);
	}
	
	// Allocate spot message
	@OPERATION
	public void allocateSpotSumo(Object idDriver, Object idSpot, Object trust) {
		
		String protocol = "D" + idDriver + "PS" + idSpot + "TR" + trust;
		
		conn.connect(protocol);
	}

	// Leave spot message
	@OPERATION
	public void leaveSpotSumo(Object idDriver, Object idSpot) {
		
		String protocol = "D" + idDriver + "LS" + idSpot;
		
		conn.connect(protocol);
	}
}

