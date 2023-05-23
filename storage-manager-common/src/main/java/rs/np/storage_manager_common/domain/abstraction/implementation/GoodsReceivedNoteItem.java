//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa koja opisuje stanje i ponasanje stavke prijemnice (eng. goods received note). 
 * Nasledjuje apstraktnu klasu {@link AbstractDocumentItem}.
 * @author Milan
 * @since 1.0.0
 */
public class GoodsReceivedNoteItem extends AbstractDocumentItem implements DomainClass{
    /**
     * privatni atribut, poslovni partner u saradnji {@link Partner}
     */
	private Partner partner;
    /**
     * neparametrizovani konstruktor
     */
    public GoodsReceivedNoteItem() {
    }
    /**
     * parametrizovani konstruktor
     * @param ID identifikator stavke kao tip {@link Integer}
     * @param noteID jedinstveni identifikator prijemnice kao tip {@link Integer}
     * @param firm nasa firma kao tip {@link Firm}
     * @param partner dobavljac od kojeg primamo robu kao tip {@link Partner}
     * @param amountAdded kolicina robe koja se dodaje na stanje ({@link Integer})
     * @param product artikal koji dodajemo na stanje (tip {@link Product})
     */
    public GoodsReceivedNoteItem(Integer ID, Integer noteID,
            Firm firm, Partner partner, Integer amountAdded, Product product) {
        this.ID = ID;
        this.documentID = noteID;
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
    public void setDocumentID(Integer noteID) {
        this.documentID= noteID;
    }

    @Override
    public Firm getFirm() {
        return firm;
    }

    @Override
    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public Partner getSecondParticipant() {
        return partner;
    }

    @Override
    public void setSecondParticipant(DomainClass partner) {
        this.partner = (Partner)partner;
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
    public String toString() {
        return "GoodsReceivedNoteItem{" + "ID=" + ID + ", noteID=" + documentID + ", firm=" + firm + ", patner=" + partner + ", amountAdded=" + amount + ", product=" + product + '}';
    }

    @Override
    public String getTableName() {
        return "stavkaprijemnice";
    }

    @Override
    public String getColumnNames() {
        return "(IDStavkePrij, IDPrijemnice, IDFirme, IDPartnera, PrimljenaKolP, sifraArtikla)";
    }

    @Override
    public String getValues() {
        return "(IDStavkePrij = " + ID + ", IDPrijemnice = " + 
                (documentID == null ? "NULL" : documentID) + ", IDFirme = " +
                (firm == null ? "NULL" : firm.getID()) + ", IDPartnera = " +
                (partner == null ? "NULL" : partner.getID()) + ", PrimljenaKolP = " +
                amount + ", sifraArtikla = " + 
                (product == null ? "NULL" : product.getID()) + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(IDStavkePrij=" + ID + ")" ;
        return "true";
    }

    @Override
    public String getInsertValues() {
        return "(" + (documentID == null? "NULL" : documentID) + ", " + (firm == null? "NULL" : firm.getID()) +
                ", " + (partner == null? "NULL" : partner.getID()) + ", " + amount + ", " + 
                (product == null? "NULL" : product.getID()) + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(IDPrijemnice, IDFirme, IDPartnera, PrimljenaKolP, sifraArtikla)";
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * ovde se stock povecava
     */
    @Override
    public int alterStock(int stock) {
        if(product == null){
            return 0;
        }
        else {
            return product.getAmount() + stock;
        }
    }
}
