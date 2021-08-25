package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BestRacerFormatterTest {
	
	public static final String NEW_LINE = System.lineSeparator();
	
	BestRacersFormatter bestRacersFormatter = new BestRacersFormatter();
	RacerRepository racerRepository = new RacerRepository();
	
	@Test
	void givenNothing_whenFormat_thenSortedResultsOfRacers() {
		assertEquals(" 1. Sebastian Vettel  |FERRARI                   |1:04.415" + NEW_LINE
				+ " 2. Daniel Ricciardo  |RED BULL RACING TAG HEUER |1:12.013" + NEW_LINE
				+ " 3. Valtteri Bottas   |MERCEDES                  |1:12.434" + NEW_LINE
				+ " 4. Lewis Hamilton    |MERCEDES                  |1:12.460" + NEW_LINE
				+ " 5. Stoffel Vandoorne |MCLAREN RENAULT           |1:12.463" + NEW_LINE
				+ " 6. Kimi Raikkonen    |FERRARI                   |1:12.639" + NEW_LINE
				+ " 7. Fernando Alonso   |MCLAREN RENAULT           |1:12.657" + NEW_LINE
				+ " 8. Sergey Sirotkin   |WILLIAMS MERCEDES         |1:12.706" + NEW_LINE
				+ " 9. Charles Leclerc   |SAUBER FERRARI            |1:12.829" + NEW_LINE
				+ "10. Sergio Perez      |FORCE INDIA MERCEDES      |1:12.848" + NEW_LINE
				+ "11. Romain Grosjean   |HAAS FERRARI              |1:12.930" + NEW_LINE
				+ "12. Pierre Gasly      |SCUDERIA TORO ROSSO HONDA |1:12.941" + NEW_LINE
				+ "13. Carlos Sainz      |RENAULT                   |1:12.950" + NEW_LINE
				+ "14. Esteban Ocon      |FORCE INDIA MERCEDES      |1:13.028" + NEW_LINE
				+ "15. Nico Hulkenberg   |RENAULT                   |1:13.065" + NEW_LINE
				+ "----------------------------------------------------------" + NEW_LINE
				+ "16. Brendon Hartley   |SCUDERIA TORO ROSSO HONDA |1:13.179" + NEW_LINE
				+ "17. Marcus Ericsson   |SAUBER FERRARI            |1:13.265" + NEW_LINE
				+ "18. Lance Stroll      |WILLIAMS MERCEDES         |1:13.323" + NEW_LINE
				+ "19. Kevin Magnussen   |HAAS FERRARI              |1:13.393", bestRacersFormatter.format(racerRepository.getRacers()));
	}

}
