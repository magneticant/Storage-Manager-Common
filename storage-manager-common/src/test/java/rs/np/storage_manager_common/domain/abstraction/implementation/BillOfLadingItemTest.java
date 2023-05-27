package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.WhereClauseMode;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItemTest;
import rs.np.storage_manager_common.domain.abstraction.Buyer;

class BillOfLadingItemTest extends AbstractDocumentItemTest{

	@BeforeEach
	void setUp() throws Exception {
		item = new BillOfLadingItem();
		item1 = new BillOfLadingItem();
	}

	@AfterEach
	void tearDown() throws Exception {
		item = null;
		item1 = null;
	}

	@Test
	void alterStockTestNormal() {
		Product product = new Product();
		product.setAmount(10);
		item.setProduct(product);
		
		assertEquals(5, item.alterStock(5));
	}

	@Test
	void alterStockTakingMoreThanWeHave() {
		Product product = new Product();
		product.setAmount(10);
		item.setProduct(product);
		
		assertEquals(0, item.alterStock(20));
	}
	
	@Test
	void setSecondParticipantTest() {
		Buyer buyer = new Buyer();
		buyer.setID(1);
		buyer.setBuyerAddress("New buyer address");
		buyer.setMode(WhereClauseMode.BY_ID);
		item.setSecondParticipant(buyer);
		
		assertEquals(buyer, item.getSecondParticipant());
	}
	
	
}