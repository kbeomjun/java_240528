package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

@WebServlet("/comment/delete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmNumStr = request.getParameter("cm_num");
		String cmOriNumStr = request.getParameter("cm_ori_num");
		
		JSONObject jobj = new JSONObject();
		ObjectMapper om = new ObjectMapper();
		try {
			int cm_num = Integer.parseInt(cmNumStr);
			int cm_ori_num = Integer.parseInt(cmOriNumStr);
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			
			boolean res =  postService.deleteComment(cm_num, cm_ori_num, user);
			jobj.put("result", res);
		}catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}
}