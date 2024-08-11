package com.kh.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class searchResultController
 */
@WebServlet("/search.do")
public class searchResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchResultController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] qStrArr = null;
		HashMap<String, String> qStrMap = new HashMap<String, String>();
		
		// currentPage 쿼리스트링이 없으면 자동으로 1 부여
		qStrMap.put("currentPage", "1");
		
		// orderby 쿼리스트링이 없으면 자동으로 date 부여
		qStrMap.put("orderby", "date");
		
		if(request.getQueryString() != null) {
			qStrArr = request.getQueryString().split("&");
			// 모든 쿼리스트링을 qStrMap에 저장
			for(String s : qStrArr) {
				if(s.split("=").length == 2) {
					String key = s.split("=")[0];
					String value = s.split("=")[1];
					
					qStrMap.put(key, value);
				}
			}
		}
		String keyword = request.getParameter("keyword");
		if(keyword != null) {
			qStrMap.put("keyword", keyword);
		}
		
		// 해당하는 상품 수 가져오기
		int listCount = new ProductService().selectKeywordListCount(qStrMap);

		// int listCount; // 총 게시글 개수
		int currentPage; // 현재 보고있는 페이지
		int pageLimit; // 페이징 바에 표시할 페이지 개수
		int boardLimit; // 한 패이지에 보여질 게시글 개수
		
		int maxPage; // 최대 페이지 수
		int startPage; // 첫번째 페이징바의 숫자
		int endPage; // 마지막 페이징바의 숫자
		
		// currentPage 가져오기
		currentPage = Integer.parseInt(qStrMap.get("currentPage"));
		
		pageLimit = 10;
		boardLimit = 15;
		
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
		
		
		
		// 상품 목록 조회용 서블릿
		ArrayList<Product> list = new ProductService().selectKeywordProductList(qStrMap, pi);
		
		// HashMap<String, Object> h = new HashMap<String, Object>();
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("qStrMap", qStrMap);
		
		request.getRequestDispatcher("views/search/searchResult.jsp").forward(request, response);

//		response.setContentType("application/json; charset=UTF-8");
//		new Gson().toJson(h, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}