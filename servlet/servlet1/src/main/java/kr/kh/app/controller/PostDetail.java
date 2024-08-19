package kr.kh.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.FileVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.model.vo.RecommendVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

@WebServlet("/post/detail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNumStr = request.getParameter("po_num");
		try {
			int po_num = Integer.parseInt(poNumStr);
			postService.updatePostView(po_num);
			PostVO post = postService.getPost(po_num);
			request.setAttribute("post", post);
			
			MemberVO user = (MemberVO) request.getSession().getAttribute("user");
			RecommendVO recommend = postService.getRecommend(po_num, user);
			request.setAttribute("re", recommend);
			
			List<FileVO> fileList = postService.getFileList(po_num);
			request.setAttribute("fileList", fileList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/views/post/detail.jsp").forward(request, response);
	}
}