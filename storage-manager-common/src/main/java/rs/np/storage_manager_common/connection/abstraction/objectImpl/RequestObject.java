//package connection;
package rs.np.storage_manager_common.connection.abstraction.objectImpl;

import java.io.Serializable;

import rs.np.storage_manager_common.connection.Operation;
import rs.np.storage_manager_common.connection.abstraction.Request;

/**
 *
 * @author Milan
 */
public class RequestObject implements Request, Serializable{
	private static final long serialVersionUID = 2670912919957318134L;
	private Object obj;
    private Operation operation;
    public RequestObject() {
    }

    public RequestObject(Object obj, Operation operation) {
    	this.operation = operation;
    	this.obj = obj;
    }
    @SuppressWarnings("unchecked")
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
