//package connection;
package rs.np.storage_manager_common.connection;
import java.io.*;
import java.net.*;

import rs.np.storage_manager_common.connection.abstraction.Sender;

/**
 *
 * @author Milan
 */
public class SenderObject implements Sender{
    private Socket socket;
    private ObjectOutputStream out;

    public SenderObject(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Object output stream not initialized correctly.");
            System.out.println(ex);
        }
    }
    
    public void sendObject(Object obj) throws Exception {
        try {
            out.writeObject(obj);
            out.flush();
            System.out.println("Object sent!");
            System.out.println("Object: " + obj);
        } catch (IOException ex) {
            throw new Exception("Object not sent correctly!" + ex);
        }
    }
}
