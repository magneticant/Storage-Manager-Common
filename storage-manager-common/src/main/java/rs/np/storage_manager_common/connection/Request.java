//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;

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

    public Object getObj() {
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
