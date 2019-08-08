package com.ssafy.demo.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.demo.dto.ScheduleDTO;
import com.ssafy.demo.mapper.ScheduleMapper;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

	@Autowired
	private SqlSessionTemplate template;

	@Override
	public int insert(ScheduleDTO s) {
		ScheduleMapper mapper = template.getMapper(ScheduleMapper.class);
		return mapper.insert(s);
	}

	@Override
	public int update(ScheduleDTO s) {
		return 0;
	}

	@Override
	public int delete(int scdNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
