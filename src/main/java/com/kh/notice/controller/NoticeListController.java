package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listCount; // 총 게시글 개수
		int currentPage; // 현재 보고있는 페이지
		int pageLimit; // 페이징 바에 표시할 페이지 개수
		int boardLimit; // 한 패이지에 보여질 게시글 개수
		
		int maxPage; // 최대 페이지 수
		int startPage; // 첫번째 페이징바의 숫자
		int endPage; // 마지막 페이징바의 숫자
		
		listCount = new NoticeService().selectListCount(); // 총 게시글 수 가져오기
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		pageLimit = 10;
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// 사용자가 maxPage를 넘어간 페이지를 리퀘스트한 경우 maxPage로 리다이렉트
		if(currentPage > maxPage) {
			currentPage = maxPage;
		}
		
		startPage = ((currentPage - 1) / boardLimit) * boardLimit + 1;
		endPage = startPage + boardLimit - 1;
		
		
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage,
				   pageLimit, boardLimit,
				   maxPage, startPage,
				   endPage);
		
		ArrayList<Notice> list = new NoticeService().selectNoticeList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request
			.getRequestDispatcher("views/notice/noticeListView.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
