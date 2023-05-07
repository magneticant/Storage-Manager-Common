//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Milan
 */
public class Response implements Serializable{
    private Object response;
//    private Exception ex;
    @SerializedName(value = "exception_content")
    private String exMessage;
//    private StackTraceElement[] elements;
    
    public Response() {
    }

    public Response(Object response, String exMessage) {
        this.response = response;
        this.exMessage = exMessage;
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

}
