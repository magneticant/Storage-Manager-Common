package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.np.storage_manager_common.domain.Partner;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItemTest;

class GoodsReceivedNoteItemTest extends AbstractDocumentItemTest{
	
	@BeforeEach
	void setUp() throws Exception {
		item = new GoodsReceivedNoteItem();
	}

	@AfterEach
	void tearDown() throws Exception {
		item = null;
	}

	
	@Test
	void alterStockTestNormal() {
		Product product = new Product();
		product.setAmount(10);
		item.setProduct(product);
		
		assertEquals(15, item.alterStock(5));
	}
	
	@Test
	void setSecondParticipantTest() {
		Partner partner = new Partner(1,"businessName1","businessAddress1");
		item.setSecondParticipant(partner);
		
		assertEquals(partner, item.getSecondParticipant());
	}
}
