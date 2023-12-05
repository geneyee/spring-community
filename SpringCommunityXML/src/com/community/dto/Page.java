package com.community.dto;

public class Page {
	
	// 최소 페이지 번호
	private int min;
	
	// 최대 페이지 번호
	private int max;
	
	// 이전 버튼 페이지 번호
	private int prevPage;
	
	// 다음 버튼 페이지 번호
	private int nextPage;
	
	// 전체 페이지 수
	private int pageCnt;
	
	// 현재 페이지 번호
	private int currentPage;
	
	// contentCnt : 전체 글 수, contentPageCnt : 페이지 당 글 수, paginationCnt : 페이지 버튼의 수
	public Page(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		// 현재 페이지 번호
		this.currentPage = currentPage;
		
		// 전체 페이지 수 = 전체 글의 수 / 페이지 당 글의 수
		pageCnt = contentCnt / contentPageCnt;
		
		// 글 13개 있는 경우 가정 -> 13/10 = 1 이지만 글 3개 남음 -> 1페이지가 아니고 2페이지가 되어야한다.
		if(contentCnt % contentPageCnt > 0) {
			pageCnt++;
		}
		
		min = ((currentPage - 1) / paginationCnt ) * paginationCnt + 1;
		max = min + paginationCnt - 1;
		
		if(max > pageCnt) {
			max = pageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
		
	}
	

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
}
