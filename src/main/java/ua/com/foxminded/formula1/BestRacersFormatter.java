package ua.com.foxminded.formula1;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BestRacersFormatter {

	public static final String VERTICAL_SEPERATOR = " |";
	public static final String HORIZONTAL_SEPERATOR = "-";
	public static final String SPACE = " ";

	public void printResult(List<Racer> racers) {
		List<Racer> sortedRacers = sortRacers(racers);

		int maxLengthName = sortedRacers.stream().map(foo -> foo.getName().length()).max(Integer::compareTo).get();
		int maxLengthCar = sortedRacers.stream().map(foo -> foo.getCar().length()).max(Integer::compareTo).get();
		for (Racer racer : sortedRacers) {
			int racerIndex = sortedRacers.indexOf(racer);
			if (racerIndex == 15) {
				System.out.println(HORIZONTAL_SEPERATOR.repeat(
						maxLengthName + maxLengthCar + 13 + String.valueOf(sortedRacers.indexOf(racer) + 1).length()));
			}
			System.out.println((sortedRacers.indexOf(racer) + 1) + "." + racer.getName()
					+ SPACE.repeat(maxLengthName - racer.getName().length()
							- String.valueOf(sortedRacers.indexOf(racer) + 1).length() + 1)
					+ VERTICAL_SEPERATOR + racer.getCar() + SPACE.repeat(maxLengthCar - racer.getCar().length())
					+ VERTICAL_SEPERATOR + racer.getBestLapTime().format(DateTimeFormatter.ofPattern("mm:ss.SSS")));
		}
	}

	private List<Racer> sortRacers(List<Racer> racers) {
		return racers.stream().sorted(Comparator.comparing(Racer::getBestLapTime)).collect(Collectors.toList());
	}
}
