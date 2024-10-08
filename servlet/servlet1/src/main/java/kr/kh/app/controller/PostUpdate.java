package kr.kh.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.kh.app.model.vo.FileVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

@WebServlet("/post/update")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 10, //10MB
	maxRequestSize = 1024 * 1024 * 10 * 3,
	fileSizeThreshold = 1024 * 1024
)
public class PostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNumStr = request.getParameter("po_num");
		try {
			int po_num = Integer.parseInt(poNumStr);
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			PostVO post = postService.getPost(po_num, user);
			if(post != null) {
				List<FileVO> fileList = postService.getFileList(po_num);
				request.setAttribute("fileList", fileList);
				request.setAttribute("post", post);
				request.getRequestDispatcher("/WEB-INF/views/post/update.jsp").forward(request, response);
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			request.setAttribute("msg", "작성자가 아니거나 게시글이 없습니다.");
			request.setAttribute("url", "/post/detail?po_num="+poNumStr);
			request.getRequestDispatcher("/WEB-INF/views/message.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNumStr = request.getParameter("po_num");
		try {
			int po_num = Integer.parseInt(poNumStr);
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			PostVO post = new PostVO(po_num, title, content);

			List<Part> fileList = (ArrayList<Part>) request.getParts();
			String[] fiNumStr = request.getParameterValues("fi_num");
			
			if(postService.updatePost(post, user, fileList, fiNumStr)) {
				request.setAttribute("msg", "게시글을 수정했습니다.");
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글 수정에 실패했습니다.");
		}
		request.setAttribute("url", "/post/detail?po_num="+poNumStr);
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}