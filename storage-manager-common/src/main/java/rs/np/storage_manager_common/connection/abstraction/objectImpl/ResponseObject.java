//package connection;
package rs.np.storage_manager_common.connection.abstraction.objectImpl;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import rs.np.storage_manager_common.connection.abstraction.Response;

/**
 *
 * @author Milan
 */
public class ResponseObject implements Response, Serializable{
	private static final long serialVersionUID = -6083103420641204849L;
	private Object response;
//    private Exception ex;
    @SerializedName(value = "exception_content")
    private String exMessage;
    
    public ResponseObject() {
    }

    public ResponseObject(Object response, String exMessage) {
        this.exMessage = exMessage;
        this.response = response;
    }
    @SuppressWarnings("unchecked")
	@Override
	public <T> T getResponse(Class<T> className) {
		return (T)response;
	}

//	public void setResponse(Object response) {
//		this.response = response;
//	}
    @Override
	public String getExMessage() {
		return exMessage;
	}
    
    @Override
	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

	@Override
	public String toString() {
		return "Response [response=" + response + ", exMessage=" + exMessage + "]";
	}

	@Override
	public <T> void setResponse(List<T> response, Class<T[]> className) {
		this.response = response;
	}

	@Override
	public <T> void setResponse(T response, Class<T> className) {
		this.response = response;
	}

	@Override
	public <T> List<T> getResponse(Class<T> className, boolean isList) {
		return (List<T>) response;
	}

}
