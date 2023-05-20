package rs.np.storage_manager_common.domain.abstraction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

import rs.np.storage_manager_common.domain.Firm;

public abstract class AbstractDocumentTest {
	protected AbstractDocument document;
	protected AbstractDocument document1;
	protected DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	void setIDNull() {
		assertThrows(NullPointerException.class,
				()->document.setID(null));
	}
	
	@CsvSource({
		"-1",
		"1000001"
	})
	@ParameterizedTest
	void setIDIllegalArgument(Integer arg) {
		assertThrows(IllegalArgumentException.class,
				()->document.setID(arg));
	}
	
	@Test
	void setIDTestNormal() {
		document.setID(1);
		assertEquals(1, document.getID());
	}
	
	@Test
	//Id, firmname, firmaddress
	void setFirmTestNormal() {
		Firm firm = new Firm(1, "firmName", "firmAddress");
		document.setFirm(firm);
		assertEquals(firm, document.getFirm());
	}
	
	@Test
	void setFirmNull() {
		assertThrows(NullPointerException.class, 
				()->document.setFirm(null));
	}
	
	@Test
	void setIssueDateNull() {
		assertThrows(NullPointerException.class, 
				()->document.setIssueDate(null));
	}
	
	@Test
	void setIssueDateTestAfterToday() {
		try {
			Date newDate = sdf.parse("3001-01-01");
			assertThrows(DateTimeException.class, 
					()->document.setIssueDate(newDate));
		} catch (ParseException e) {}
	}
	
	@Test
	void setIssueDateTestAfterDeadLine() {
		try {
			Date newDate = sdf.parse("3001-01-01");
			document.setDeadLine(newDate);
			assertThrows(DateTimeException.class,
					()->document.setIssueDate(
							sdf.parse("3001-01-02")));
		} catch (ParseException e) {}
	}
	
	@Test
	void setIssueDateTestNormal() {
		try {
			Date date = sdf.parse("2020-01-01");
			document.setIssueDate(date);
			assertEquals(sdf.parse("2020-01-01"),
					document.getIssueDate());
		}catch(ParseException ex) {}
	}
	
	@Test
	void setDeadlineTestNull() {
		assertThrows(NullPointerException.class, 
				()->document.setDeadLine(null));
	}
	
	@Test
	void setDeadlineTestBeforeToday() {
		try {
			Date newDate = sdf.parse("2020-01-01");
			assertThrows(DateTimeException.class, 
					()->document.setDeadLine(newDate));
		} catch (ParseException e) {}
	}
	
	@Test
	void setDeadlineTestBeforeIssueDate() {
		try {
			Date newDate = sdf.parse("3001-01-01");
			document.setDeadLine(newDate);
			assertThrows(DateTimeException.class,
					()->document.setIssueDate(
							sdf.parse("3001-01-02")));
		} catch (ParseException e) {}
	}
	
	@Test
	void setDeadlineTestNormal() {
		try {
			Date date = sdf.parse("3001-01-01");
			document.setDeadLine(date);
			assertEquals(sdf.parse("3001-01-01"),
					document.getDeadLine());
		}catch(ParseException ex) {}
	}
	
	@Test
	void setTotalCostTestNull() {
		assertThrows(NullPointerException.class,
				()->document.setTotalCost(null));
	}
	
	@CsvSource({
		"-1",
		"-2",
		"-10"
	})
	@ParameterizedTest
	void setTotalCostTestIllegalArgument(double num) {
		assertThrows(IllegalArgumentException.class,
				()->document.setTotalCost(new BigDecimal(num)));
	}
	
	@Test
	void setTotalCostTestNormal() {
		document.setTotalCost(new BigDecimal(1000));
		assertEquals(new BigDecimal(1000), document.getTotalCost());
	}
	
	@Test
	void setItemsTestNull() {
		assertThrows(NullPointerException.class,
				()->document.setItems(null));
	}
	
	@Test
	void setSecondParticipantTestNull() {
		assertThrows(NullPointerException.class,
				()->document.setSecondParticipant(null));
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
	
	@CsvSource({
		"1, 1, true",
		"1, 2, false"
	})
	@ParameterizedTest
	void equalsTest(Integer ID1, Integer ID2, boolean result) {
		document.setID(ID1);
		document1.setID(ID2);
		
		assertEquals(result, document.equals(document1));
	}
	
}
