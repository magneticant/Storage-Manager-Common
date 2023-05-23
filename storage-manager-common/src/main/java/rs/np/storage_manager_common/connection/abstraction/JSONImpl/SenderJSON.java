package rs.np.storage_manager_common.connection.abstraction.JSONImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rs.np.storage_manager_common.connection.abstraction.Sender;


public class SenderJSON implements Sender{
	@SuppressWarnings("unused")
	private Socket socket;
	private PrintWriter out;
	
	public SenderJSON(Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			System.out.println("JSON output stream not initialized correctly.");
            System.out.println(e);
		}
	}
	@Override
	public void sendObject(Object obj) throws Exception{
		GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls()
				.setDateFormat("yyyy-MM-dd");
        Gson gson = gsonBuilder.create();
        try {
		out.println(gson.toJson(obj));
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
		System.out.println("JSON sent!");
		System.out.println(new GsonBuilder().serializeNulls().
				setPrettyPrinting().setDateFormat("yyyy-MM-dd")
				.create().toJson(obj));
	}
	
	
}
