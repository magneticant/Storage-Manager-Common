package rs.np.storage_manager_common.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rs.np.storage_manager_common.connection.abstraction.Sender;

public class SenderJSON implements Sender{
	private Socket socket;
	private PrintWriter out;
	
	public SenderJSON() {
		this.socket = socket;
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			System.out.println("JSON output stream not initialized correctly.");
            System.out.println(e);
		}
	}
	
	public void sendObject(Object obj) {
		var gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		out.println(gson.toJson(obj));
		System.out.println("JSON sent!");
		System.out.println("JSON: " + gson.toJson(obj));
	}
}
