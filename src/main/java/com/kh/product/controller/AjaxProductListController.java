package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class AjaxProductListController
 */
@WebServlet("/productList.pr")
public class AjaxProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProductListController() {
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
		
		if(request.getParameter("qStr") != null) {
			qStrArr = request.getParameter("qStr").split("&");
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
		
		int listCount; // 총 게시글 개수
		int currentPage; // 현재 보고있는 페이지
		int pageLimit; // 페이징 바에 표시할 페이지 개수
		int boardLimit; // 한 패이지에 보여질 게시글 개수
		
		int maxPage; // 최대 페이지 수
		int startPage; // 첫번째 페이징바의 숫자
		int endPage; // 마지막 페이징바의 숫자
		
		listCount = new ProductService().selectKeywordListCount(qStrMap); // 총 게시글 수 가져오기
		
		// currentPage 쿼리스트링이 없으면 자동으로 1 부여
		currentPage = request.getParameter("currentPage") == "" ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		
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
		
		// 좋아요 총 갯수 구하기
		
		//System.out.println(productNo);
		
		
		
		
		// 상품 목록 조회용 서블릿
		ArrayList<Product> list = new ProductService().selectKeywordProductList(qStrMap, pi);
		
		ArrayList<Integer> likeCount = new ArrayList<> ();
		
		for(Product p : list) {
			likeCount.add(new ProductService().selectLikeCount(p.getProductNo())); 
			
		}
		
		//System.out.println(likeCount);
		
		// System.out.println(list.get(0).getCategoryNo());
		// System.out.println(list.get(0).getCategoryNo());
	
		HashMap<String, Object> h = new HashMap<String, Object>();
		h.put("likeCount", likeCount);
		h.put("list", list);
		h.put("pi", pi);
		// list를 응답데이터로 넘기기
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(h, response.getWriter());
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}