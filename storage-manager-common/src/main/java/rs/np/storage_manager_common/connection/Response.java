//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import rs.np.storage_manager_common.domain.utility.TransferType;

/**
 *
 * @author Milan
 */
public class Response implements Serializable{
    private Object response;
//    private Exception ex;
    @SerializedName(value = "exception_content")
    private String exMessage;
    
    public Response() {
    }

    public Response(Object response, String exMessage) {
        this.exMessage = exMessage;
        this.response = response;
    }

	public <T> Object getResponse(Class<T> className) {
		return (T)response;
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

	@Override
	public String toString() {
		return "Response [response=" + response + ", exMessage=" + exMessage + "]";
	}

}
