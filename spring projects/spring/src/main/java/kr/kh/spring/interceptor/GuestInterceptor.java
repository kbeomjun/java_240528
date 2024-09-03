package kr.kh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.spring.model.vo.MemberVO;

public class GuestInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		if(user != null) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<script>alert(\"회원은 접근할 수 없습니다.\")</script>");
			response.getWriter().write("<script>location.href = \"" + request.getContextPath() + "/" + "\"</script>");
			response.flushBuffer();
			return false;
		}
		return true;
	}
}