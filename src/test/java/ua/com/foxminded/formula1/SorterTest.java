package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SorterTest {

	Sorter sorter = new Sorter();
	RacerRepository racerRepository = new RacerRepository();

	@Test
	void givenUnsortedList_whenSortRacers_thenSortedList() {
		List<Racer> test = new ArrayList<Racer>();
		test.add(racerRepository.getRacer("FAM"));
		test.add(racerRepository.getRacer("DRR"));
		test.add(racerRepository.getRacer("RGH"));
		test.add(racerRepository.getRacer("CLS"));

		List<Racer> expected = new ArrayList<Racer>();
		expected.add(racerRepository.getRacer("DRR"));
		expected.add(racerRepository.getRacer("FAM"));
		expected.add(racerRepository.getRacer("CLS"));
		expected.add(racerRepository.getRacer("RGH"));

		assertEquals(sorter.sortRacers(test), expected);
	}

	@Test
	void givenEmptyList_whenSortRacers_thenEmptyList() {
		List<Racer> test = new ArrayList<Racer>();
		List<Racer> expected = new ArrayList<Racer>();

		assertEquals(sorter.sortRacers(test), expected);
	}

	@Test
	void givenListWhithOneObject_whenSortRacers_thenTheSameList() {
		List<Racer> test = new ArrayList<Racer>();
		test.add(racerRepository.getRacer("FAM"));

		assertEquals(sorter.sortRacers(test), test);
	}

	@Test
	void givenNull_whenSortRacers_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> sorter.sortRacers(null));
	}
}
