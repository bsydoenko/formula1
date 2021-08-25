package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class RacerRepositoryTest {

	RacerRepository racerRepository = new RacerRepository();

	@Test
	void givenNothing_whenGetRacers_thenListOfRacers() {
		List<Racer> test = racerRepository.getRacers();
		List<Racer> expected = new ArrayList<Racer>();
		expected.add(new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		expected.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		expected.add(new Racer("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));
		expected.add(new Racer("Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
		expected.add(new Racer("Stoffel Vandoorne", "MCLAREN RENAULT", Duration.parse("PT1M12.463S")));
		expected.add(new Racer("Kimi Raikkonen", "FERRARI", Duration.parse("PT1M12.639S")));
		expected.add(new Racer("Fernando Alonso", "MCLAREN RENAULT", Duration.parse("PT1M12.657S")));
		expected.add(new Racer("Sergey Sirotkin", "WILLIAMS MERCEDES", Duration.parse("PT1M12.706S")));
		expected.add(new Racer("Charles Leclerc", "SAUBER FERRARI", Duration.parse("PT1M12.829S")));
		expected.add(new Racer("Sergio Perez", "FORCE INDIA MERCEDES", Duration.parse("PT1M12.848S")));
		expected.add(new Racer("Romain Grosjean", "HAAS FERRARI", Duration.parse("PT1M12.93S")));
		expected.add(new Racer("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M12.941S")));
		expected.add(new Racer("Carlos Sainz", "RENAULT", Duration.parse("PT1M12.95S")));
		expected.add(new Racer("Esteban Ocon", "FORCE INDIA MERCEDES", Duration.parse("PT1M13.028S")));
		expected.add(new Racer("Nico Hulkenberg", "RENAULT", Duration.parse("PT1M13.065S")));
		expected.add(new Racer("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M13.179S")));
		expected.add(new Racer("Marcus Ericsson", "SAUBER FERRARI", Duration.parse("PT1M13.265S")));
		expected.add(new Racer("Lance Stroll", "WILLIAMS MERCEDES", Duration.parse("PT1M13.323S")));
		expected.add(new Racer("Kevin Magnussen", "HAAS FERRARI", Duration.parse("PT1M13.393S")));

		assertTrue(test.size() == expected.size() && 
			    test.containsAll(expected) && expected.containsAll(test));
	}
}
