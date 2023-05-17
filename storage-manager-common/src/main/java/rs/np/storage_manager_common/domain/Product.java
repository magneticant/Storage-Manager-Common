//package domain;
package rs.np.storage_manager_common.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa za opisivanje artikala. Sadrzi brojne podatke o artiklima na skladistu.
 * @author Milan
 * 
 * @since 1.0.0
 */
public class Product implements DomainClass {
	/**
	 * privatni atribut ID klase {@link Product}, kao tip {@link Integer}
	 */
    private Integer ID;
    /**
     * privatni atribut - naziv proizvoda ({@link String})
     */
    private String productName;
    /**
     * privatni atribut mase proizvoda izrazene u gramima, kao {@link Double} vrednost.
     */
    private Double weight;
    /**
     * privatni atribut koji nam govori o tome da li je artikal lomljiv ili ne. Boolean vrednost.
     */
    private Boolean fragile;
    /**
     * broj dostupnih artikala na skladistu, privatni atribut, {@link Integer}
     */
    private Integer amount;
    /**
     * tip proizvoda kao {@link ProductType} (enum)
     */
    private ProductType type;
    /**
     * cena proizvoda, {@link BigDecimal} vrednost
     */
    private BigDecimal price;
    /**
     * mod za WHERE klauzulu, atribut tipa {@link WhereClauseMode}
     */
    private WhereClauseMode mode;
    /**
     * neparametarski konstruktor
     */
    public Product() {
    }
    /**
     * all-params konstruktor
     * @param ID identifikator kao {@link Integer}
     * @param productName naziv proizvoda kao {@link String}
     * @param weight masa proizvoda kao {@link Double}
     * @param fragile lomljivost proizvoda kao {@link Boolean}
     * @param amount kolicina proizvoda (stanje robe na skladistu) kao {@link Integer}
     * @param type tip artikla kao {@link ProductType}
     * @param price cena kao {@link BigDecimal}
     */
    public Product(Integer ID, String productName, Double weight, Boolean fragile, Integer amount, ProductType type, BigDecimal price) {
        this.ID = ID;
        this.productName = productName;
        this.weight = weight;
        this.fragile = fragile;
        this.amount = amount;
        this.type = type;
        this.price = price;
    }
    /**
     * get metoda za ID
     * @return identifikator kao {@link Integer}
     */
    public Integer getID() {
        return ID;
    }
    /**
     * set metoda za ID
     * @param productID kao {@link Integer}
     * @throws NullPointerException ako je ID unet kao null vrednost
     * @throws IllegalArgumentException ako je unet id koji je ili manji od nule ili veci od 1000000
     */
    public void setID(Integer productID) {
    	if(productID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(productID < 0 || productID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = productID;
    }
    /**
     * get metoda za ime proizvoda
     * @return ime proizvoda kao {@link String}
     */
    public String getProductName() {
        return productName;
    }
    /**
     * set metoda za ime prozivoda
     * @param productName ime kao {@link String}
     */
    public void setProductName(String productName) {
    	if(productName == null || productName.isBlank()) {
    		throw new NullPointerException("Product name cannot be left blank.");
    	}
    	if(productName.length() <2 || productName.length()>30) {
    		throw new IllegalArgumentException("Product name cannot be less than 2 or longer than 30");
    	}
        this.productName = productName;
    }
    /**
     * get metoda za masu proizvoda 
     * @return masa kao {@link Double}
     */
    public Double getWeight() {
        return weight;
    }
    /**
     * set metoda za masu proizvoda 
     * @param weight masa kao {@link Double} vrednost
     * @throws NullPointerException ako je uneta vrednost null za masu
     * @throws IllegalArgumentException ako je uneta vrednsot za masu manja od nule
     */
    public void setWeight(Double weight) {
    	if(weight == null) {
    		throw new NullPointerException("Weight cannot be null.");
    	}
    	if(weight < 0) {
    		throw new IllegalArgumentException("Weigth cannot be negative");
    	}
        this.weight = weight;
    }
    /**
     * get metoda za lomljivost proizvoda
     * @return status lomljivosti kao {@link Boolean} vrednost
     */
    public Boolean getFragile() {
        return fragile;
    }
    /**
     * set metoda za lomljivost proizvoda 
     * @param fragile kao {@link Boolean} vrednost
     */
    public void setFragile(Boolean fragile) {
    	if(fragile == null) {
    		throw new NullPointerException("Fragile is a boolean value"
    				+ " and as such can only be true or false.");
    	}
        this.fragile = fragile;
    }
    /**
     * get metoda za broj artikala na skladistu
     * @return kolicina kao {@link Integer}
     */
    public Integer getAmount() {
        return amount;
    }
    /**
     * set metoda za kolicinu robe na skladistu
     * @param amount kao {@link Integer} vrednost
     * @throws NullPointerException ako je uneta kolicina null vrednost.
     * @throws IllegalArgumentException ako je unet stock manji od nule. 
     */
    public void setAmount(Integer amount) {
    	if(amount == null) {
    		throw new NullPointerException("Stock cannot be null.");
    	}
    	if(amount < 0) {
    		throw new IllegalArgumentException("Stocks of value less than 0 are not allowed.");
    	}
        this.amount = amount;
    }
    /**
     * get metoda za tip proizvoda
     * @return type kao {@link ProductType}
     */
    public ProductType getType() {
        return type;
    }
    /**
     * set metoda za tip proizvoda 
     * @param type kao {@link ProductType}, tip proizvoda 
     * @throws NullPointerException ako je uneti tip proizvoda null vrednost.
     */
    public void setType(ProductType type) {
    	if(type == null) {
    		throw new NullPointerException("Product type must be set with this method.");
    	}
        this.type = type;
    }
    /**
     * get metoda za cenu artikla
     * @return price, kao {@link BigDecimal} vrednost.
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * set metoda za cenu artikla
     * @param price kao {@link BigDecimal} vrednost
     * @throws NullPointerException ako je uneta cena null vrednost
     * @throws IllegalArgumentException ako je uneta cena manja od nule
     */
    public void setPrice(BigDecimal price) {
    	if(price == null) {
    		throw new NullPointerException("Price cannot be undefined.");
    	}
    	if(price.intValue() < 0) {
    		throw new IllegalArgumentException("Price is never negative.");
    	}
        this.price = price;
    }
    /**
     * get metoda za mod ({@link WhereClauseMode})
     * @return mode kao tip {@link WhereClauseMode}
     */
    public WhereClauseMode getMode() {
        return mode;
    }
    /**
     * set metoda za mod
     * @param mode kao tip {@link WhereClauseMode}, mod za WHERE klauzulu.
     * @throws NullPointerException ako je unet mod null vrednost.
     */
    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }
    
    /**
     * hashCode se racuna za ID i productName
     */
    @Override
	public int hashCode() {
		return Objects.hash(ID, productName);
	}
    
    /**
     * equals se racuna za ID i productName
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(productName, other.productName);
	}
	@Override
    public String getTableName() {
        return "artikal";
    }

    @Override
    public String getColumnNames() {
        return "sifraArtikla, imeArtikla, gramaza, lomljiv, kolicina, tip, cena";
    }

    @Override
    public String getValues() {
        return "sifraArtikla = " + ID + ", imeArtikla = '" + productName +
                "', gramaza = " + weight + ", lomljiv = '" + (fragile == false ? 0 : 1) +
                "', kolicina = " + amount + ", tip = '" + type + 
                "', cena = " + price;
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_NAME)
            return "(imeArtikla LIKE '%" + productName + "%')";
        if(mode == WhereClauseMode.BY_ID)
            return "(sifraArtikla = " + ID + ")";
        
        return "true";
    }

    @Override
    public String getInsertValues() {
      return "('" + productName + "', " + weight + ", " + fragile + ", " +
              amount + ", '" + type + "', " + price + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "imeArtikla, gramaza, lomljiv, kolicina, tip, cena";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("sifraArtikla"),
                rs.getString("imeArtikla"),
                rs.getDouble("gramaza"),
                rs.getBoolean("lomljiv"),
                rs.getInt("kolicina"),
                ProductType.valueOf(rs.getString("tip")),
                BigDecimal.valueOf(rs.getDouble("cena"))
        );
    }

	@Override
	public String toString() {
		return productName;
	}
    
}
