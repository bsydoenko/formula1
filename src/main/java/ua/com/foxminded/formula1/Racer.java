package ua.com.foxminded.formula1;

import java.time.Duration;
import java.util.Objects;

public class Racer {

	private String name;
	private String car;
	private Duration bestLapTime;

	public Racer(String name, String car, Duration bestLapTime) {
		this.name = name;
		this.car = car;
		this.bestLapTime = bestLapTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public Duration getBestLapTime() {
		return bestLapTime;
	}

	public void setBestLapTime(Duration bestLapTime) {
		this.bestLapTime = bestLapTime;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Racer))
			return false;
		Racer other = (Racer) o;
		boolean currencyName = (this.name == null && other.name == null)
				|| (this.name != null && this.name.equals(other.name));
		boolean currencyCar = (this.car == null && other.car == null)
				|| (this.car != null && this.car.equals(other.car));
		return  currencyName && currencyCar && (this.bestLapTime.equals(other.bestLapTime));
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, car, bestLapTime);
	}
}
