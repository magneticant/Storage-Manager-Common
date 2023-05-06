package rs.np.storage_manager_common.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import rs.np.storage_manager_common.connection.abstraction.Receiver;

public class ReceiverJSON implements Receiver{
	private Socket socket;
	private BufferedReader in;
	
	public ReceiverJSON(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("JSON input stream not initialized correctly.");
            System.out.println(e);
		}
	}
	
	@Override
	public Object receiveObject() throws Exception {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		JsonObject obj = gson.fromJson(in,JsonObject.class );
		System.out.println("Json object received correctly!");
		System.out.println(obj);
		
		return obj.toString();
	}

}
