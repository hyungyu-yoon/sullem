package com.ssafy.demo.dto;

import java.util.List;

public class DaysDTO {
	private int no;
	private String name;
	public DaysDTO(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "{no:" + no + ",name:" + name+"}";
	}
	
	
	
}
