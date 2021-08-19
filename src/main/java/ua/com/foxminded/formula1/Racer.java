package ua.com.foxminded.formula1;

import java.time.LocalTime;

public class Racer {

	private String name;
	private String car;
	private LocalTime bestLapTime;

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

	public LocalTime getBestLapTime() {
		return bestLapTime;
	}

	public void setBestLapTime(LocalTime timeOfLap) {
		this.bestLapTime = timeOfLap;
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
}
