package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;

class RacerRepositoryTest {

	RacerRepository racerRepository = new RacerRepository();

	@Test
	void givenCorrectFiles_whenGetRacers_thenListOfRacers() {
		List<Racer> actual = racerRepository.getRacers("correctAbbreviationsOfThreeRacers.txt",
				"correctStartOfThreeRacers.log", "correctEndOfThreeRacers.log");
		List<Racer> expected = new ArrayList<Racer>();
		expected.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		expected.add(new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		expected.add(new Racer("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));

		assertEquals(expected, actual);
	}

	@Test
	void givenCorrectFilesAndNull_whenGetRacers_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> racerRepository.getRacers(null, "correctStartOfThreeRacers.log", "correctEndOfThreeRacers.log"));
	}

	@Test
	void givenThreeNulls_whenGetRacers_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> racerRepository.getRacers(null, null, null));
	}

	@Test
	void givenNonexistentFiles_whenGetRacers_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> racerRepository.getRacers("blabla.txt", "bla.log", "bla.log"));
	}

	@Test
	void givenUncorrectFiles_whenGetRacers_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> racerRepository.getRacers("incorrectFile.txt", "start.log", "end.log"));
	}
}
