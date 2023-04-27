//package domain.abstraction;
package rs.np.storage_manager_common.domain.abstraction;

import rs.np.storage_manager_common.domain.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Milan
 */
public abstract class AbstractDocument implements Serializable, DomainClass {
    public Integer ID;
    protected Firm firm;
    protected Date issueDate;
    protected Date Deadline;
    protected BigDecimal totalCost;
    protected WhereClauseMode mode;
    protected List<AbstractDocumentItem> items;
    
    public abstract Integer getID();
    public abstract void setID(Integer ID);
    public abstract Firm getFirm();
    public abstract void setFirm(Firm firm);
    public abstract Date getIssueDate();
    public abstract void setIssueDate(Date date);
    public abstract Date getDeadLine();
    public abstract void setDeadLine(Date date);
    public abstract BigDecimal getTotalCost();
    public abstract void setTotalCost(BigDecimal totalCost);
    public abstract List<AbstractDocumentItem> getItems();
    public abstract void setItems(List<AbstractDocumentItem> items);
    public abstract DomainClass getSecondParticipant();
    public abstract void setSecondParticipant(DomainClass participant);
	
}
