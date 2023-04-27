//package domain.abstraction;
package rs.np.storage_manager_common.domain.abstraction;

import rs.np.storage_manager_common.domain.*;
import java.io.Serializable;

/**
 *
 * @author Milan
 */
public abstract class AbstractDocumentItem implements Serializable, DomainClass {
    protected Integer ID;
    protected AbstractDocument document;
    protected Firm firm;
    protected Integer amount;
    protected Product product;
    protected WhereClauseMode mode;
    
    public abstract Integer getID();
    public abstract void setID(Integer ID);
    public abstract AbstractDocument getDocument();
    public abstract void setDocument(AbstractDocument document);
    public abstract Firm getFirm();
    public abstract void setFirm(Firm firm);
    public abstract Integer getAmount();
    public abstract void setAmount(Integer amount);
    public abstract Product getProduct();
    public abstract void setProduct(Product product);
    public abstract DomainClass getSecondParticipant();
    public abstract void setSecondParticipant(DomainClass participant);
    public abstract int alterStock(int stock);
}
