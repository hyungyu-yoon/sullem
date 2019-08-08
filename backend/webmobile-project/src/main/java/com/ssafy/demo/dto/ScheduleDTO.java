package com.ssafy.demo.dto;

import java.util.Arrays;
import java.util.List;

public class ScheduleDTO {
	private String title;
	private String description;
	private DaysDTO[] events;
	public ScheduleDTO(String title, String description, DaysDTO[] events) {
		super();
		this.title = title;
		this.description = description;
		this.events = events;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DaysDTO[] getEvents() {
		return events;
	}
	public void setEvents(DaysDTO[] events) {
		this.events = events;
	}
	@Override
	public String toString() {
		return "{ title:" + title + ", description:" + description + ", events:" + Arrays.toString(events)+"}";
	}

}
