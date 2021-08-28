package ua.com.foxminded.formula1;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BestRacersFormatter {

	public static final String NEW_LINE = System.lineSeparator();
	public static final String SPACE = " ";
	public static final String VERTICAL_SEPERATOR = SPACE + "|";
	public static final String HORIZONTAL_SEPERATOR = "-";
	public static final String POINT = ". ";

	public String format(List<Racer> racers, int limitOfRacers) {
		if (racers == null) {
			throw new IllegalArgumentException("list cannot be null.");
		}
		if (limitOfRacers <= 0) {
			throw new IllegalArgumentException("limitOfRacers cannot be 0 or negative.");
		}
		
		List<Racer> sortedRacers = racers.stream().sorted(Comparator.comparing(Racer::getBestLapTime))
				.collect(Collectors.toList());
		int maxLengthName = getMaxFieldLength(sortedRacers, Racer::getName);
		int maxLengthCar = getMaxFieldLength(sortedRacers, Racer::getCar);
		String formatter = "%2d%s%-" + maxLengthName + "s%" + "s%-" + maxLengthCar + "s%s%1d:%02d.%03d";
		var wrapper = new Object() {
			String result = "";
		};
		sortedRacers.stream().forEach(racer -> {
			int racerIndex = sortedRacers.indexOf(racer);
			Duration bestLapTime = racer.getBestLapTime();
			if (sortedRacers.indexOf(racer) == limitOfRacers) {
				String[] stringsOfResult = wrapper.result.split(NEW_LINE);
				wrapper.result += (NEW_LINE
						+ HORIZONTAL_SEPERATOR.repeat(stringsOfResult[stringsOfResult.length - 1].length()));
			}
			if (wrapper.result.length() != 0) {
				wrapper.result += NEW_LINE;
			}
			wrapper.result += String.format(formatter, racerIndex + 1, POINT, racer.getName(), VERTICAL_SEPERATOR,
					racer.getCar(), VERTICAL_SEPERATOR, bestLapTime.toMinutesPart(), bestLapTime.toSecondsPart(),
					bestLapTime.toMillisPart());
		});
		return wrapper.result;
	}

	private int getMaxFieldLength(List<Racer> racers, Function<Racer, String> func) {
		return racers.stream().map(func::apply).map(String::length).max(Integer::compareTo).get();
	}
}
