package kr.or.ddit.vo;

import java.util.List;

//@Data

//<T> : 커스텀 가능
//ex) boardVO, noticeVO 등등 바꿔가면서 선언 가능
public class PaginationInfoVO<T> {
	private int totalRecord;	//총 게시글 수
	private int totalPage;		//총 페이지 수
	private int currentPage;	//현재 페이지
	private int screenSize = 10;		//페이지 당 게시글 수
	private int blockSize = 5;		//페이지 블록 수
	private int startRow;		//시작 row
	private int endRow;			//끝 row
	private int startPage;		//시작 페이지
	private int endPage;		//끝 페이지
	private List<T> dataList;	//결과를 넣을 데이터 리스트(페이지 당 글 개수를 잘라서 담아놓을 그릇)
	private String searchType;	//검색 타입(제목, 내용, 작성자 등등)
	private String searchWord;	//검색 단어(키워드)
	
	public PaginationInfoVO() {
		
	}
	
	//PaginationInfoVO 객체를 만들 때 한 페이지당 게시글 수와 페이지 블록 수를
	//원하는 값으로 초기화 할 수 있도록 custom 생성자 생성
	public PaginationInfoVO(int screenSize, int blockSize) {
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		//총 페이지 수 구하기
		totalPage = (int) Math.ceil(totalRecord / (double) screenSize);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;		//끝 row = 현재 페이지 * 페이지 당 게시글 수
		startRow = endRow - (screenSize - 1);	//시작 row = 끝 row - (페이지 당 게시글 수 - 1)
		
		//마지막 페이지 = (현재 페이지 + (페이지 블록 수 -1)) / 페이지 블록 사이즈^2
		//1-5
		endPage = (currentPage + (blockSize - 1)) / blockSize * blockSize;
		startPage = endPage - (blockSize - 1);	//시작 페이지 = 마지막 페이지 - (페이지 블록 사이즈 - 1)
	}

	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	
	//부트스트랩을 이용하던 여러 css요소를 이용해서 페이징 UI를 구현하는 메소드	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		
		html.append("<ul class='pagination justify-content-center'>");
		
		if(startPage > 1) {
			html.append("<li class='page-item'><a href='' tabindex='-1' class='page-link' data-page='" + (startPage - blockSize) + "'>Prev</a></li>");
		}
		
		for(int i = startPage; i <= (endPage < totalPage ? endPage : totalPage); i++) {
			if(i == currentPage) {
				html.append("<li class='page-item active'><span class='page-link'>" + i + "</span></li>");
			} else {
				html.append("<li class='page-item'><a href='' class='page-link' data-page='" + i + "'>" + i + "</a></li>");
			}
		}
		
		if(endPage < totalPage) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='" + (endPage + 1) + "'>Next</a></li>");
		}
		
		html.append("</ul>");
		
		return html.toString();
	}
}
