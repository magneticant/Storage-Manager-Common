//package domain;
package rs.np.storage_manager_common.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class Product implements DomainClass {
    private Integer ID;
    private String productName;
    private Double weight;
    private Boolean fragile;
    private Integer amount;
    private ProductType type;
    private BigDecimal price;
    private WhereClauseMode mode;
    
    public Product() {
    }

    public Product(Integer ID, String productName, Double weight, Boolean fragile, Integer amount, ProductType type, BigDecimal price) {
        this.ID = ID;
        this.productName = productName;
        this.weight = weight;
        this.fragile = fragile;
        this.amount = amount;
        this.type = type;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer productID) {
    	if(productID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(productID < 0 || productID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
    	if(productName == null || productName.isBlank()) {
    		throw new NullPointerException("Product name cannot be left blank.");
    	}
    	if(productName.length() <2 || productName.length()>30) {
    		throw new IllegalArgumentException("Product name cannot be less than 2 or longer than 30");
    	}
        this.productName = productName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
    	if(weight == null) {
    		throw new NullPointerException("Weight cannot be null.");
    	}
    	if(weight < 0) {
    		throw new IllegalArgumentException("Weigth cannot be negative");
    	}
        this.weight = weight;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
    	if(fragile == null) {
    		throw new NullPointerException("Fragile is a boolean value"
    				+ " and as such can only be true or false.");
    	}
        this.fragile = fragile;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
    	if(amount == null) {
    		throw new NullPointerException("Stock cannot be null.");
    	}
    	if(amount < 0) {
    		throw new IllegalArgumentException("Stocks of value less than 0 are not allowed.");
    	}
        this.amount = amount;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
    	if(type == null) {
    		throw new NullPointerException("Product type must be set with this method.");
    	}
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
    	if(price == null) {
    		throw new NullPointerException("Price cannot be undefined.");
    	}
    	if(price.intValue() < 0) {
    		throw new IllegalArgumentException("Price is never negative.");
    	}
        this.price = price;
    }

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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ID);
        hash = 97 * hash + Objects.hashCode(this.productName);
        hash = 97 * hash + Objects.hashCode(this.weight);
        hash = 97 * hash + Objects.hashCode(this.fragile);
        hash = 97 * hash + Objects.hashCode(this.amount);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.price);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        if (!Objects.equals(this.fragile, other.fragile)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.price, other.price);
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
