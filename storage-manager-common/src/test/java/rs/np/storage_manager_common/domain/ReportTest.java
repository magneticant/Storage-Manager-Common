package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportTest extends DomainClassTest{
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@BeforeEach
	void setUp() throws Exception {
		Product product = new Product(2, "prodTest", 10.0, false, 1, ProductType.Carpets, new BigDecimal(10));
		ReportItem reportItem = new ReportItem(1, sdf.parse("2022-01-01"), 10.0, product);
		List<ReportItem> items = Arrays.asList(reportItem);
		domainClass = new Report(sdf.parse("2022-01-01"), 88.9, items);
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
}
