package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인기능을 담당하는 서블릿
		
		// post 방식이므로 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// loginPage.jsp에 name="" 키값을 가져와서 변수에 담아둠
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		// VO로 가공하기
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPwd(memberPwd);
		
		
		
		// 서버로 전달과 동시에 결과받기
		// 회원정보 한 행의 결과조회이기 때문에 ArrayList 가 아닌
		// Member 객체로 받을거임
		Member loginUser = new MemberService().loginMember(m);
		
		
		if(loginUser == null ) { // 로그인 실패할 경우
			
			
			
			request.getSession().setAttribute("alertMsg", "아이디 또는 비밀번호가 틀렸습니다.");
			// 응답페이지를 따로 지정안하고 alert로 로그인 실패 안내하고 넘어갈거기 때문에 
			// 포워딩말고 리다이렉트방식 사용함 url 재요청
			response.sendRedirect(request.getContextPath()+"/loginPage.me");
			
			
			
		} else if(loginUser.getMemberId().equals("admin")) {
			request.getSession().setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath()+"/admin.ad");
		} else {
		
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", loginUser.getMemberId()+"님 계정으로 로그인 성공.");
			
			// 이시점에서 session에 로그인 정보가 담겨있음 예를들자면 로그인 상태유지
			// 로그인상태를 유지한 시점에서 메인페이지로 이동
			response.sendRedirect(request.getContextPath());
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
