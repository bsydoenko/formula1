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

	public static final int LIMIT_OF_RACERS = 15;

	public String format(List<Racer> racers) {
		List<Racer> sortedRacers = sortRacers(racers);

		int maxLengthName = getMaxFieldLength(sortedRacers, Racer::getName);
		int maxLengthCar = getMaxFieldLength(sortedRacers, Racer::getCar);
		String formatter = "%2d%s%-" + maxLengthName + "s%" + "s%-" + maxLengthCar + "s%s%1d:%02d.%03d";
		String result = "";
		for (Racer racer : sortedRacers) {
			int racerIndex = sortedRacers.indexOf(racer);
			Duration bestLapTime = racer.getBestLapTime();
			if (racerIndex == LIMIT_OF_RACERS) {
				result += (NEW_LINE + HORIZONTAL_SEPERATOR.repeat(
						maxLengthName + maxLengthCar + 14 + String.valueOf(sortedRacers.indexOf(racer) + 1).length()));
			}
			if (result.length() != 0) {
				result += NEW_LINE;
			}
			result += String.format(formatter, racerIndex + 1, POINT, racer.getName(), VERTICAL_SEPERATOR,
					racer.getCar(), VERTICAL_SEPERATOR, bestLapTime.toMinutesPart(), bestLapTime.toSecondsPart(),
					bestLapTime.toMillisPart());
		}
		return result;
	}

	private int getMaxFieldLength(List<Racer> racers, Function<Racer, String> func) {
		return racers.stream().map(foo -> func.apply(foo).length()).max(Integer::compareTo).get();
	}

	private List<Racer> sortRacers(List<Racer> racers) {
		return racers.stream().sorted(Comparator.comparing(Racer::getBestLapTime)).collect(Collectors.toList());
	}
}
