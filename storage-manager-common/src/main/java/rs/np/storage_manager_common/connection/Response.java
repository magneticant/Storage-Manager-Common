//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;

/**
 *
 * @author Milan
 */
public class Response implements Serializable{
    private Object response;
//    private Exception ex;
    private String exMessage;
    private StackTraceElement[] elements;
    
    public Response() {
    }

    public Response(Object response, String exMessage, StackTraceElement[] elements) {
        this.response = response;
        this.exMessage = exMessage;
        this.elements = elements;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

	public String getExMessage() {
		return exMessage;
	}

	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

	public StackTraceElement[] getElements() {
		return elements;
	}

	public void setElements(StackTraceElement[] elements) {
		this.elements = elements;
	}

    
}
