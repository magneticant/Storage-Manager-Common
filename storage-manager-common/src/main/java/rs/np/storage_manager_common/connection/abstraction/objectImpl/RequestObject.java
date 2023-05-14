//package connection;
package rs.np.storage_manager_common.connection.abstraction.objectImpl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import rs.np.storage_manager_common.connection.Operation;
import rs.np.storage_manager_common.connection.abstraction.Request;
import rs.np.storage_manager_common.domain.DomainClass;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.User;

/**
 *
 * @author Milan
 */
public class RequestObject implements Request, Serializable{
    private Object obj;
    private Operation operation;
    public RequestObject() {
    }

    public RequestObject(Object obj, Operation operation) {
    	this.operation = operation;
    	this.obj = obj;
    }
    @Override
	public <T> T getObj(Class<T> className) {
		return (T)obj;
	}
    @Override
	public void setObj(Object obj) {
		this.obj = obj;
	}
    @Override
	public Operation getOperation() {
		return operation;
	}
    @Override
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Request [obj=" + obj + ", operation=" + operation + "]";
	}
    
}
