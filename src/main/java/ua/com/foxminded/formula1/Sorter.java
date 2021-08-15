package ua.com.foxminded.formula1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

	public List<Racer> sortRacers(List<Racer> racers) {
		if (racers == null) {
			throw new IllegalArgumentException("List racers cannot be null.");
		}
		return racers.stream().sorted(Comparator.comparing(Racer::getTimeOfLap)).collect(Collectors.toList());
	}
}
