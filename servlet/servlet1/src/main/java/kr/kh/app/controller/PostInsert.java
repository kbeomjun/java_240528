package kr.kh.app.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

@WebServlet("/post/insert")
// jsp form태그에 enctype을 설정하기 않고 @MultipartConfig을 추가하면 인식을 못함
// 반대로 jsp form태그에 enctype을 설정하고 @MultipartConfig을 추가하지 않으면 인식을 못함
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 10, //10MB
	maxRequestSize = 1024 * 1024 * 10 * 3,
	fileSizeThreshold = 1024 * 1024
)
public class PostInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coNumStr = request.getParameter("co_num");
		try {
			int co_num = Integer.parseInt(coNumStr);
			request.setAttribute("co_num", co_num);
			
			request.getRequestDispatcher("/WEB-INF/views/post/insert.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "잘못된 커뮤니티입니다.");
			request.setAttribute("url", "/community");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coNumStr = request.getParameter("co_num");
		try {
			int co_num = Integer.parseInt(coNumStr);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			if(user == null) {
				throw new RuntimeException();
			}
			String id = user.getMe_id();
			PostVO post = new PostVO(co_num, title, content, id);
			
			ArrayList<Part> files = (ArrayList<Part>) request.getParts();
			
			if(postService.insertPost(post, files)) {
				request.setAttribute("msg", "게시글을 등록했습니다.");
				request.setAttribute("url", "/post/list?co_num="+co_num);
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글 등록에 실패했습니다.");
			request.setAttribute("url", "/post/list?co_num="+coNumStr);
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}