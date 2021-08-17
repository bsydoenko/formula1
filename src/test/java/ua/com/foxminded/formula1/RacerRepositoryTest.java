package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RacerRepositoryTest {

	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.S");

	RacerRepository racerRepository = new RacerRepository();

	@Test
	void givenNull_whenRacer_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> racerRepository.getRacer(null));
	}

	@Test
	void givenAbbreviationFromFile_whenRacer_thenClassObjectwithTrueFields() throws ParseException {
		Racer test = racerRepository.getRacer("DRR");
		Racer expected = new Racer();
		expected.setName("Daniel Ricciardo");
		expected.setCar("RED BULL RACING TAG HEUER");
		expected.setStartTime(FORMATTER.parse("2018-05-24_12:14:12.054"));
		expected.setEndTime(FORMATTER.parse("2018-05-24_12:15:24.067"));
		expected.setTimeOfLap(LocalTime.of(0, 1, 12, 13));
		assertEquals(test, expected);
	}

	@Test
	void givenMissingAbbreviationInFile_whenRacer_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> racerRepository.getRacer("ABC"));
	}

	@Test
	void givenNothing_whenGetRacers_thenListOfRacers() {
		List<Racer> test = racerRepository.getRacers();
		List<Racer> expected = new ArrayList<Racer>(); 
		expected.add(racerRepository.getRacer("DRR"));
		expected.add(racerRepository.getRacer("SVF"));
		expected.add(racerRepository.getRacer("LHM"));
		expected.add(racerRepository.getRacer("KRF"));
		expected.add(racerRepository.getRacer("VBM"));
		expected.add(racerRepository.getRacer("EOF"));
		expected.add(racerRepository.getRacer("FAM"));
		expected.add(racerRepository.getRacer("CSR"));
		expected.add(racerRepository.getRacer("SPF"));
		expected.add(racerRepository.getRacer("PGS"));
		expected.add(racerRepository.getRacer("NHR"));
		expected.add(racerRepository.getRacer("SVM"));
		expected.add(racerRepository.getRacer("SSW"));
		expected.add(racerRepository.getRacer("CLS"));
		expected.add(racerRepository.getRacer("RGH"));
		expected.add(racerRepository.getRacer("BHS"));
		expected.add(racerRepository.getRacer("MES"));
		expected.add(racerRepository.getRacer("LSW"));
		expected.add(racerRepository.getRacer("KMH"));
		assertEquals(test, expected);
	}

}
