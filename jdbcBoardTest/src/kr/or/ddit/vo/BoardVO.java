package kr.or.ddit.vo;

import java.sql.Date;

/*
DB테이블에 있는 컬럼을 기준으로 데이터를 객체화 할 클래스

DB테이블의 '컬럼명'과 이름이 같은 '멤버변수'를 작성한다.

DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.
*/

/*
create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   	-- 작성날짜
    board_cnt number default 0, -- 조회수
    board_content clob,     	-- 내용
    constraint pk_jdbc_board primary key (board_no)
);

*/
public class BoardVO {
	private int no;
	private String title;
	private String writer;
	private Date date;
	private int cnt;
	private String content;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", writer=" + writer + ", date=" + date + ", cnt=" + cnt
				+ ", content=" + content + "]";
	}
	
	
	
	
}
