package rs.np.storage_manager_common.domain.abstraction.implementation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import rs.np.storage_manager_common.domain.abstraction.BuyerTest;

class NaturalPersonTest extends BuyerTest{

	@BeforeEach
	void setUp() throws Exception {
		buyer = new NaturalPerson();
	}

	@AfterEach
	void tearDown() throws Exception {
		buyer = null;
	}


}
