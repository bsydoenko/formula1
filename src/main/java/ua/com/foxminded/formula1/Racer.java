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

	public void setBestLapTime(Duration BestLapTime) {
		this.bestLapTime = BestLapTime;
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
				&& (car == guest.car || (car != null && car.equals(guest.getCar())))
				&& (bestLapTime.equals(guest.bestLapTime));
	}

	@Override
    public int hashCode() {
        return Objects.hash(name, car, bestLapTime);
    }
}
