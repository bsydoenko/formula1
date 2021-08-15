package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class RacerTest {

	@Test
	void givenNull_whenRacer_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Racer(null));
	}

	@Test
	void givenAbbreviationFromFile_whenRacer_thenClassObjectwithTrueFields() throws ParseException {
		Racer testRacer = new Racer("DRR");
		Racer expectedRacer = new Racer();
		ReflectionTestUtils.setField(expectedRacer, "name", "Daniel Ricciardo");
		ReflectionTestUtils.setField(expectedRacer, "car", "RED BULL RACING TAG HEUER");
		ReflectionTestUtils.setField(expectedRacer, "startTime",
				new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.S").parse("2018-05-24_12:14:12.054"));
		ReflectionTestUtils.setField(expectedRacer, "endTime",
				new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.S").parse("2018-05-24_12:15:24.067"));
		ReflectionTestUtils.setField(expectedRacer, "timeOfLap", LocalTime.of(0, 1, 12, 13));
		assertEquals(testRacer, expectedRacer);
	}

	@Test
	void givenMissingAbbreviationInFile_whenRacer_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Racer("ABC"));
	}

}
