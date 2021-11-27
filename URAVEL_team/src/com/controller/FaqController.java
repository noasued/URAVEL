package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FaqDao;
import com.dto.FaqDto;
import com.dto.MemberDto;

public class FaqController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		MemberDto loginUser = (MemberDto) request.getSession().getAttribute("userInfo");

		FaqDao dao = new FaqDao();

		if (command.equals("faqlist")) {
			String language = request.getParameter("language");
			List<FaqDto> list = dao.selectAll(language);

			request.setAttribute("list", list);

			dispatch("faq/faqlist.jsp", request, response);

			System.out.println("list : " + request.getParameter("faqlist"));

		}

		else if (command.equals("faqinsertform")) {
			if(loginUser == null || !(loginUser.getRole().equals("ADMIN"))) {
				jsResponse("작성 권한이 없습니다.","../FAQ?command=faqlist",response);
			} else {
				response.sendRedirect("faq/faqinsert.jsp");
			}
		}

		else if (command.equals("faqinsert")) {
			String title = request.getParameter("faq-form-title");
			String content = request.getParameter("faq-form-content");

			FaqDto dto = new FaqDto();
			dto.setTitle(title);
			dto.setContent(content);

			int res = dao.insert(dto);

			if (res > 0) {
				jsResponse("FAQ 작성 성공", "../FAQ?command=faqlist", response);
			} else {
				jsResponse("FAQ 작성 실패", "FAQ?command=faqinsertform", response);
			}

			System.out.println("insert : " + request.getParameter("faqinsert"));
		}

		else if (command.equals("faqupdateform")) {
			if(loginUser == null || !(loginUser.getRole().equals("ADMIN"))) {
				jsResponse("수정 권한이 없습니다.","../FAQ?command=faqlist",response);
			} else {
				int faqno = Integer.parseInt(request.getParameter("faqno"));
				String language = null;
				FaqDto dto = dao.selectOne(faqno, language);
	
				request.setAttribute("dto", dto);
				dispatch("faq/faqupdate.jsp", request, response);
			}
		}

		else if (command.equals("faqupdate")) {
			int faqno = Integer.parseInt(request.getParameter("faqno"));
			String title = request.getParameter("faq-form-title");
			String content = request.getParameter("faq-form-content");

			FaqDto dto = new FaqDto();
			dto.setFaqno(faqno);
			dto.setTitle(title);
			dto.setContent(content);

			int res = dao.update(dto);

			if (res > 0) {
				jsResponse("FAQ 수정 성공", "FAQ?command=faqlist", response);
			} else {
				jsResponse("FAQ 수정 실패", "FAQ?command=faqupdate&faqno=" + faqno, response);
			}

		}

		else if (command.equals("faqdelete")) {
			if(loginUser == null || !(loginUser.getRole().equals("ADMIN"))) {
				jsResponse("삭제 권한이 없습니다.","../FAQ?command=faqlist",response);
			} else {
				int faqno = Integer.parseInt(request.getParameter("faqno"));
				int res = dao.delete(faqno);
	
				if (res > 0) {
					jsResponse("FAQ 삭제 성공", "FAQ?command=faqlist", response);
				} else {
					jsResponse("FAQ 삭제 실패", "FAQ?command=faqlist&faqno=" + faqno, response);
				}
			}

		}

	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
