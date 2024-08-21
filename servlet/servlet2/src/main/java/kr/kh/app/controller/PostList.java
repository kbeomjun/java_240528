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
		String coNumStr = request.getParameter("co_num");
		String pageStr = request.getParameter("page");
		String type = request.getParameter("type");
		String search = request.getParameter("search");
		
		try {
			int co_num = Integer.parseInt(coNumStr);
			CommunityVO community = postService.getCommunity(co_num);
			if(community == null) {
				throw new RuntimeException();
			}
			int page = 1;
			if(pageStr != null && pageStr.length() != 0) {
				page = Integer.parseInt(pageStr);
			}
			
			Criteria cri = new PostCriteria(page, 5, search, co_num, type);
			PageMaker pm = postService.getPostPageMaker(cri, cri.getPerPageNum());
			List<PostVO> list = postService.getPostList(cri);
			
			request.setAttribute("co", community);
			request.setAttribute("pm", pm);
			request.setAttribute("list", list);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "잘못된 커뮤니티입니다.");
			request.setAttribute("url", "/community");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
	}
}