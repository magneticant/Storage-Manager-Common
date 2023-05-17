package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;
import rs.np.storage_manager_common.domain.abstraction.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;

class BillOfLadingTest extends AbstractDocumentTest {

	@BeforeEach
	void setUp() throws Exception {
		document = new BillOfLading();
	}

	@AfterEach
	void tearDown() throws Exception {
		document = null;
	}


}
