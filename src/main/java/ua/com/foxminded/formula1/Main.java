package ua.com.foxminded.formula1;

public class Main {

	public static void main(String[] args) {
		RacerRepository racerRepository = new RacerRepository();
		BestRacersFormatter bestRacersFormatter = new BestRacersFormatter();
		
		System.out.print(bestRacersFormatter.format(racerRepository.getRacers()));
	}
}
