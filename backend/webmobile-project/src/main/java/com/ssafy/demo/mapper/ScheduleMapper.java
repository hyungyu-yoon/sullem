package com.ssafy.demo.mapper;

import com.ssafy.demo.dto.ScheduleDTO;

public interface ScheduleMapper {

	public int insert(ScheduleDTO s);
	public int delete(int scdNo);
	public int update(ScheduleDTO s);
	
}
