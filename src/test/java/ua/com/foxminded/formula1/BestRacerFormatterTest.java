package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BestRacerFormatterTest {

	public static final String NEW_LINE = System.lineSeparator();

	BestRacersFormatter bestRacersFormatter = new BestRacersFormatter();

	@Test
	void givenListOfThreeRacersAndLimitTwo_whenFormat_thenSortedResultsOfRacers() {
		List<Racer> racers = new ArrayList<Racer>();
		racers.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		racers.add(new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));

		String expected = " 1. Sebastian Vettel |FERRARI                   |1:04.415" + NEW_LINE
				+ " 2. Daniel Ricciardo |RED BULL RACING TAG HEUER |1:12.013" + NEW_LINE
				+ "---------------------------------------------------------" + NEW_LINE
				+ " 3. Valtteri Bottas  |MERCEDES                  |1:12.434";
		assertEquals(expected, bestRacersFormatter.format(racers, 2));
	}

	@Test
	void givenListOfOneRacrAndLimitOne_whenFormat_thenResultOfRacer() {
		List<Racer> racers = new ArrayList<Racer>();
		racers.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));

		String expected = " 1. Daniel Ricciardo |RED BULL RACING TAG HEUER |1:12.013";
		assertEquals(expected, bestRacersFormatter.format(racers, 1));
	}
	
	@Test
	void givenLimitMoreThenNumbersOfRacers_whenFormat_thenSortedResultsOfRacersWithoutSeperator() {
		List<Racer> racers = new ArrayList<Racer>();
		racers.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		racers.add(new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));

		String expected = " 1. Sebastian Vettel |FERRARI                   |1:04.415" + NEW_LINE
				+ " 2. Daniel Ricciardo |RED BULL RACING TAG HEUER |1:12.013" + NEW_LINE
				+ " 3. Valtteri Bottas  |MERCEDES                  |1:12.434";
		assertEquals(expected, bestRacersFormatter.format(racers, 4));
	}
	
	@Test
	void givenNegativeLimitOfRacers_whenFormat_thenIllegalArgumentException() {
		List<Racer> racers = new ArrayList<Racer>();
		racers.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		racers.add(new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> bestRacersFormatter.format(racers, -2));
	}
	
	@Test
	void givenLimitOfRacersZero_whenFormat_thenIllegalArgumentException() {
		List<Racer> racers = new ArrayList<Racer>();
		racers.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		racers.add(new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> bestRacersFormatter.format(racers, 0));
	}

	@Test
	void givenNull_whenFormat_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> bestRacersFormatter.format(null, 0));
	}
}
