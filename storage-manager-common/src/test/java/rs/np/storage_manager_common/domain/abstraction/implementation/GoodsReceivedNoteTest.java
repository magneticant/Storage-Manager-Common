package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import rs.np.storage_manager_common.domain.Firm;
import rs.np.storage_manager_common.domain.Partner;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentTest;

class GoodsReceivedNoteTest extends AbstractDocumentTest{

	@BeforeEach
	void setUp() throws Exception {
		document = new GoodsReceivedNote();
	}

	@AfterEach
	void tearDown() throws Exception {
		document = null;
	}

	@Test
	void setSecondParticipantTestNormal() {
		Partner partner = new Partner(1,"businessPartnerName",
				"businessPartnerAddress");
		document.setSecondParticipant(partner);
		assertEquals(partner, document.getSecondParticipant());
	}
	
	@Test
	void setItemsTestNormal() {
		GoodsReceivedNoteItem i1 = new GoodsReceivedNoteItem(1, 1, 
				new Firm(1, "abc123", "firm Address"), new Partner(
						1,"partner name", "partner address"), 10, new Product());
		GoodsReceivedNoteItem i2 = new GoodsReceivedNoteItem(2, 1, 
				new Firm(1, "abc123456", "firm Address123"), new Partner(
						1,"partner name", "partner address"), 10, new Product());
		List<GoodsReceivedNoteItem> items = Arrays.asList(i1,i2);
		document.setItems(items);
		
		assertEquals(items, 
				document.getItems());
	}
	
	@Test
	void addItemTest() {
		GoodsReceivedNoteItem i1 = new GoodsReceivedNoteItem(1, 1, 
				new Firm(1, "abc123", "firm Address"), new Partner(
						1,"partner name", "partner address"), 10, new Product());
		
		document.addItem(i1);
		
		assertEquals(i1,
				document.getItems().get(document.getItems().size()-1));
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
		try {
			document.setID(1);
			var firm = new Firm();
			firm.setID(2);
			document.setFirm(firm);
			document.setSecondParticipant(new Partner());
			document.setIssueDate(sdf.parse("2020-01-01"));
			document.setDeadLine(sdf.parse("3001-01-01"));
			
			assertTrue(document.toString().contains("1"));
			assertTrue(document.toString().contains("2"));
			assertTrue(document.toString().contains("2020"));
			assertTrue(document.toString().contains("3001"));
			assertTrue(document.toString().contains("01"));
		} catch (ParseException e) {}
	}
}
