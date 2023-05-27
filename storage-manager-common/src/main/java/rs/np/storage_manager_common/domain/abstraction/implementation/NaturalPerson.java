//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.Buyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Klasa koja opisuje stanje i ponasanje fizickih lica. Nasledjuje klasu {@link Buyer}.
 * @author Milan
 * @since 1.0.0
 */
public class NaturalPerson extends Buyer {
	/**
	 * privatni staticki atribut, serijski broj generisan na zahtev Serializable interfejsa.
	 */
	private static final long serialVersionUID = -992676865909510193L;
	/**
	 * privatni atribut kupac tipa {@link Buyer}. Ovaj atribut postoji zbog toga sto se referenca
	 * na kupca cuva u klasama koje predstavaljaju specijalizaciju od kupca.
	 */
    private Buyer buyer;
    /**
     * privatni atribut ime kupca kao {@link String}
     */
    private String buyerName;
    /**
     * privatni atribut prezime kupca kao {@link String}
     */
    private String buyerLastName;
    /**
     * neparametrizovani konstruktor
     */
    public NaturalPerson() {
    }
    /**
     * 
     * @param ID jedinstveni identifikator, ID, kao tip {@link Integer}
     * @param buyer kupac, tipa {@link Buyer}
     * @param buyerName ime fizickog lica (osobe) kao {@link String}
     * @param buyerLastName prezime fizickog lica (osobe) kao {@link String}
     */
    public NaturalPerson(Integer ID, Buyer buyer, String buyerName, String buyerLastName) {
        this.ID = ID;
        this.buyer = buyer;
        this.buyerName = buyerName;
        this.buyerLastName = buyerLastName;
    }
    /**
     * get metoda za kupca
     * @return kupac kao tip {@link Buyer}
     */
    public Buyer getBuyer() {
        return buyer;
    }
    /**
     * set metoda za kupca
     * @param buyer kupac kao tip {@link Buyer}
     * @throws NullPointerException ako je pokusan unos null vrednosti za kupca.
     */
    public void setBuyer(Buyer buyer) {
    	if(buyer == null) {
    		throw new NullPointerException("Buyer must not be null.");
    	}
        this.buyer = buyer;
    }
    /**
     * get metoda za ime kupca
     * @return ime kupca kao {@link String}
     */
    public String getBuyerName() {
        return buyerName;
    }
    /**
     * set metoda za ime kupca
     * @param buyerName ime kupca kao {@link String} vrednost
     * @throws NullPointerException ako je unet prazan ili null {@link String}
     * @throws IllegalArgumentException ako je uneti {@link String} duzine manje od 2 ili vece od 30
     */
    public void setBuyerName(String buyerName) {
    	if(buyerName == null || buyerName.isBlank()) {
    		throw new NullPointerException("Buyer name must not be null.");
    	}
    	if(buyerName.length() < 2 || buyerName.length() > 30) {
    		throw new IllegalArgumentException("Buyer name must not be less than "
    				+ "2 characters or more than 30 characters.");
    	}
        this.buyerName = buyerName;
    }
    /**
     * get metoda za prezime kupca
     * @return prezime kupca kao {@link String}
     */
    public String getBuyerLastName() {
        return buyerLastName;
    }
    /**
     * set metoda za prezime kupca
     * @param buyerLastName ime kupca kao {@link String} vrednost
     * @throws NullPointerException ako je unet prazan ili null {@link String}
     * @throws IllegalArgumentException ako je uneti {@link String} duzine manje od 2 ili vece od 30
     */
    public void setBuyerLastName(String buyerLastName) {
    	if(buyerLastName == null || buyerLastName.isBlank()) {
    		throw new NullPointerException("Buyer's last name must not be null.");
    	}
    	if(buyerLastName.length() < 2 || buyerLastName.length() > 30) {
    		throw new IllegalArgumentException("Buyer's last name must not be less than "
    				+ "2 characters or more than 30 characters.");
    	}
        this.buyerLastName = buyerLastName;
    }

    @Override
    public String toString() {
        return  buyerName + " " + buyerLastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.buyer);
        hash = 97 * hash + Objects.hashCode(this.buyerName);
        hash = 97 * hash + Objects.hashCode(this.buyerLastName);
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
        final NaturalPerson other = (NaturalPerson) obj;
        if (!Objects.equals(this.buyerName, other.buyerName)) {
            return false;
        }
        if (!Objects.equals(this.buyerLastName, other.buyerLastName)) {
            return false;
        }
        return Objects.equals(this.buyer, other.buyer);
    }
    
    @Override
    public String getTableName() {
        return "fizickoLice";
    }

    @Override
    public String getColumnNames() {
        return "(IDKupca, imeKupca, prezimeKupca)";
    }

    @Override
    public String getValues() {
        return "(IDKupca = " + ID + ", imeKupca = " + buyerName + 
                ", prezimeKupca = " + buyerLastName + ")";
    }

    @Override
    public String getInsertValues() {
        return "(" + buyerName + ", " + buyerLastName + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID){
            return "(IDKupca= " + ID + ")";
        }
        return "true";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(imeKupca, prezimeKupca)";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
//    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Buyer buyer = new Buyer();
        buyer.setID(rs.getInt("IDKupca"));
        return new NaturalPerson(
                rs.getInt("IDKupca"),
                buyer,
                rs.getString("imeKupca"),
                rs.getString("prezimeKupca")
        );
    }
    
}
