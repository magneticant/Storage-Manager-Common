//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Klasa koja opisuje stanje i ponasanje stavke narudzbenice (eng. invoice). 
 * Nasledjuje apstraktnu klasu {@link AbstractDocumentItem}.
 * @author Milan
 * @since 1.0.0
 */
public class InvoiceItem extends AbstractDocumentItem implements DomainClass {
	/**
     * privatni atribut, poslovni partner u saradnji {@link Partner}
     */
    private Partner partner;
    /**
     * neparametrizovani konstruktor
     */
    public InvoiceItem() {
    }
    /**
     * parametrizovani konstruktor
     * @param ID identifikator stavke kao tip {@link Integer}
     * @param invoiceID jedinstveni identifikator narudzbenice kao tip {@link Integer}
     * @param firm nasa firma kao tip {@link Firm}
     * @param partner dobavljac od kojeg porucujemo robu kao tip {@link Partner}
     * @param amountAdded kolicina robe koja se porucuje ({@link Integer})
     * @param product artikal koji porucujemo (tip {@link Product})
     */
    public InvoiceItem(Integer ID, Integer invoiceID, Firm firm, Partner partner, Integer amountAdded, Product product) {
        this.ID = ID;
        this.documentID = invoiceID;
        this.firm = firm;
        this.partner = partner;
        this.amount = amountAdded;
        this.product = product;
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
    public Integer getDocumentID() {
        return documentID;
    }

    @Override
    public void setDocumentID(Integer invoiceID) {
//    	if(invoiceID == null) {
//    		throw new NullPointerException("Invoice must not be set to null.");
//    	}
        this.documentID = invoiceID;
    }

    @Override
    public Firm getFirm() {
        return firm;
    }

    @Override
    public void setFirm(Firm firm) {
    	if(firm == null) {
    		throw new NullPointerException("Firm must not be set to null.");
    	}
        this.firm = firm;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
    	if(partner == null) {
    		throw new NullPointerException("Partner must not be set to null.");
    	}
        this.partner = partner;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer amountAdded) {
    	if(amountAdded == null) {
    		throw new NullPointerException("Amount added must not be null.");
    	}
    	if(amountAdded < 0) {
    		throw new IllegalArgumentException("Amount added is always positive.");
    	}
        this.amount = amountAdded;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ID);
        hash = 37 * hash + Objects.hashCode(this.documentID);
        hash = 37 * hash + Objects.hashCode(this.firm);
        hash = 37 * hash + Objects.hashCode(this.partner);
        hash = 37 * hash + Objects.hashCode(this.amount);
        hash = 37 * hash + Objects.hashCode(this.product);
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
        final InvoiceItem other = (InvoiceItem) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.documentID, other.documentID)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.partner, other.partner)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" + "ID=" + ID + ", invoiceID=" + documentID + ", firm=" + firm + ", partner=" + partner + ", amountAdded=" + amount + ", product=" + product + ", mode=" + mode + '}';
    }
    
    @Override
    public String getTableName() {
        return "stavkanarudzbenice";
    }

    @Override
    public String getColumnNames() {
        return "(IDStavkePrij, IDPrijemnice, IDFirme, IDPartnera, PrimljenaKolP, sifraArtikla)";
    }

    @Override
    public String getValues() {
        return "(IdStavkeNar = " + ID + ", IDNarudzbenice = " + 
                (documentID == null ? "NULL" : documentID) + ", IDFirme = " +
                (firm == null ? "NULL" : firm.getID()) + ", IDPartnera = " +
                (partner == null ? "NULL" : partner.getID()) + ", PorucenaKolN = " +
                amount + ", sifraArtikla = " + 
                (product == null ? "NULL" : product.getID()) + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(IDStavkeNar =" + ID + ")" ;
        else return "true";
    }

    @Override
    public String getInsertValues() {
        return "(" + (documentID == null? "NULL" : documentID) + ", " + (firm == null? "NULL" : firm.getID()) +
                ", " + (partner == null? "NULL" : partner.getID()) + ", " + amount + ", " + 
                (product == null? "NULL" : product.getID()) + ")";
    }
    /*
    private Integer ID;
    private GoodsReceivedNote note;
    private Firm firm;
    private Partner partner;
    private Integer amountOrdered;
    private Product product;
    */

    @Override
    public String getColumnsWithoutID() {
        return "(IDNarudzbenice , IDFirme, IDPartnera, PorucenaKolN, sifraArtikla)";
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass getSecondParticipant() {
        return partner;
    }

    @Override
    public void setSecondParticipant(DomainClass participant) {
    	if(participant == null) {
    		throw new NullPointerException("Participant must not be null.");
    	}
        partner = (Partner)participant;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * ovde stock ostaje isti (jer tek sad porucujemo robu, nije nam jos stigla)
     */
    @Override
    public int alterStock(int stock) {
        if(product == null)
            return 0;
        else 
            return product.getAmount();
    }
    
}
