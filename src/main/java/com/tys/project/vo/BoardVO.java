package com.tys.project.vo;

import java.util.Date;

public class BoardVO {
	private int board_idx;
	private String board_title;
	private String board_writer;
	private Date board_day;
	private int board_count;
	private String board_content;
	private String department_id;
	private String department_name;
	private String board_modifier;
	private Date board_modifyday;
	
	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public Date getBoard_day() {
		return board_day;
	}

	public void setBoard_day(Date board_day) {
		this.board_day = board_day;
	}

	public int getBoard_count() {
		return board_count;
	}

	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getBoard_modifier() {
		return board_modifier;
	}

	public void setBoard_modifier(String board_modifier) {
		this.board_modifier = board_modifier;
	}

	public Date getBoard_modifyday() {
		return board_modifyday;
	}

	public void setBoard_modifyday(Date board_modifyday) {
		this.board_modifyday = board_modifyday;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	@Override
	public String toString() {
		return "BoardVO [board_idx=" + board_idx + ", board_title=" + board_title + ", board_writer=" + board_writer
				+ ", board_day=" + board_day + ", board_count=" + board_count + ", board_content=" + board_content
				+ ", department_id=" + department_id + ", department_name=" + department_name + ", board_modifier="
				+ board_modifier + ", board_modifyday=" + board_modifyday + "]";
	}
	
	
	
	
	
}