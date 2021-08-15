package ua.com.foxminded.formula1;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static final String VERTICAL_SEPERATOR = " |";
	public static final String HORIZONTAL_SEPERATOR = "-";
	public static final String SPACE = " ";

	public static void main(String[] args) {
		List<Racer> racers = new ArrayList<Racer>();
		try {
			Scanner scannerAbbreviations = new Scanner(new File("src/main/resources/raceData/abbreviations.txt"));
			while (scannerAbbreviations.hasNext()) {
				racers.add(new Racer(scannerAbbreviations.nextLine().substring(0, 3)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Sorter sorter = new Sorter();
		printResult(sorter.sortRacers(racers));
	}

	private static void printResult(List<Racer> sortedRacers) {
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
					+ VERTICAL_SEPERATOR + racer.getTimeOfLap().format(DateTimeFormatter.ofPattern("mm:ss.nnn")));
		}
	}
}
