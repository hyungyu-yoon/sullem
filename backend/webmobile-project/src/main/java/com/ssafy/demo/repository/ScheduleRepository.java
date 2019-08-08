package com.ssafy.demo.repository;

import com.ssafy.demo.dto.ScheduleDTO;

public interface ScheduleRepository {
	public int insert(ScheduleDTO s);
	public int update(ScheduleDTO s);
	public int delete(int scdNo);
	
}
