package rs.np.storage_manager_common.connection.abstraction.JSONImpl;

import com.google.gson.GsonBuilder;

import rs.np.storage_manager_common.connection.Operation;


public class RequestJSON {
	private String obj;
	private Operation operation;
	
	
	public RequestJSON() {
		// TODO Auto-generated constructor stub
	}


	public RequestJSON(String obj, Operation operation) {
		super();
		this.obj = obj;
		this.operation = operation;
	}


	public <T> T getObj(Class<T> className) {
		return (T) new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create().fromJson(obj, className);
	}


	public void setObj(String obj) {
		if(obj == null)
			return;
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
		return "RequestJSON [obj=" + obj + ", operation=" + operation + "]";
	}
	
}
