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

@WebServlet("/post/delete")
public class PostDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNumStr = request.getParameter("po_num");
		try {
			int po_num = Integer.parseInt(poNumStr);
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			PostVO post = postService.getPost(po_num, user);
			if(post != null) {
				postService.deletePost(post);
				int co_num = post.getPo_co_num();
				request.setAttribute("msg", "게시글을 삭제했습니다.");
				request.setAttribute("url", "/post/list?co_num="+co_num);
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글 삭제에 실패했습니다.");
			request.setAttribute("url", "/post/detail?po_num="+poNumStr);
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}