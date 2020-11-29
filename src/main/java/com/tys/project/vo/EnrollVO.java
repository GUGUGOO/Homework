package com.tys.project.vo;

import org.springframework.web.multipart.MultipartFile;

public class EnrollVO extends PagingSearchVO {
	
		private String Enroll_id;
	   private String Enroll_password;
	   private String Enroll_name;
	   private String Enroll_birth;
	   private String Enroll_phone;
	   private String Department_id;
	   private String Enroll_mail;
	   private String Enroll_image;
	   private MultipartFile Enroll_image_file;
	   private Integer Enroll_count;
	   private Boolean Enroll_isActivate;
	   private String Enroll_category;
	   private String Enroll_depart_id;
	   private String department_name;	

	
	   public MultipartFile getEnroll_image_file() {
			return Enroll_image_file;
		}
		public void setEnroll_image_file(MultipartFile enroll_image_file) {
			Enroll_image_file = enroll_image_file;
		}
		
	public String getEnroll_id() {
		return Enroll_id;
	}
	public void setEnroll_id(String enroll_id) {
		Enroll_id = enroll_id;
	}
	public String getEnroll_password() {
		return Enroll_password;
	}
	public void setEnroll_password(String enroll_password) {
		Enroll_password = enroll_password;
	}
	public String getEnroll_name() {
		return Enroll_name;
	}
	public void setEnroll_name(String enroll_name) {
		Enroll_name = enroll_name;
	}
	public String getEnroll_birth() {
		return Enroll_birth;
	}
	public void setEnroll_birth(String enroll_birth) {
		Enroll_birth = enroll_birth;
	}
	public String getEnroll_phone() {
		return Enroll_phone;
	}
	public void setEnroll_phone(String enroll_phone) {
		Enroll_phone = enroll_phone;
	}
	public String getDepartment_id() {
		return Department_id;
	}
	public void setDepartment_id(String department_id) {
		Department_id = department_id;
	}
	public String getEnroll_mail() {
		return Enroll_mail;
	}
	public void setEnroll_mail(String enroll_mail) {
		Enroll_mail = enroll_mail;
	}
	public String getEnroll_image() {
		return Enroll_image;
	}
	public void setEnroll_image(String enroll_image) {
		Enroll_image = enroll_image;
	}
	public Integer getEnroll_count() {
		return Enroll_count;
	}
	public void setEnroll_count(Integer enroll_count) {
		Enroll_count = enroll_count;
	}
	public Boolean getEnroll_isActivate() {
		return Enroll_isActivate;
	}
	public void setEnroll_isActivate(Boolean enroll_isActivate) {
		Enroll_isActivate = enroll_isActivate;
	}
	public String getEnroll_category() {
		return Enroll_category;
	}
	public void setEnroll_category(String enroll_category) {
		Enroll_category = enroll_category;
	}
	public String getEnroll_depart_id() {
		return Enroll_depart_id;
	}
	public void setEnroll_depart_id(String enroll_depart_id) {
		Enroll_depart_id = enroll_depart_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	@Override
	public String toString() {
		return "EnrollVO [Enroll_id=" + Enroll_id + ", Enroll_password=" + Enroll_password + ", Enroll_name="
				+ Enroll_name + ", Enroll_birth=" + Enroll_birth + ", Enroll_phone=" + Enroll_phone + ", Department_id="
				+ Department_id + ", Enroll_mail=" + Enroll_mail + ", Enroll_image=" + Enroll_image + ", Enroll_count="
				+ Enroll_count + ", Enroll_isActivate=" + Enroll_isActivate + ", Enroll_category=" + Enroll_category
				+ ", Enroll_depart_id=" + Enroll_depart_id + ", department_name=" + department_name
				+ ", getEnroll_id()=" + getEnroll_id() + ", getEnroll_password()=" + getEnroll_password()
				+ ", getEnroll_name()=" + getEnroll_name() + ", getEnroll_birth()=" + getEnroll_birth()
				+ ", getEnroll_phone()=" + getEnroll_phone() + ", getDepartment_id()=" + getDepartment_id()
				+ ", getEnroll_mail()=" + getEnroll_mail() + ", getEnroll_image()=" + getEnroll_image()
				+ ", getEnroll_count()=" + getEnroll_count() + ", getEnroll_isActivate()=" + getEnroll_isActivate()
				+ ", getEnroll_category()=" + getEnroll_category() + ", getEnroll_depart_id()=" + getEnroll_depart_id()
				+ ", getDepartment_name()=" + getDepartment_name() + "]";
	}
}