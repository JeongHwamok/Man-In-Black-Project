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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
        String memberPhone = request.getParameter("memberPhone");
        String memberEmail = request.getParameter("memberEmail");
        String memberAddress = request.getParameter("memberAddress");
        
        Member m = new Member();
        m.setMemberId(memberId);
        m.setMemberPhone(memberPhone);
        m.setMemberEmail(memberEmail);
        m.setMemberAddress(memberAddress);
        
       Member updateMem = new MemberService().updateMember(m);
       
		if(updateMem == null) { // 실패
			
			request.setAttribute("errorMsg", 
								 "회원정보 수정에 실패했습니다.");

			request.getRequestDispatcher("views/common/errorPage.jsp")
										.forward(request, response);
			
		} else {
			HttpSession session = request.getSession();
			
			session.setAttribute("alertMsg", 
								 "성공적으로 회원 정보를 수정했습니다.");

			session.setAttribute("loginUser", updateMem);
			response.sendRedirect(request.getContextPath()+"/myPage.me");
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
