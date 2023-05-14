package rs.np.storage_manager_common.connection.abstraction;

import java.util.List;

public interface Response {
	public <T> void setResponse(List<T> response, Class<T[]> className);
	public <T> void setResponse(T response, Class<T> className);
	public <T> List<T> getResponse(Class<T> className, boolean isList);
	public <T> T getResponse(Class<T> className);
	public String getExMessage();
	public void setExMessage(String exMessage);
}
