package ua.com.foxminded.formula1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class Racer {
	private String name;
	private String car;
	private Date startTime;
	private Date endTime;
	private LocalTime timeOfLap;

	public Racer() {
	}

	public Racer(String abbreviation) {
		if (abbreviation == null) {
			throw new IllegalArgumentException("abbreviation cannot be null.");
		}

		String[] racerInformation = new String[3];
		Date[] logInformation = new Date[2];
		try {
			racerInformation = getRacerInformation(abbreviation);
			logInformation = getAllLogInformation(abbreviation);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.name = racerInformation[1];
			this.car = racerInformation[2];
			this.startTime = logInformation[0];
			this.endTime = logInformation[1];
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		this.timeOfLap = calculateDifference(startTime, endTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Racer guest = (Racer) obj;
		return (name == guest.name || (name != null && name.equals(guest.getName())))
				&& (car == guest.car || (car != null && car.equals(guest.getCar()))
						&& (startTime.getTime() == guest.startTime.getTime()) && (endTime.equals(guest.endTime)))
				&& (timeOfLap.equals(guest.timeOfLap));
	}

	public String getName() {
		return name;
	}

	public String getCar() {
		return car;
	}

	public LocalTime getTimeOfLap() {
		return timeOfLap;
	}

	private LocalTime calculateDifference(Date startTime, Date endTime) {
		// Calucalte time difference in milliseconds
		long time_difference = endTime.getTime() - startTime.getTime();

		int hours_difference = (int) ((time_difference / (1000 * 60 * 60)) % 24);
		int seconds_difference = (int) (time_difference / 1000) % 60;
		int minutes_difference = (int) ((time_difference - seconds_difference) / 1000) / 60;
		int miliseconds_difference = (int) time_difference - (minutes_difference * 60000 + seconds_difference * 1000);

		return LocalTime.of(hours_difference, minutes_difference, seconds_difference, miliseconds_difference);
	}

	private String[] getRacerInformation(String abbreviation) throws FileNotFoundException {
		Scanner scannerAbbreviations = new Scanner(new File("src/main/resources/raceData/abbreviations.txt"));
		String[] racerInformation = new String[3];
		while (scannerAbbreviations.hasNext()) {
			racerInformation = scannerAbbreviations.nextLine().split("_");

			if (racerInformation[0].equals(abbreviation)) {
				scannerAbbreviations.close();
				return racerInformation;
			}
		}
		scannerAbbreviations.close();
		throw new IllegalArgumentException("abbreviation cannot be founded in file.");
	}

	private Date[] getAllLogInformation(String abbreviation) throws IOException {
		Date[] result = new Date[2];

		result[0] = getLogInformation(new File("src/main/resources/raceData/start.log"), abbreviation);
		result[1] = getLogInformation(new File("src/main/resources/raceData/end.log"), abbreviation);
		return result;
	}

	private Date getLogInformation(File file, String abbreviation) throws FileNotFoundException {
		Scanner scannerLog = new Scanner(file);
		String racerLogInformation = "";
		while (scannerLog.hasNext()) {
			racerLogInformation = scannerLog.nextLine();

			if (racerLogInformation.substring(0, 3).equals(abbreviation)) {
				scannerLog.close();
				try {
					return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.S").parse(racerLogInformation.substring(3));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		scannerLog.close();
		throw new IllegalArgumentException("log information cannot be founded in file.");
	}
}
