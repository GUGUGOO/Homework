package com.tys.project.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	private PagingVO pag;
	
	public void setPag(PagingVO pag) {
		this.pag = pag;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public PagingVO getPag() {
		return pag;
	}
	
	// 페이지 계산
	private void calcData() {
		endPage = (int) (Math.ceil(pag.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		if(startPage <= 0) startPage = 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double)pag.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * pag.getPerPageNum() >= totalCount ? false : true;
	}
	
	// 페이지 쿼리 작성
	public String makeQuery(int page) {
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
						    .queryParam("page", page)
							.queryParam("perPageNum", pag.getPerPageNum())
							.build();
		   
		return uriComponents.toUriString();
	}
	// 서칭 작성
	public String makeSearch(int page) {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", pag.getPerPageNum())
				.queryParam("searchType", ((PagingSearchVO)pag).getSearchType())
				.queryParam("keyword", encoding(((PagingSearchVO)pag).getKeyword()))
				.build();
		
		return uriComponents.toUriString();
	}
	
	// 인코딩
	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword,"UTF-8");
		}
		catch(UnsupportedEncodingException e) {
			return "";
		}
	}
}