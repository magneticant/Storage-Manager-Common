package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.np.storage_manager_common.domain.abstraction.BuyerTest;

class LegalPersonTest extends BuyerTest{
	
	@BeforeEach
	void setUp() throws Exception {
		buyer = new LegalPerson();
	}

	@AfterEach
	void tearDown() throws Exception {
		buyer = null;
	}


}
