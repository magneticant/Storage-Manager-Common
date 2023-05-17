package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
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


}
