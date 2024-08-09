package kr.kh.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;
import kr.kh.app.pagination.PostCriteria;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

@WebServlet("/post/list")
public class PostList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int coNum = 0;
		try {
			coNum = Integer.parseInt(request.getParameter("co_num"));
			String pageStr = request.getParameter("page");
			int page = 1;
			if(pageStr != null && pageStr.length() != 0) {
				page = Integer.parseInt(pageStr);
			}
			
			CommunityVO community = postService.getCommunity(coNum);
			if(community == null) {
				throw new Exception();
			}
			
			Criteria cri = new PostCriteria(page, 2, "", coNum);
			PageMaker pm = postService.getPageMaker(cri, 2);
			
			List<PostVO> list = postService.getPostList(cri);

			request.setAttribute("co", community);
			request.setAttribute("list", list);
			request.setAttribute("pm", pm);
			
			request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "잘못된 커뮤니티입니다.");
			request.setAttribute("url", "/community");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
	}
}