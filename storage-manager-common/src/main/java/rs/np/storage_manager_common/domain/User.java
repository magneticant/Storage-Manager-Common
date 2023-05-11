//package domain;
package rs.np.storage_manager_common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa koja sadrzi podatke o korisnicima naseg sistema.
 * 
 * @author Milan
 * @since 1.0.0
 */
public class User implements DomainClass{
	/**
	 * privatni atribut ID, koji predstavlja jedinstveni identifikator svakog korisnika sistema
	 */
    private Integer ID;
    /**
     * ime korisnika kao {@link String}
     */
    private String name;
    /**
     * prezime korisnika kao {@link String}
     */
    private String lastName;
    /**
     * korisnicko ime korisnika kao {@link String}
     */
    private String username;
    /**
     * sifra korisnika kao {@link String}
     */
    private String password;
    /**
     * mode po kome se gleda WHERE klauzula u SQL-u, kao tip {@link WhereClauseMode}
     */
    private WhereClauseMode mode;
 	/**
  	 * neparametrizovani konstruktor
  	 */
    public User() {
    }
    /**
     * parametrizovani konstruktor
     * @param id jedinstveni identifikator korisnika kao {@link Integer}
     * @param name ime korisnika kao {@link String}
     * @param lastName prezime korisnika kao {@link String}
     * @param username korisnicko ime kao {@link String} 
     * @param password sifra korisnika kao {@link String}
     */
    public User(Integer id, String name, String lastName, String username, String password) {
        this.ID = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    /**
     * get metoda za ID
     * @return ID kao {@link Integer}
     */
    public Integer getID() {
        return ID;
    }
    /**
     * set metoda za ID
     * @param kao {@link Integer}
     * @throws NullPointerException ako je pokusana dodela null vrednosti.
     * @throws IllegalArgumentException ako je pokusana dodela vrednosti manje od 0 ili vece od milion.
     */
    public void setID(Integer id) {
    	if(id == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(id < 0 || id > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = id;
    }
    /**
     * get metoda za ime korisnika
     * @return name kao {@link String} vrednost
     */
    public String getName() {
        return name;
    }
    /**
     * set metoda za ime korisnika
     * @param name ime kao {@link String}
     * @throws NullPointerException pri pokusaju unosa null vrednosti.
     * @throws IllegalArgumentException pri pokusaju unosa imena kraceg od 2 karaktera ili duzeg od 30.
     */
    public void setName(String name) {
    	if(name == null || name.isBlank()) {
    		throw new NullPointerException("Name cannot be set to null.");
    	}
    	if(name.length() < 2 || name.length() > 30) {
    		throw new IllegalArgumentException("Name cannot be set "
    				+ "to less than 2 characters or more than "
    				+ "30 characters.");
    	}
        this.name = name;
    }
    /**
     * get metoda za prezime
     * @return prezime (lastName) kao {@link String} vrednost.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * set metoda za prezime 
     * @param lastName (prezime) kao {@link String}
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * get metoda za korisnicko ime
     * @return username kao {@link String}
     */
    public String getUsername() {
        return username;
    }
    /**
     * set metoda za korisnicko ime
     * @param username kao {@link String} vrednost
     * @throws NullPointerException ako je korisnik pokusao unos praznog ili null korisnickog imena
     * @throws IllegalArgumentException ako je korisnik pokusao unos korisnickog imena kraceg od 5 karaktera ili duzeg od 30.
     */
    public void setUsername(String username) {
    	if(username == null || username.isBlank()) {
    		throw new NullPointerException("Name cannot be set to null.");
    	}
    	if(username.length() < 5 || username.length() > 30) {
    		throw new IllegalArgumentException("Name cannot be set "
    				+ "to less than 2 characters or more than "
    				+ "30 characters.");
    	}
        this.username = username;
    }
    /**
     * get metoda za sifru korisnika
     * @return password (sifra) kao {@link String}
     */
    public String getPassword() {
        return password;
    }
    /**
     * set metoda za sifru 
     * @param password kao {@link String} vrednost
     * @throws NullPointerException ako je korisnik pokusao unos prazne sifre ili null sifre
     * @throws IllegalArgumentException ako je korisnik pokusao unos sifre krace od 5 karaktera ili duzeg od 30.
     */
    public void setPassword(String password) {
    	if(password == null || password.isBlank()) {
    		throw new NullPointerException("Name cannot be set to null.");
    	}
    	if(password.length() < 5 || password.length() > 30) {
    		throw new IllegalArgumentException("Name cannot be set "
    				+ "to less than 2 characters or more than "
    				+ "30 characters.");
    	}
        this.password = password;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }
    /**
     * set metoda za mod po kojem se generise WHERE klauzula u SQL-u.
     * @param mode kao tip {@link WhereClauseMode}
     */
    public void setMode(WhereClauseMode mode) {
        this.mode = mode;
    }

    
    /**
     * hashCode se racuna za ID, name, lastname, username i password
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.ID);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.lastName);
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.password);
        return hash;
    }
    /**
     * equals se racuna za ID, name, lastname, username i password
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
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + ID + ", name=" + name + ", lastName=" + lastName + ", username=" + username + ", password=" + password + '}';
    }
    
    @Override
    public String getWhereCondition(WhereClauseMode mode){
        if(mode == WhereClauseMode.BY_ID)
            return "(ID=" + ID + ")"; 
        if(mode == WhereClauseMode.BY_USERNAME_PASSWORD)
            return "(username = " + "'" +username + "'" + " &&" + " password = " + "'" +  password + "')";
        
        return "true";
    }

    @Override
    public String getTableName() {
        return "korisnik";
    }

    @Override
    public String getColumnNames() {
        return "ID, ime, prezime, username, password";
    }

    @Override
    public String getValues() {
        return "(ID = " + ID + ", ime = " + name + ", prezime = " + lastName +
                ", username = " + username + ", password = " + password;
    }

    @Override
    public String getInsertValues() {
        return "(" + name + ", " + lastName + ", " + username + ", " + password + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "ime, prezime, username, password";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("ID"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getString("username"),
                rs.getString("password"));
    }
}
