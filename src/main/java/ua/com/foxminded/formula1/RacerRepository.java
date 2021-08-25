package ua.com.foxminded.formula1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RacerRepository {

	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	private Map<String, LocalDateTime> getLogInformation(String fileName) {
		return readFile(fileName)
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> LocalDateTime.parse(s.substring(3), FORMATTER)));
	}

	private Stream<String> readFile(String fileName) {
		Stream<String> lines = null;
		try (InputStream inputStream = RacerRepository.class.getClassLoader().getResourceAsStream(fileName)) {
			lines = IOUtils.toString(inputStream, StandardCharsets.UTF_8).lines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public List<Racer> getRacers() {
		Map<String, LocalDateTime> startLogInf = getLogInformation("start.log");
		Map<String, LocalDateTime> endLogInf = getLogInformation("end.log");

		List<Racer> racers = new ArrayList<Racer>();
		readFile("abbreviations.txt").map(s->s.split("_")).forEach(s->racers.add(getRacer(s[1], s[2], startLogInf.get(s[0]), endLogInf.get(s[0]))));
		return racers;
	}

	public Racer getRacer(String name, String car, LocalDateTime startTime, LocalDateTime endTime) {
		Racer racer = new Racer(name, car, Duration.between(startTime, endTime));
		return racer;
	}
}
