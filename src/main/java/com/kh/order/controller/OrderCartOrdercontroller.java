package com.kh.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.order.model.service.OrderService;
import com.kh.order.model.vo.Order;

/**
 * Servlet implementation class OrderCartOrdercontroller
 */
@WebServlet("/order.caor")
public class OrderCartOrdercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCartOrdercontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터 전달 위한 VO
		Order o = new Order();
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		// 회원번호
		o.setMemberNo(loginUser.getMemberNo());
		
		// 배송 요청사항
		switch(request.getParameter("deliveryOptions")){
			case "leave-at-door":
				o.setOrderMessage("문 앞에 놓아주세요");
				break;
			case "ring-bell":
				o.setOrderMessage("벨 누르시고 놓아주세요");
				break;
			case "direct-handover":
				o.setOrderMessage("직접 전달해주세요");
				break;
			default:
				o.setOrderMessage("요청사항 없음");
		}
		
		// 주문 주소
		o.setOrderAddress(request.getParameter("address"));
		
		// 총 주문 금액
		o.setOrderPrice(Integer.parseInt(request.getParameter("total")));
		
		// 결제코드
		o.setOrderCode(request.getParameter("paymentId"));
		
		// 해당 회원의 카트 리스트 조회
		ArrayList<Cart> list = new CartService().selectCartList(loginUser.getMemberId());
		
		// ORDER_CODE 필드 값 없음 => 결제 성공 시 서비스사에서 돌아오는 반환값 사용
		// ORDER, ORDER_ITEM 테이블에 INSERT 요청
		int result = new OrderService().insertOrder(o, list);
		Order previousOrder = new OrderService().selectPreviousOrder();
		Member orderMember = new MemberService().selectMember(previousOrder.getMemberNo());
		
		if(result > 0) {
			
			// 리다이렉션 시 사용할수 있게 적당히 Attribute 첨부
			request.setAttribute("list", list);
			request.setAttribute("previousOrder", previousOrder);
			request.setAttribute("orderMember", orderMember);
			request.getRequestDispatcher("views/order/orderComplete.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
