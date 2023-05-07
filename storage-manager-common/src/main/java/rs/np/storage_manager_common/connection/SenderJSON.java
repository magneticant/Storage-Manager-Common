package rs.np.storage_manager_common.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SenderJSON {
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
	
	public void sendObject(Object obj) {
		Gson gson = new Gson();
		out.println(removeQuotesAndUnescape(gson.toJson(obj)));
		System.out.println("JSON sent!");
		System.out.println(new GsonBuilder().serializeNulls().
				setPrettyPrinting().create().toJson(obj));
	}
	
	private String removeQuotesAndUnescape(String uncleanJson) {
        String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");

        return StringEscapeUtils.unescapeJava(noQuotes);
    }
}
