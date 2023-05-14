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
import rs.np.storage_manager_common.domain.DomainClass;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.User;
import rs.np.storage_manager_common.domain.utility.TransferType;

/**
 *
 * @author Milan
 */
public class Request implements Serializable{
    private Object obj;
    private Operation operation;
    public Request() {
    }

    public Request(Object obj, Operation operation, TransferType type) {
    	this.operation = operation;
    	this.obj = obj;
    }

	public <T> Object getObj(Class<T> className) {
		return (T)obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Request [obj=" + obj + ", operation=" + operation + "]";
	}
    
}
