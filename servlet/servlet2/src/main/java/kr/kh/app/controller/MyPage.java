package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.service.MemberService;
import kr.kh.app.service.MemberServiceImp;

@WebServlet("/mypage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String me_pw = request.getParameter("me_pw");
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		
		if(memberService.updatePw(user, me_pw)) {
			request.setAttribute("msg", "비밀번호를 변경했습니다.");
		}else {
			request.setAttribute("msg", "비밀번호를 변경하지 못했습니다.");
		}
		request.setAttribute("url", "/mypage");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}