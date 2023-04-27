//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;

/**
 *
 * @author Milan
 */
public class Response implements Serializable{
    private Object response;
    private Exception ex;

    public Response() {
    }

    public Response(Object response, Exception ex) {
        this.response = response;
        this.ex = ex;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
    
    
}
