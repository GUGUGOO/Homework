package com.tys.project.vo;

public class PagingSearchVO extends PagingVO {
	
	private String searchType = "";
	private String keyword = "";
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "PagingSearchVO [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
}
