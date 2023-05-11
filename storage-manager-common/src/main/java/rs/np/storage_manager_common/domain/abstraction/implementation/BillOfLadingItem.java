//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;
import rs.np.storage_manager_common.domain.abstraction.Buyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class BillOfLadingItem extends AbstractDocumentItem implements DomainClass{
    private Buyer buyer;

    public BillOfLadingItem() {
    }

    public BillOfLadingItem(Integer ID, BillOfLading bill, Buyer buyer, Firm firm, Integer issuedAmount, Product product, WhereClauseMode mode) {
        this.ID = ID;
        this.document = bill;
        this.buyer = buyer;
        this.firm = firm;
        this.amount = issuedAmount;
        this.product = product;
        this.mode = mode;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
    	if(ID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(ID < 0 || ID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = ID;
    }

    @Override
    public AbstractDocument getDocument() {
        return document;
    }

    @Override
    public void setDocument(AbstractDocument bill) {
    	if(bill == null) {
    		throw new NullPointerException("Bill must not be null.");
    	}
        this.document = bill;
    }

    @Override
    public DomainClass getSecondParticipant() {
        return buyer;
    }

    @Override
    public void setSecondParticipant(DomainClass buyer) {
    	if(buyer == null) {
    		throw new NullPointerException("Buyer must not be null.");
    	}
        this.buyer = (Buyer)buyer;
    }

    @Override
    public Firm getFirm() {
        return firm;
    }

    @Override
    public void setFirm(Firm firm) {
    	if(firm == null) {
    		throw new NullPointerException("Firm must not be null.");
    	}
        this.firm = firm;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer issuedAmount) {
    	if(issuedAmount == null) {
    		throw new NullPointerException("Issued amount must not be null.");
    	}
    	if(issuedAmount < 0) {
    		throw new IllegalArgumentException("Issued amount must not be negative.");
    	}
        this.amount = issuedAmount;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
    	if(product == null) {
    		throw new NullPointerException("Product must not be null.");
    	}
        this.product = product;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.ID);
        hash = 17 * hash + Objects.hashCode(this.document);
        hash = 17 * hash + Objects.hashCode(this.buyer);
        hash = 17 * hash + Objects.hashCode(this.firm);
        hash = 17 * hash + Objects.hashCode(this.amount);
        hash = 17 * hash + Objects.hashCode(this.product);
        hash = 17 * hash + Objects.hashCode(this.mode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BillOfLadingItem other = (BillOfLadingItem) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.document, other.document)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return this.mode == other.mode;
    }

    @Override
    public String toString() {
        return "BillOfLadingItem{" + "ID=" + ID + ", bill=" + document + ", buyer=" + buyer + ", firm=" + firm + ", issuedAmount=" + amount + ", product=" + product + ", mode=" + mode + '}';
    }

    @Override
    public String getTableName() {
        return "stavkaotpremnice";
    }

    @Override
    public String getColumnNames() {
        return "IDStavke, IDOtpremnice, IDKupca, IDFirme,"
                + " izdataKolP, sifraArtikla";
    }

    @Override
    public String getValues() {
        return "IDStavke = " + ID + ",IDOtpremnice = " +
                (document == null? "NULL" : document.getID()) + ", " +
                "IDKupca = " + (buyer == null? "NULL" : buyer.getID()) +
                ",IDFirme = " + (firm == null? "NULL" : firm.getID()) +
                ", izdataKolP = " + amount + ", sifraArtikla = " +
                (product == null? "NULL" : product.getID());
    }

    @Override
    public String getInsertValues() {
        return "(" + (document == null? "NULL" : document.getID()) + ", " +(buyer == null? "NULL" : buyer.getID()) +
                ", " + (firm == null? "NULL" : firm.getID())  + ", " + amount + ", " + 
                (product == null? "NULL" : product.getID()) + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID){
            return "(IDStavke = " + ID + ")";
        }
        return "true";
    }

    @Override
    public String getColumnsWithoutID() {
        return "IDOtpremnice, IDKupca, IDFirme,"
                + " izdataKolP, sifraArtikla";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int alterStock(int stock) {
        if(product == null){
            return 0;
        }
        else if(product.getAmount()- stock < 0){
            return 0;
        }
        else 
            return product.getAmount() - stock;
    }
    
    
}
