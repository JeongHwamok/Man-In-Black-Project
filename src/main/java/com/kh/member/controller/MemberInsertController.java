package com.kh.member.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// 0) POST 방식 인코딩 설정하기
		request.setCharacterEncoding("UTF-8");
		
	// 1) 전달값 뽑기
        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");
        String memberName = request.getParameter("memberName");
        String memberPhone = request.getParameter("memberPhone");
        String memberEmail = request.getParameter("memberEmail");
        Date memberBirthDate = Date.valueOf(request.getParameter("memberBirthDate"));
        String memberAddress = request.getParameter("memberAddress");
        
        
        Member m = new Member(memberId, memberPwd, memberName, memberPhone, 
                                        memberEmail, memberBirthDate, memberAddress);
        System.out.println(m);
        int result = new MemberService().InsertMember(m);

        if (result > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("alertMsg", "회원가입에 성공했습니다.");
            response.sendRedirect(request.getContextPath());
        } else {
            request.getSession().setAttribute("alertMsg", "회원가입에 실패했습니다.");
            response.sendRedirect("login.jsp"); // 실패 시 로그인 페이지로 리다이렉트
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
