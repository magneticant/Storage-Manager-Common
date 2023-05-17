package rs.np.storage_manager_common.connection.abstraction.JSONImpl;

import java.io.Serializable;

import com.google.gson.GsonBuilder;

import rs.np.storage_manager_common.connection.Operation;
import rs.np.storage_manager_common.connection.abstraction.Request;


public class RequestJSON implements Request, Serializable{
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

	@Override
	public <T> T getObj(Class<T> className) {
		System.out.println("POKUSAVAM DA IZGETUJEM");
		return (T) new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create().fromJson(obj, className);
	}


//	private void setObj(String obj) {
//		if(obj == null)
//			return;
//		this.obj = obj;
//	}
	@Override
	public void setObj(Object obj) {
		if(obj == null)
			return;
		this.obj = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create().toJson(obj);
		
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
		return "RequestJSON [obj=" + obj + ", operation=" + operation + "]";
	}
	
}
