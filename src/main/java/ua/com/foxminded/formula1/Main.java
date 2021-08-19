package ua.com.foxminded.formula1;

public class Main {

	public static void main(String[] args) {
		RacerRepository racerRepository = new RacerRepository();
		BestRacersFormatter bestRacersFormatter = new BestRacersFormatter();

		bestRacersFormatter.printResult(racerRepository.getRacers());
	}
}
