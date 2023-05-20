package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import rs.np.storage_manager_common.domain.Firm;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.WhereClauseMode;
import rs.np.storage_manager_common.domain.abstraction.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

class BillOfLadingTest extends AbstractDocumentTest {

	@BeforeEach
	void setUp() throws Exception {
		document = new BillOfLading();
		document1 = new BillOfLading();
	}

	@AfterEach
	void tearDown() throws Exception {
		document = null;
		document1 = null;
	}

	@Test
	void setSecondParticipantTestNormal() {
		Buyer buyer = new Buyer();
		buyer.setID(1);
		buyer.setBuyerAddress("buyerAddress");
		buyer.setMode(WhereClauseMode.BY_ID);
		
		document.setSecondParticipant(buyer);
		assertEquals(buyer, document.getSecondParticipant());
	}
	
	@Test
	void setItemsTestNormal() {
		BillOfLadingItem i1 = new BillOfLadingItem(1, 1, new Buyer(), new Firm() , 
				10, new Product(), WhereClauseMode.BY_ID);
		BillOfLadingItem i2 = new BillOfLadingItem(1, 1, new Buyer(), new Firm() , 
				10, new Product(), WhereClauseMode.BY_USERNAME_PASSWORD);
		List<BillOfLadingItem> items = Arrays.asList(i1, i2);
		document.setItems(items);
		
		assertEquals(items, document.getItems());
	}
	
	@Test
	void addItemTest() {
		BillOfLadingItem i1 = new BillOfLadingItem(1, 1, new Buyer(), new Firm() , 
				10, new Product(), WhereClauseMode.BY_ID);
		document.addItem(i1);
		
		assertEquals(i1, document.getItems().get(document.getItems().size()-1));
	}
	
	@Test
	void equalsTestSameObj() {
		assertTrue(document.equals(document));
	}
	
	@Test
	void equalsTestNullObj() {
		assertFalse(document.equals(null));
	}
	
	@Test
	void equalsTestDifferentClass() {
		assertFalse(document.equals(new Gson()));
	}
	
	
	
	@Test
	void toStringTest() {
		document.setID(1);
		Buyer buyer = new Buyer();
		buyer.setID(2);
		buyer.setBuyerAddress("buyerAddress123");
		buyer.setMode(WhereClauseMode.BY_ID);
		Firm firm = new Firm(3,"firmName","FirmAddress123");
		document.setFirm(firm);
		document.setSecondParticipant(buyer);
		
		assertTrue(document.toString().contains("1"));
		assertTrue(document.toString().contains("2"));
		assertTrue(document.toString().contains("3"));
		assertTrue(document.toString().contains("buyerAddress123"));
		assertTrue(document.toString().contains("firmName"));
	}
}
