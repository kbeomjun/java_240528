package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

@WebServlet("/post/insert")
public class PostInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coNumStr = request.getParameter("co_num");
		request.setAttribute("co_num", coNumStr);
		request.getRequestDispatcher("/WEB-INF/views/post/insert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coNumStr = request.getParameter("co_num");
		String po_title = request.getParameter("po_title");
		String po_content = request.getParameter("po_content");
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		try {
			int co_num = Integer.parseInt(coNumStr);
			PostVO post = new PostVO(co_num, po_title, po_content, user.getMe_id());
			if(postService.insertPost(post)) {
				request.setAttribute("msg", "게시글을 등록하였습니다.");
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글을 등록하지 못했습니다.");
		}
		request.setAttribute("url", "/post/list?co_num="+coNumStr);
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}