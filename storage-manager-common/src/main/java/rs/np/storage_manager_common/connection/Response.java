//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

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

    public <T> Object getResponse(Class<T> param) {
    	Type domainListType = new TypeToken<List<T>>(){}.getType();
    	if((response instanceof LinkedTreeMap<?, ?>)) {
    		System.out.println("NOT A LIST BUT I STILL CAST IT TO DOMAIN CLASS!!!!");
    		return new Gson().fromJson(response.toString(), param);
    	}
    	
    	if(response instanceof Iterable) {
    		System.out.println("*******************");
    		System.out.println(response.toString());
			System.out.println("IT IS A LIST!!!!");
			System.out.println("*******************");
			
			return new Gson().fromJson(new Gson().toJson(response), domainListType);
		}
    	System.out.println("WORST CASE!!!! NOTHING HAPPENED!!!!");
    	
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
