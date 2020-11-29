package com.tys.project.vo;

import java.util.Date;

public class NoticeVO {
	
	public String Notice_idx;
	public String Notice_title;
	public String Notice_writer;
	public Date Notice_day;
	public Integer Notice_count;
	public String Department_id;
	public String Department_name;
	public String Notice_modifier;
	public Date Notice_modifyday;
	
	public String Notice_content;
	
	public String getNotice_idx() {
		return Notice_idx;
	}
	public void setNotice_idx(String notice_idx) {
		Notice_idx = notice_idx;
	}
	public String getNotice_title() {
		return Notice_title;
	}
	public void setNotice_title(String notice_title) {
		Notice_title = notice_title;
	}
	public String getNotice_writer() {
		return Notice_writer;
	}
	public void setNotice_writer(String notice_writer) {
		Notice_writer = notice_writer;
	}
	public Date getNotice_day() {
		return Notice_day;
	}
	public void setNotice_day(Date notice_day) {
		Notice_day = notice_day;
	}
	public Integer getNotice_count() {
		return Notice_count;
	}
	public void setNotice_count(Integer notice_count) {
		Notice_count = notice_count;
	}
	public String getDepartment_id() {
		return Department_id;
	}
	public void setDepartment_id(String department_id) {
		Department_id = department_id;
	}
	public String getDepartment_name() {
		return Department_name;
	}
	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}
	public String getNotice_modifier() {
		return Notice_modifier;
	}
	public void setNotice_modifier(String notice_modifier) {
		Notice_modifier = notice_modifier;
	}
	public Date getNotice_modifyday() {
		return Notice_modifyday;
	}
	public void setNotice_modifyday(Date notice_modifyday) {
		Notice_modifyday = notice_modifyday;
	}
	public String getNotice_content() {
		return Notice_content;
	}
	public void setNotice_content(String notice_content) {
		Notice_content = notice_content;
	}
	
	
}
