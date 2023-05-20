//package domain.abstraction;
package rs.np.storage_manager_common.domain.abstraction;

import rs.np.storage_manager_common.domain.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Apstraktna klasa koja opisuje stanje i ponasanje razlicitih poslovnih dokumenata 
 * nastalih poslovnom saradnjom nase firme i drugih lica
 * @author Milan
 * @since 1.0.0
 */
public abstract class AbstractDocument implements Serializable, DomainClass {
	/**
	 * protected atribut ID klase {@link AbstractDocument}, kao tip {@link Integer}
	 */
	protected Integer ID;
	/**
	 * zasticeni (protected scope) atribut firma, ovo predstavlja nasu firmu, kao tip {@link Firm}
	 */
    protected Firm firm;
    /**
     * datum izdavanja (protected), kao {@link Date}
     */
    protected Date issueDate;
    /**
     * rok isplate (protected), kao {@link Date}
     */
    protected Date Deadline;
    /**
     * ukupna cena za isplatu data kao {@link BigDecimal} vrednost
     */
    protected BigDecimal totalCost;
    /**
     * mod po kojem se odredjuje WHERE klauzula u SQL-u, kao protected {@link WhereClauseMode} vrednost
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
     * @throws NullPointerException ako se unese null vrednost
     * @throws IllegalArgumentException ako se unese vrednost manja od 0 ili veca od 1000000
     */
    public abstract void setID(Integer ID);
    /**
     * get metoda za firmu
     * @return firm kao objekat klase {@link Firm}
     */
    public abstract Firm getFirm();
    /**
     * set metoda za firmu
     * @param firm firma koja se dodeljuje, kao tip {@link Firm}
     * @throws NullPointerException ako se unese null vrednost
     */
    public abstract void setFirm(Firm firm);
    /**
     * get metoda za datum izdavanja dokumenta
     * @return issueDate kao tip {@link Date}
     * 
     */
    public abstract Date getIssueDate();
    /**
     * set metoda za datum izdavanja dokumenta
     * @param date kao datumski tip (vidi:{@link Date})
     * @throws NullPointerException ako se unese null za datum
     * @throws DateTimeException ako se unese datum u buducnosti
     * @throws DateTimeException ako je vec unet rok za isplatu a za datum izdavanja se unese datum posle roka za uplatu.
     */
    public abstract void setIssueDate(Date date);
    /**
     * get metoda za datum dospelosti potrazivanja
     * @return date kao datumski tip (vidi:{@link Date})
     */
    public abstract Date getDeadLine();
    /**
     * set metoda za datum dospelosti potrazivanja
     * @param date kao datumski tip (vidi:{@link Date})
     */
    public abstract void setDeadLine(Date date);
    /**
     * get metoda za ukupnu cenu
     * @return ukupna cena tipa {@link BigDecimal}
     * @throws NullPointerException ako se unese null vrednost za datum
     * @throws DateTimeException ako se unese datum u proslosti
     * @throws DateTimeException ako je vec unet rok izdavanja a za datum za uplatu se unese pre datuma izdavanja.
     */
    public abstract BigDecimal getTotalCost();
    /**
     * set metoda za ukupnu cenu
     * @param totalCost ukupna cena za placanje kao {@link BigDecimal}
     * @throws NullPointerException ako se unese null vrednost
     * @throws IllegalArgumentException ako se unese ukupna cena manja od nule
     */
    public abstract void setTotalCost(BigDecimal totalCost);
    /**
     * get metoda za stavke dokumenta
     * @return lista stavki dokumenta ({@link AbstractDocumentItem})
     */
    public abstract List<? extends AbstractDocumentItem> getItems();
    /**
     * set metoda za stavke dokumenta
     * @param items kao lista stavki dokumenta ({@link AbstractDocument})
     * @throws NullPointerException ako se unese null vrednost
     */
    public abstract void setItems(List<? extends AbstractDocumentItem> items);
    /**
     * get metoda za drugog ucesnika u poslovnoj saradnji
     * @return objekat tipa {@link DomainClass} koji predstavlja drugog ucesnika u poslovnoj saradnji
     */
    public abstract DomainClass getSecondParticipant();
    /**
     * set metoda za drugog ucesnika u poslovnoj saradnji
     * @param participant tipa {@link DomainClass} koji predstavlja drugog ucesnika u poslovnoj saradnji
     * @throws NullPointerException ako se unese null vrednost
     */
    public abstract void setSecondParticipant(DomainClass participant);
    /**
     * Metoda za dodavanje stavke izvestaja. U zavisnosti od tipa stavke, svaka klasa koja nasledjuje ovu klasu
     * ce dodati stavku odgovarajuceg tipa (nadtip {@link AbstractDocumentItem})
     * @param item tipa {@link AbstractDocumentItem}, stavka poslovnog dokumenta za dodavanje
     */
	public abstract void addItem(AbstractDocumentItem item);
	/**
	 * hashCode se racuna iskljucivo za ID
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}
	/**
	 * equals metoda se racuna iskljucivo po ID-ju
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDocument other = (AbstractDocument) obj;
		return Objects.equals(ID, other.ID);
	}
	
}
