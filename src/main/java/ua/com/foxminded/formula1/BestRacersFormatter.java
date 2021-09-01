package ua.com.foxminded.formula1;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.common.collect.Streams;

public class BestRacersFormatter {

	public static final String NEW_LINE = System.lineSeparator();
	public static final String SPACE = " ";
	public static final String VERTICAL_SEPERATOR = SPACE + "|";
	public static final String HORIZONTAL_SEPERATOR = "-";
	public static final String POINT = ". ";

	public String format(List<Racer> racers, int topRacersNumber) {
		if (racers == null) {
			throw new IllegalArgumentException("list cannot be null.");
		}
		if (topRacersNumber <= 0) {
			throw new IllegalArgumentException("limitOfRacers cannot be 0 or negative.");
		}

		int maxLengthName = getter(racers, Racer::getName);
		int maxLengthCar = getter(racers, Racer::getCar);
		String formatter = "%2d%s%-" + maxLengthName + "s%s%-" + maxLengthCar + "s%s%1d:%02d.%03d";

		String result = Streams
				.mapWithIndex(racers.stream().sorted(Comparator.comparing(Racer::getBestLapTime)), (racer, index) -> {
					String stringOfResult = String.format(formatter, index + 1, POINT, racer.getName(),
							VERTICAL_SEPERATOR, racer.getCar(), VERTICAL_SEPERATOR,
							racer.getBestLapTime().toMinutesPart(), racer.getBestLapTime().toSecondsPart(),
							racer.getBestLapTime().toMillisPart());
					if (index == topRacersNumber) {
						return HORIZONTAL_SEPERATOR.repeat(stringOfResult.length()) + NEW_LINE + stringOfResult;
					}
					return stringOfResult;
				}).collect(Collectors.joining(NEW_LINE));

		return result;
	}

	private int getter(List<Racer> racers, Function<Racer, String> func) {
		return racers.stream().map(func).mapToInt(String::length).max().getAsInt();
	}
}
