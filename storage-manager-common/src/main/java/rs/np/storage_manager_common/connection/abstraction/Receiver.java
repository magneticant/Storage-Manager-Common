package rs.np.storage_manager_common.connection.abstraction;

public interface Receiver {
	public<T> T receiveObject(Class<T> obj) throws Exception;
}
