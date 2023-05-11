//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import rs.np.storage_manager_common.domain.DomainClass;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.User;
import rs.np.storage_manager_common.domain.utility.JSONPurifier;

/**
 *
 * @author Milan
 */
public class Request implements Serializable{
    private Object obj;
    private Operation operation;

    public Request() {
    }

    public Request(Object obj, Operation operation) {
        this.obj = obj;
        this.operation = operation;
    }

    public Object getObj(Class<?> param) {
//    	Type domainListType = new TypeToken<List<Object>>(){}.getType();
    	if((obj instanceof LinkedTreeMap<?, ?>)) {
    		
    		return new Gson().fromJson(JSONPurifier.removeQuotesAndUnescape(obj.toString()), param);
    	}
        return obj;
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
    
    
}
