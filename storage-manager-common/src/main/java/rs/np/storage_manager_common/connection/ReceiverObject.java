//package connection;
package rs.np.storage_manager_common.connection;
import java.io.*;
import java.net.*;

/**
 *
 * @author Milan
 */
public class ReceiverObject {
    private Socket socket; 
    private ObjectInputStream in;
    
    public ReceiverObject(Socket socket) {
        try {
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Object input stream not initialized correctly.");
            System.out.println(ex);
        }
    }
    
    public Object receiveObject() throws Exception {
        try {
            Object obj = in.readObject();
            System.out.println("Object received correctly!");
            System.out.println("Object: " + obj.toString());
            return obj;
        } catch (IOException ex) {
            throw new Exception("Error receiving message " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error receiving message " + ex.getMessage());
        }
    }
}
