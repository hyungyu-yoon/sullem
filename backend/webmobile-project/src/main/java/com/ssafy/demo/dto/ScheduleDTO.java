package com.ssafy.demo.dto;

import java.util.List;

public class ScheduleDTO {
	private String title;
	private String description;
	private int startevents;
	private String events;
	private String createevents;
	public ScheduleDTO(String title, String description, int startevents, String events, String createevents) {
		this.title = title;
		this.description = description;
		this.startevents = startevents;
		this.events = events;
		this.createevents = createevents;
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
	public int getStartevents() {
		return startevents;
	}
	public void setStartevents(int startevents) {
		this.startevents = startevents;
	}
	public String getEvents() {
		return events;
	}
	public void setEvents(String events) {
		this.events = events;
	}
	public String getCreateevents() {
		return createevents;
	}
	public void setCreateevents(String createevents) {
		this.createevents = createevents;
	}
	@Override
	public String toString() {
		return "ScheduleDTO [title=" + title + ", description=" + description + ", startevents=" + startevents
				+ ", events=" + events + ", createevents=" + createevents + "]";
	}

}
