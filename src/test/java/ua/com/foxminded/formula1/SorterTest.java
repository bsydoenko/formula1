package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SorterTest {

	Sorter sorter = new Sorter();

	@Test
	void givenUnsortedList_whenSortRacers_thenSortedList() {
		List<Racer> test = new ArrayList<Racer>();
		test.add(new Racer("FAM"));
		test.add(new Racer("DRR"));
		test.add(new Racer("RGH"));
		test.add(new Racer("CLS"));

		List<Racer> expected = new ArrayList<Racer>();
		expected.add(new Racer("DRR"));
		expected.add(new Racer("FAM"));
		expected.add(new Racer("CLS"));
		expected.add(new Racer("RGH"));

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
		test.add(new Racer("FAM"));

		assertEquals(sorter.sortRacers(test), test);
	}

	@Test
	void givenNull_whenSortRacers_thenIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> sorter.sortRacers(null));
	}
}
