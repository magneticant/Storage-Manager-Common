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
 * Klasa koja opisuje stanje i ponasanje stavke otpremnice (eng. bill of lading). 
 * Nasledjuje apstraktnu klasu {@link AbstractDocumentItem}.
 * @author Milan
 * @since 1.0.0
 */
public class BillOfLadingItem extends AbstractDocumentItem implements DomainClass{
	 /**
     * privatni atribut, kupac {@link Buyer}
     */
    private Buyer buyer;
    /**
     * neparametrizovani konstruktor
     */
    public BillOfLadingItem() {
    }
    /**
     * parametrizovani konstruktor
     * @param ID identifikator stavke kao tip {@link Integer}
     * @param billID jedinstveni identifikator otpremnice kao tip {@link Integer}
     * @param buyer kupac kome prodajemo robu kao tip {@link Buyer}
     * @param firm nasa firma kao tip {@link Firm}
     * @param issuedAmount kolicina robe koja skida sa stanja ({@link Integer})
     * @param product artikal koji skidamo sa stanja (tip {@link Product})
     * @param mode mod po kojem se odredjuje uslov u WHERE klauzuli za SQL upite nad bazom kao tip {@link WhereClauseMode}
     */
    public BillOfLadingItem(Integer ID, Integer billID, Buyer buyer, Firm firm, Integer issuedAmount, Product product, WhereClauseMode mode) {
        this.ID = ID;
        this.documentID = billID;
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
    public Integer getDocumentID() {
        return documentID;
    }

    @Override
    public void setDocumentID(Integer billID) {
        this.documentID = billID;
    }

    @Override
    public DomainClass getSecondParticipant() {
        return buyer;
    }

    @Override
    public void setSecondParticipant(DomainClass buyer) {
        this.buyer = (Buyer)buyer;
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
        this.product = product;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }
    /**
     * set metoda za mod po kojem se gleda WHERE uslov u SQL naredbi
     * @param mode mod po kojem se gleda WHERE uslov u SQL naredbi, kao tip {@link WhereClauseMode}
     */
    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "BillOfLadingItem{" + "ID=" + ID + ", billID=" + documentID + ", buyer=" + buyer + ", firm=" + firm + ", issuedAmount=" + amount + ", product=" + product + ", mode=" + mode + '}';
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
                (documentID == null? "NULL" : documentID) + ", " +
                "IDKupca = " + (buyer == null? "NULL" : buyer.getID()) +
                ",IDFirme = " + (firm == null? "NULL" : firm.getID()) +
                ", izdataKolP = " + amount + ", sifraArtikla = " +
                (product == null? "NULL" : product.getID());
    }

    @Override
    public String getInsertValues() {
        return "(" + (documentID == null? "NULL" : documentID) + ", " +(buyer == null? "NULL" : buyer.getID()) +
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
    /**
     * ovde se stock smanjuje
     */
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
