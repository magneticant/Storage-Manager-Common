package rs.np.storage_manager_common.connection.abstraction;

import rs.np.storage_manager_common.connection.Operation;

public interface Request {
	public void setObj(Object obj);
	public <T> T getObj(Class<T> className);
	public void setOperation(Operation operation);
	public Operation getOperation();
}
