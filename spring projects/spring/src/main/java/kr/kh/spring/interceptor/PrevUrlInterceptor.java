package kr.kh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.spring.model.vo.MemberVO;

public class PrevUrlInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		String prevUrl = (String)session.getAttribute("prevUrl");
		if(prevUrl == null) {
			return;
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			return;
		}
		
		response.sendRedirect(prevUrl);
		session.removeAttribute("prevUrl");
	}
}