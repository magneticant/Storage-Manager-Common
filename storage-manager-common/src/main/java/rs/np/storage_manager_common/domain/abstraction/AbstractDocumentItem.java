//package domain.abstraction;
package rs.np.storage_manager_common.domain.abstraction;

import rs.np.storage_manager_common.domain.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Apstraktna klasa koja opisuje stanje i ponasanje razlicitih stavki poslovnih dokumenata 
 * nastalih poslovnom saradnjom nase firme i drugih lica
 * @author Milan
 * @since 1.0.0
 */
public abstract class AbstractDocumentItem implements Serializable, DomainClass {
	/**
	 * protected (zasticeni) atribut ID klase {@link AbstractDocumentItem}, kao tip {@link Integer}
	 */
    protected Integer ID;
    /**
     * protected (zasticeni) atribut ID klase dokumenta kojem stavka pripada. Tip je {@link Integer}
     */
    protected Integer documentID;
    /**
     * protected (zasticeni) atribut firm, tipa {@link Firm}, koji predstavlja firmu na koju se odnosi data stavka
     * dokumenta.
     */
    protected Firm firm;
    /**
     * protected (zasticeni) artibut kolicina, predstavlja kolicinu robe koja se porucuje/dostavlja/evidentira/salje i sl. 
     * tip: {@link Integer}
     */
    protected Integer amount;
    /**
     * protected (zasticeni) atribut artikal, predstavlja artikal koji se porucuje/dostavlja/evidentira/salje i sl.
     */
    protected Product product;
    /**
     * protected (zasticeni) atribut na osnovu kojeg se odredjuje mod za WHERE klauzulu prilikom SQL upita
     */
    protected WhereClauseMode mode;
    /**
     * get metoda za ID
     * @return ID kao {@link Integer}
     */
    public abstract Integer getID();
    /**
     * set metoda za ID
     * @param ID kao {@link Integer} vrednost
     */
    public abstract void setID(Integer ID);
    /**
     * get metoda za ID poslovnog dokumenta ciji je ova stavka clan
     * @return documentID kao {@link Integer}
     */
    public abstract Integer getDocumentID();
    /**
     * set metoda za ID poslovnog dokumenta ciji je ova stavka clan
     * @param documentID kao {@link Integer}
     */
    public abstract void setDocumentID(Integer documentID);
    /**
     * get metoda za firmu
     * @return firm kao objekat klase {@link Firm}
     */
    public abstract Firm getFirm();
    /**
     * set metoda za firmu
     * @param firm firma koja se dodeljuje, kao tip {@link Firm}
     */
    public abstract void setFirm(Firm firm);
    /**
     * get metoda za kolicinu robe koja se porucuje/dostavlja/evidentira/salje i sl.
     * @return kolicina robe kao {@link Integer}
     */
    public abstract Integer getAmount();
    /**
     * get metoda za kolicinu robe koja se porucuje/dostavlja/evidentira/salje i sl.
     * @param amount kolicina robe kao {@link Integer}
     */
    public abstract void setAmount(Integer amount);
    /**
     * get metoda za artikal koji koji se porucuje/dostavlja/evidentira/salje i sl.
     * @return artikal kao tip {@link Product}
     */
    public abstract Product getProduct();
    /**
     * set metoda za artikal koji koji se porucuje/dostavlja/evidentira/salje i sl.
     * @param product artikal kao tip {@link Product}
     */
    public abstract void setProduct(Product product);
    /**
     * get metoda za drugu stranu poslovne saradnje (moze biti kupac, prodavac, dobavljac itd.)
     * @return drugi ucesnik kao tip {@link DomainClass}
     */
    public abstract DomainClass getSecondParticipant();
    /**
     * set metoda za drugu stranu poslovne saradnje (moze biti kupac, prodavac, dobavljac itd.)
     * @param product artikal kao tip {@link Product}
     */
    public abstract void setSecondParticipant(DomainClass participant);
    /**
     * Strategy pattern metoda koja na osnovu zadatog broja artikala "zakljucuje" nacin na koji treba promeniti 
     * stanje robe na skladistu.
     * @param stock broj artikala u stavki dokumenta kao int vrednost.
     * @return novo stanje na skladistu posle racunanja (kao int)
     */
    public abstract int alterStock(int stock);
    /**
     * hashCode se racuna za ID i documentID
     */
	@Override
	public int hashCode() {
		return Objects.hash(ID, documentID);
	}
	/**
	 * equals se racuna za ID i documentID
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDocumentItem other = (AbstractDocumentItem) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(documentID, other.documentID);
	}
    
    
}
