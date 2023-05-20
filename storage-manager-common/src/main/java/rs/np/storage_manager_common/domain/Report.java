//package domain;
package rs.np.storage_manager_common.domain;

//import domain.utility.DateParser;
import rs.np.storage_manager_common.domain.utility.DateParser;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Klasa za izvestaje o kapacitetu skladista
 * 
 * @author Milan
 * 
 * @since 1.0.0
 */
public class Report implements DomainClass{
	/**
	 * privatni atribut ID klase {@link Product}, kao tip {@link Integer}
	 */
    private Date reportDate;
    /**
     * privatni atribut tipa {@link Double} koji predstavlja totalni dostupni kapacitet skladista
     */
    private Double totalCapacity;
    /**
     * lista stavki izvestaja tipa {@link ReportItem}
     */
    List<ReportItem> reportItems;
    /**
     * mod za WHERE klauzulu, atribut tipa {@link WhereClauseMode}
     */
    private WhereClauseMode mode;
            
    /**
     * neparametrizovani konstruktor
     * inicijalizuje listu stavki na {@link ArrayList}-u, i postavlja totalni kapacitet na 100 (100%)
     */
    public Report() {
        reportItems = new ArrayList<>();
        this.totalCapacity = Double.valueOf("100");
        
    }
    /**
     * parametrizovani konstruktor
     * @param reportDate datum izrade izvestaja (ovo je ID izvestaja)
     * @param totalCapacity totalni ukupni kapacitet skladista (double vrednost)
     */
    public Report(Date reportDate, double totalCapacity) {
        reportItems = new ArrayList<>();
        this.reportDate = reportDate;
        this.totalCapacity = totalCapacity;
    }
    /**
     * parametrizovani konstruktor
     * @param reportDate datum izrade izvestaja (ovo je ID izvestaja)
     * @param totalCapacity totalni ukupni kapacitet skladista (double vrednost)
     * @param reportItems stavke izvestaja date kao lista {@link ReportItem} objekata
     */
    public Report(Date reportDate, double totalCapacity, List<ReportItem> reportItems) {
        reportItems = new ArrayList<>();
        this.reportDate = reportDate;
        this.totalCapacity = totalCapacity;
        this.reportItems = reportItems;
    }
    /**
     * get metoda za datum izvestaja
     * @return reportDate kao tip {@link Date}
     */
    public Date getReportDate() {
        return reportDate;
    }
    /**
     * set metoda za datum izvestaja
     * @param reportDate kao tip {@link Date}
     * @throws DateTimeException ako je pokusan unos datuma izvestaja koji je u buducnosti
     */
    public void setReportDate(Date reportDate) {
    	if(reportDate!= null && reportDate.after(new Date())) {
    		throw new DateTimeException("Report date cannot be set to a future date.");
    	}
        this.reportDate = reportDate;
    }
    /**
     * get metoda za totalni kapacitet skladista
     * @return totalCapacity kao double vrednost
     */
    public double getTotalCapacity() {
        return totalCapacity;
    }
    /**
     * set metoda za totalni kapacitet
     * @param totalCapacity raspolozivi kapacitet kao double vrednost
     * @throws IllegalArgumentException ako je unet kapacitet manji od nule ili veci od 100
     */
    public void setTotalCapacity(double totalCapacity) {
    	if(totalCapacity < 0 || totalCapacity > 100) {
    		throw new IllegalArgumentException("Total capacity is a value between 0 and 100.");
    	}
        this.totalCapacity = totalCapacity;
    }
    /**
     * get metoda za stavke izvestaja
     * @return reportItems kao lista {@link ReportItem}-a
     */
    public List<ReportItem> getReportItems() {
        return reportItems;
    }
    /**
     * Set metoda za stavke izvestaja
     * @param reportItems kao {@link List} stavki izvestaja ({@link ReportItem})
     * @throws NullPointerException ako je pokusan unos null vrednosti za stavke izvestaja
     */
    public void setReportItems(List<ReportItem> reportItems) {
    	if(reportItems == null) {
    		throw new NullPointerException("You may only set non null "
    				+ "values for report items.");
    	}
        this.reportItems = reportItems;
    }
    /**
     * Set metoda za mod po kojem se odredjuje uslov u WHERE klauzuli SQL upita
     * @param mode mod po kojem se odredjuje uslov u WHERE klauzuli SQL upita, kao tip {@link WhereClauseMode}
     */
    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }
    /**
     * hashCode se racuna za reportDate i totalCapacity
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.reportDate);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.totalCapacity) ^ (Double.doubleToLongBits(this.totalCapacity) >>> 32));
        return hash;
    }
    /**
     * equals se racuna za reportDate i totalCapacity
     */
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
        final Report other = (Report) obj;
        if (Double.doubleToLongBits(this.totalCapacity) != Double.doubleToLongBits(other.totalCapacity)) {
            return false;
        }
        return Objects.equals(this.reportDate, other.reportDate);
    }

    @Override
    public String toString() {
        return "Cap: " + totalCapacity + " Items: " +
                (reportItems == null? "0" : reportItems.size());
    }

    @Override
    public String getTableName() {
        return "izvestaj";
    }

    @Override
    public String getColumnNames() {
        return "datumIzvestaja, ukupanKap";
    }

    @Override
    public String getValues() {
        return "(datumIzvestaja = " + reportDate + 
                ", ukupanKap = " + totalCapacity + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(datumIzvestaja = '" + DateParser.resolveDateFormat(reportDate) + "')";
        return "true";
    }
    /**
     * Ovo je samo placeholder vrednost da bi se ispostovala struktura apstraktnih metoda 
     * zadatih interfejsom DomainClass. Svaka klasa je u obavezi da implementira get i set metodu za ID,
     *  a posto ovde ne postoji ID kao integer, dodeljuje se placeholder vrednosti.
     */
    private Integer placeholder;
    
    @Override
    public void setID(Integer id) {
        if(id == null) {
        	throw new NullPointerException("Id must not be set to null");
        }
        if(id < 0 || id > 1000000) {
        	throw new IllegalArgumentException("Id must be set to a value between 0 and 1000000");
        }
        placeholder = id;
    }
    
    @Override
    public Integer getID() {
    	return placeholder;
    }

    @Override
    public String getInsertValues() {
        return "('" + DateParser.resolveDateFormat(reportDate) + 
                "', " + totalCapacity + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(ukupanKap)";
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        return new Report(
                DateParser.sqlDateToUtilDate(
                rs.getDate("datumizvestaja")),
                rs.getDouble("ukupanKap")
            );
    }
    /**
     * javna metoda koja svim stavkama izvestaja dodeljuje vrednosti datuma (ID-ja) 
     * izvestaja kojem su namenjene.
     * @throws IllegalStateException ako je atribut reportItems postavljen na null.
     * 
     */
    public void assignItemIDs() {
    	if(reportItems == null) {
    		throw new IllegalStateException("Report items should not have been null.");
    	}
    	if(reportItems.isEmpty()) {
    		System.out.println("There are no items.");
    		return;
    	}
    	for(ReportItem item: reportItems) {
    		item.setReportID(reportDate);
    	}
    }

}
