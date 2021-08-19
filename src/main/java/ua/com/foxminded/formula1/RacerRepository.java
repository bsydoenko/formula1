package ua.com.foxminded.formula1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class RacerRepository {

	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	Map<String, LocalDateTime> startLogInf = getLogInformation("start.log");
	Map<String, LocalDateTime> endLogInf = getLogInformation("end.log");
	Map<String, String[]> racerInformation = getRacerInformation();

	private Map<String, LocalDateTime> getLogInformation(String fileName) {
		return readFile(fileName)
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> LocalDateTime.parse(s.substring(3), FORMATTER)));
	}

	private Map<String, String[]> getRacerInformation() {
		return readFile("abbreviations.txt")
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> s.substring(4).split("_")));
	}

	private Stream<String> readFile(String fileName) {
		ClassLoader classLoader = RacerRepository.class.getClassLoader();
		Stream<String> lines = null;
		try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
			lines = IOUtils.toString(inputStream, StandardCharsets.UTF_8).lines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public List<Racer> getRacers() {
		List<Racer> racers = new ArrayList<Racer>();
		for (Entry<String, LocalDateTime> entry : startLogInf.entrySet()) {
			racers.add(getRacer(entry.getKey()));
		}

		return racers;
	}

	public Racer getRacer(String abbreviation) {
		Racer racer = new Racer();
		racer.setName(racerInformation.get(abbreviation)[0]);
		racer.setCar(racerInformation.get(abbreviation)[1]);
		racer.setBestLapTime(LocalTime.ofInstant(
				Instant.ofEpochMilli(
						Duration.between(startLogInf.get(abbreviation), endLogInf.get(abbreviation)).toMillis()),
				ZoneId.systemDefault()));

		return racer;
	}
}
