package com.ssafy.demo.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.demo.dto.DaysDTO;
import com.ssafy.demo.dto.RouteDTO;
import com.ssafy.demo.dto.ScheduleDTO;
import com.ssafy.demo.service.ScheduleServiceImpl;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleServiceImpl service;
	
	@PostMapping("insert")
	public Object insert(@RequestBody Map<String,Object> params) {
		System.out.println(params.get("events"));
		return params;
	}
}
