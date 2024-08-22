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

@WebServlet("/post/update")
public class PostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNumStr = request.getParameter("po_num");
		try {
			int po_num = Integer.parseInt(poNumStr);
			PostVO post = postService.getPost(po_num);
			request.setAttribute("po", post);
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/views/post/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNumStr = request.getParameter("po_num");
		String po_title = request.getParameter("po_title");
		String po_content = request.getParameter("po_content");
		try {
			int po_num = Integer.parseInt(poNumStr);
			PostVO post = new PostVO(po_num, po_title, po_content);
			if(postService.updatePost(post)) {
				request.setAttribute("msg", "게시글을 수정하였습니다.");
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			request.setAttribute("msg", "게시글을 수정하지 못했습니다.");
			e.printStackTrace();
		}
		request.setAttribute("url", "/post/detail?po_num="+poNumStr);
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}