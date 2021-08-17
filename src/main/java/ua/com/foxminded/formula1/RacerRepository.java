package ua.com.foxminded.formula1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;

public class RacerRepository {

	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.S");

	ClassLoader loader = Racer.class.getClassLoader();
	
	public List<Racer> getRacers(){
		List<Racer> racers = new ArrayList<Racer>();
		try {
			ClassLoader loader = Main.class.getClassLoader();
			Scanner scannerAbbreviations = new Scanner(new File(loader.getResource("abbreviations.txt").getFile()));
			while (scannerAbbreviations.hasNext()) {
				racers.add(getRacer(scannerAbbreviations.nextLine().substring(0, 3)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return racers;
	}
	
	public Racer getRacer(String abbreviation) {
		Racer racer = new Racer();
		String[] racerInformation = new String[3];
		Date[] logInformation = new Date[2];
		try {
			racerInformation = getRacerInformation(abbreviation);
			logInformation = getAllLogInformation(abbreviation);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			racer.setName(racerInformation[1]);
			racer.setCar(racerInformation[2]);
			racer.setStartTime(logInformation[0]);
			racer.setEndTime(logInformation[1]);
			racer.setTimeOfLap(calculateDifference(racer.getStartTime(), racer.getEndTime()));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return racer;
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
		Scanner scannerAbbreviations = new Scanner(new File(loader.getResource("abbreviations.txt").getFile()));
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

		result[0] = getLogInformation(new File(loader.getResource("start.log").getFile()), abbreviation);
		result[1] = getLogInformation(new File(loader.getResource("end.log").getFile()), abbreviation);
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
					return FORMATTER.parse(racerLogInformation.substring(3));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		scannerLog.close();
		throw new IllegalArgumentException("log information cannot be founded in file.");
	}
}
