//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;
import rs.np.storage_manager_common.domain.abstraction.Buyer;
import rs.np.storage_manager_common.domain.utility.DateParser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja opisuje stanje i ponasanje otpremnice (eng. bill of lading). 
 * Nasledjuje apstraktnu klasu {@link AbstractDocument}.
 * @author Milan
 * @since 1.0.0
 */
public class BillOfLading extends AbstractDocument implements DomainClass, Serializable {
	/**
     * privatni atribut, kupac {@link Buyer}
     */
	private Buyer buyer;
    /**
     * lista stavki dokumenta ({@link BillOfLadingItem})
     */
    private List<BillOfLadingItem> items;
    /**
     * neparametrizovani konstruktor
     */
    public BillOfLading() {
        items = new ArrayList<>();
    }
    /**
     * parametrizovani konstruktor
     * @param ID identifikator otpremnice kao tip {@link Integer}
     * @param buyer kupac kome prodajemo robu kao tip {@link Buyer}
     * @param firm nasa firma kao tip {@link Firm}
     * @param issueDate datum izdavanja otpremnice ({@link Date})
     * @param dueDate datum dospelosti obaveze isplate otpremnice ({@link Date})
     * @param totalCost ukupna cena robe ({@link BigDecimal})
     * @param mode mod po kojem se odredjuje uslov za WHERE klauzulu u SQL upitima
     */
    public BillOfLading(Integer ID, Buyer buyer, Firm firm, Date issueDate, Date dueDate, BigDecimal totalPrice, WhereClauseMode mode) {
        this.ID = ID;
        this.buyer = buyer;
        this.firm = firm;
        this.issueDate = issueDate;
        this.Deadline = dueDate;
        this.totalCost = totalPrice;
        this.mode = mode;
        items = new ArrayList<>();
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
    		throw new NullPointerException("Firm cannot be null.");
    	}
        this.firm = firm;
    }

    @Override
    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public void setIssueDate(Date issueDate) {
    	if(issueDate == null) {
    		throw new NullPointerException("Issue date must not be null");
    	}
    	if(issueDate.after(new Date())) {
    		throw new DateTimeException("Issue date cannot be set in the future.");
    	}
    	if(this.Deadline != null && this.Deadline.before(issueDate)) {
    		throw new DateTimeException("Due date cannot be before issue date.");
    	}
        this.issueDate = issueDate;
    }

    @Override
    public Date getDeadLine() {
        return Deadline;
    }
    
    @Override
    public void setDeadLine(Date date) {
    	if(date == null) {
    		throw new NullPointerException("Due date must not be null");
    	}
    	if(date.before(new Date())) {
    		throw new DateTimeException("Due date cannot be set in the past.");
    	}
    	if(this.issueDate != null && date.before(issueDate)) {
    		throw new DateTimeException("Due date cannot be before issue date.");
    	}
        Deadline = date;
    }

    @Override
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public void setTotalCost(BigDecimal totalCost) {
    	if(totalCost == null) {
    		throw new NullPointerException("Total cost must not be null.");
    	}
    	if(totalCost.doubleValue() < 0) {
    		throw new IllegalArgumentException("Total cost must not be less than 0.");
    	}
        this.totalCost = totalCost;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("You must set mode with this method.");
    	}
        this.mode = mode;
    }

    
    public List<BillOfLadingItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<? extends AbstractDocumentItem> items) {
    	if(items == null) {
    		throw new NullPointerException("Items must be set with this method.");
    	}
        this.items = (List<BillOfLadingItem>) items;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.ID);
        hash = 19 * hash + Objects.hashCode(this.buyer);
        hash = 19 * hash + Objects.hashCode(this.firm);
        hash = 19 * hash + Objects.hashCode(this.issueDate);
        hash = 19 * hash + Objects.hashCode(super.Deadline);
        hash = 19 * hash + Objects.hashCode(super.totalCost);
        hash = 19 * hash + Objects.hashCode(this.mode);
        hash = 19 * hash + Objects.hashCode(this.items);
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
        final BillOfLading other = (BillOfLading) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) {
            return false;
        }
        if (!Objects.equals(this.Deadline, other.Deadline)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (this.mode != other.mode) {
            return false;
        }
        return Objects.equals(this.items, other.items);
    }

    @Override
    public String toString() {
        return "BillOfLading{" + "ID=" + ID + ", buyer=" + buyer + ", firm=" + firm;
    }
    
    @Override
    public String getTableName() {
        return "otpremnica";
    }

    @Override
    public String getColumnNames() {
        return "IDOtpremnice, IDKupca, IDFirme, datumIzdavanja, datumValute, ukupanIznos";
    }

    @Override
    public String getValues() {
        return "IDOtpremnice = " + ID + ", IDKupca = " + (getSecondParticipant() == null ? "NULL" : buyer.getID()) + 
                ", IDFirme = " + (firm == null? "NULL" : firm.getID()) + ", datumIzdavanja = " + 
        DateParser.resolveDateFormat(issueDate) + ", datumValute = " + DateParser.resolveDateFormat(Deadline) 
                + ", ukupanIznos = " + totalCost;
    }

    @Override
    public String getInsertValues() {
        
        return "(" + (buyer == null? "NULL" : buyer.getID()) + ", " +
               (firm == null? "NULL" : firm.getID()) + ", '" +
                DateParser.resolveDateFormat(issueDate) + "', '" + 
                DateParser.resolveDateFormat(Deadline) + "', " +
                String.valueOf(totalCost) + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        switch(mode){
            case BY_ID:
                return "(IDOtpremnice = " + ID + ")";
            case BY_NAME:
            case BY_USERNAME_PASSWORD:
                return "true";
            default: return "true";
        }
    }

    @Override
    public String getColumnsWithoutID() {
        return "IDKupca, IDFirme, datumIzdavanja, datumValute, ukupanIznos";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

	@Override
	public void addItem(AbstractDocumentItem item) {
		items.add((BillOfLadingItem)item);
	}
    
}
