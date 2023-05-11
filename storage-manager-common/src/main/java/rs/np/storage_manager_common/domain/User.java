//package domain;
package rs.np.storage_manager_common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class User implements DomainClass{
    private Integer ID;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private WhereClauseMode mode;
 
    public User() {
    }

    public User(Integer id, String name, String lastName, String username, String password) {
        this.ID = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer id) {
    	if(id == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(id < 0 || id > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = id;
    }

    public String getName() {
        return name;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	if(username == null || username.isBlank()) {
    		throw new NullPointerException("Name cannot be set to null.");
    	}
    	if(username.length() < 2 || username.length() > 30) {
    		throw new IllegalArgumentException("Name cannot be set "
    				+ "to less than 2 characters or more than "
    				+ "30 characters.");
    	}
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    	if(password == null || password.isBlank()) {
    		throw new NullPointerException("Name cannot be set to null.");
    	}
    	if(password.length() < 2 || password.length() > 30) {
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

    public void setMode(WhereClauseMode mode) {
        this.mode = mode;
    }

    
    
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
