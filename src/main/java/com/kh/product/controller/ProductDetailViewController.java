package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.inquiry.model.service.InquiryService;
import com.kh.inquiry.model.vo.Inquiry;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.review.model.service.ReviewService;
import com.kh.review.model.vo.Review;


/**
 * Servlet implementation class ProductDetailViewController
 */
@WebServlet("/productDetail.pr")
public class ProductDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		ArrayList<Product> list = new ProductService().selectProductDetail(productNo); 
		ArrayList<Inquiry> iList = new InquiryService().selectInquiry(productNo);
		ArrayList<Review> rList = new ReviewService().selectProductReview(productNo);
		
		
		
		request.setAttribute("prList", list);
		
		for(Inquiry i :  iList) {
			System.out.println(i);
		}
		
		for(Review r :  rList) {
			System.out.println(r);
		
		}
		
		request.setAttribute("iList", iList);
		request.setAttribute("rList", rList);
		request.getRequestDispatcher("views/product/productDetailView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
