//package domain;
package rs.np.storage_manager_common.domain;

import rs.np.storage_manager_common.domain.utility.DateParser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Klasa za stavke izvestaja o totalnom kapacitetu skladista
 * 
 * @author Milan
 * 
 * @since 1.0.0
 */
public class ReportItem implements DomainClass{
	/**
	 * privatni staticki atribut, serijski broj generisan na zahtev Serializable interfejsa.
	 */
	private static final long serialVersionUID = 5644778758055163296L;
	/**
	 * privatni atribut ID klase {@link ReportItem}, kao tip {@link Integer}
	 */
    private Integer ID;
//    private Report report;
    /**
     * privatni atribut ID izvestaja {@link Report}, kao tip {@link Date}
     */
    private Date reportID;
    /**
     * privatni atribut koji opisuje iskorisceni kapacitet za ovaj konkretni proizvod kao {@link Double} vrednost
     */
    private Double productCapacity;
    /**
     * privatni atribut koji predstavlja proizvod za koji se ova stavka "gleda", kao tip {@link Product}
     */
    private Product product;
    /**
     * privatni atribut koji opisuje koliko mesta je dostupno za skladistenje datog artikla. Default-na vrednost 
     * je 200 proizvoda po tipu.
     */
    private Integer totalAvailableCapacity = 200;
    /**
     * privatni atribut koji predstavlja mod za WHERE sql klauzulu, kao enum.
     * Nacin na koji se odredjuje jednakost WHERE klauzule.
     */
    private WhereClauseMode mode;
    /**
     * neparametrizovani konstruktor
     */
    public ReportItem() {
    }
    /**
     * parametrizovani konstruktor
     * @param ID kao tip {@link Integer}, predstavlja identifikator stavke
     * @param reportID je tipa {@link Date} i predstavlja datum kreiranja izvestaja
     * @param productCapacity je {@link Double} vrednost koja prikazuje iskoriscenost skladisnog kapaciteta proizvoda u procentima
     * @param product je tipa {@link Product} i ovo je artikal stavke izvestaja.
     */
    public ReportItem(Integer ID, Date reportID, Double productCapacity, Product product) {
        this.ID = ID;
//        this.report = report;
        this.reportID = reportID;
        this.productCapacity = productCapacity;
        this.product = product;
    }
    /**
     * get metoda za id
     * @return ID kao tip {@link Integer}
     */
    public Integer getID() {
        return ID;
    }
    /**
     * set metoda za jedinstveni identifikator stavke izvestaja
     * @param ID kao tip {@link Integer}
     * @throws NullPointerException ako je pokusan unos null vrednosti.
     * @throws IllegalArgumentException ako je pokusan unos ID-ja manjeg od nula ili veceg od milion.
     */
    public void setID(Integer ID) {
    	if(ID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(ID < 0 || ID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = ID;
    }
    /**
     * get metoda za ID (datum izdavanja) izvestaja
     * @return reportID kao {@link Date}
     */
    public Date getReportID() {
        return reportID;
    }
    /**
     * set metoda za ID (datum izdavanja) izvestaja
     * @param reportID kao {@link Date}
     * 
     */
    public void setReportID(Date reportID) {
        this.reportID = reportID;
    }
    /**
     * get metoda za kapacitet proizvoda ({@link Double})
     * @return productCapacity (iskoriscen kapacitet proizvoda) kao {@link Double} vrednost.
     */
    public Double getProductCapacity() {
        return productCapacity;
    }
    /**
     * set metoda za kapacitet proizvoda 
     * @param productCapacity kao {@link Double} vrednost
     * @throws NullPointerException ako je pokusan unos null vrednosti.
     * @throws IllegalArgumentException ako je pokusan unos kapaciteta manjeg od nula ili veceg od 100%.
     */
    public void setProductCapacity(Double productCapacity) {
    	if(productCapacity == null) {
    		throw new NullPointerException("Product capacity cannot be null.");
    	}
    	if(productCapacity < 0 || productCapacity > 100) {
    		throw new IllegalArgumentException("Product capacity can only be set between 0 and 100.");
    	}
        this.productCapacity = productCapacity;
    }
    /**
     * get metoda za proizvod
     * @return product kao {@link Product}
     */
    public Product getProduct() {
        return product;
    }
    /**
     * set metoda za proizvod
     * @param product kao {@link Product} tip
     * @throws NullPointerException ako je pokusan unos null vrednosti za proizvod.
     */
    public void setProduct(Product product) {
    	if(product == null) {
    		throw new NullPointerException("You may not set product as null.");
    	}
        this.product = product;
    }
    /**
     * get metoda za ukupni dostupni kapacitet za konkretni proizvod (kao cela vrednost, broj 
     * artikala tog tipa koje skladiste moze da "drzi")
     * @return totalAvailableCapacity kao {@link Integer} vrednost
     */
    public Integer getTotalAvailableCapacity() {
        return totalAvailableCapacity;
    }
    /**
     * set metoda za ukupan moguci broj artikala tipa zadatog atributom ove klase koje skladiste moze da podrzi
     * @param totalAvailableCapacity ukupan moguci kapacitet kao ceo broj ({@link Integer})
     * @throws NullPointerException ako je pokusan unos null vrednosti
     * @throws IllegalArgumentException ako je pokusan unos vrednosti manje od 0 ili vece od 50000
     */
    public void setTotalAvailableCapacity(Integer totalAvailableCapacity) {
    	if(totalAvailableCapacity == null) {
    		throw new NullPointerException("Product capacity cannot be null.");
    	}
    	if(totalAvailableCapacity < 0 
    			|| totalAvailableCapacity > 50000) {
    		throw new IllegalArgumentException("Product capacity can only be set between 0 and 100.");
    	}
        this.totalAvailableCapacity = totalAvailableCapacity;
    }
    /**
     * set metoda za mod WHERE klauzule u SQL-u
     * @param mode kao {@link WhereClauseMode}
     * @throws NullPointerException ako je pokusan unos null vrednosti.
     */
    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }
    
	/**
	 * hashCode se racuna za ID i reportID
	 */
    @Override
	public int hashCode() {
		return Objects.hash(ID, reportID);
	}
    
    /**
     * equals se racuna za ID i reportID
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportItem other = (ReportItem) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(reportID, other.reportID);
	}
	@Override
	public String toString() {
		return "ReportItem [ID=" + ID + ", reportID=" + reportID + ", productCapacity=" + productCapacity + ", product="
				+ product + ", totalAvailableCapacity=" + totalAvailableCapacity + ", mode=" + mode + "]";
	}

	@Override
    public String getTableName() {
        return "stavkaizvestaja";
    }

    @Override
    public String getColumnNames() {
        return "kapacitetArt, sifraStavke, datumIzvestaja, sifraArtikla, ukupanKapDostupno";
    }

    @Override
    public String getValues() {
        return "(kapacitetArt = " + productCapacity + ", sifraStavke = " +
                ID + ", datumIzvestaja = " + reportID +
                ", sifraArtikla = " + product == null? null : product.getID() + ", ukupanKapDostupno = " +
                totalAvailableCapacity + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(sifraStavke = " + ID + ")"; 
        
        return "true";
    }

    @Override
    public String getInsertValues() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("REPORT: " + reportID);
        System.out.println("PRODUCTCAPACITY: " + productCapacity);
        System.out.println("PRODUCTID:" + product.getID());
        return "('" + format.format(reportID) + "', " +
                Math.round(productCapacity * 100.0) / 100.0 + ", " + product.getID() + 
                ", " + totalAvailableCapacity + ")";
                 
    }

    @Override
    public String getColumnsWithoutID() {
        return "datumIzvestaja, kapacitetArt,sifraArtikla, ukupanKapDostupno";
    }

    /**
     * metoda za racunanje iskoriscenog kapaciteta skladista za konkretnu stavku izvestaja
     * @return vrednost sracunatog kapaciteta kao {@link Double}
     */
    public Double calculateCapacity() {
        if(product == null)
            return Double.valueOf(100);
        Integer stock = product.getAmount();
        
        return (Double.valueOf(stock)/totalAvailableCapacity)*100;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
//        Report report = new Report();
//        report.setReportDate(
//                DateParser.sqlDateToUtilDate(
//                    rs.getDate("datumIzvestaja"))
//        );
        Product prod = new Product();
        prod.setID(rs.getInt("sifraArtikla"));
        return new ReportItem(
                rs.getInt("sifraStavke"),
                DateParser.sqlDateToUtilDate(
                        rs.getDate("datumIzvestaja")),
                rs.getDouble("ukupanKapDostupno"),
                prod
                );
    }
    /**
     * Metoda za postavljanje totalnog broja artikala odredjenog tipa koje skladiste moze da podrzi.
     * Ako se pokusa sa unosom null vrednosti ili vrednosti manje od 200, nista se ne desava. 
     * Inace se dodeljuje vrednost trenutnog "stock-a" za proizvod stavke.
     */
    public void setCapacity() {
        if(product == null || product.getAmount()<200){
            return;
        }
        this.totalAvailableCapacity = product.getAmount();
    }

    
    
}
