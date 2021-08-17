package ua.com.foxminded.formula1;

import java.time.LocalTime;
import java.util.Date;

public class Racer {

	private String name;
	private String car;
	private Date startTime;
	private Date endTime;
	private LocalTime timeOfLap;

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

	public LocalTime getTimeOfLap() {
		return timeOfLap;
	}

	public void setTimeOfLap(LocalTime timeOfLap) {
		this.timeOfLap = timeOfLap;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
}
