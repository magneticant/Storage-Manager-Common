//package domain;
package rs.np.storage_manager_common.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DomainClass extends Serializable{
    
    String getTableName();
    String getColumnNames();
    String getValues();
    String getInsertValues();
    String getWhereCondition(WhereClauseMode mode);
    public void setID(Integer id);
    String getColumnsWithoutID();
    public WhereClauseMode getMode();
    DomainClass selectObject(ResultSet rs) throws SQLException;
}
