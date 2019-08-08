package com.ssafy.demo.service;

import com.ssafy.demo.dto.ScheduleDTO;

public interface ScheduleService {
	public int insert(ScheduleDTO s);
	public int update(ScheduleDTO s);
	public int delete(int scdNo);
}
