<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.dto.FaqDto"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css">
</head>
<body>
<%@ include file="../common/header.jsp"%>
	<main>
		<%@ include file="./faqtitle.jsp"%>
		
		<h1 class="faq-insertpage-title">FAQ 작성</h1>
		<hr class="faq-underline">
		<!-- FAQ 작성 FORM -->
		<div class="faq-insert">
		
			<form action="FAQ" method="post" class="faq-insert-form">
				<input type="hidden" name="command" value="faqinsert">
				<table>
					
					<tr>
						<th>질문</th>
						<td><input type="text" name="faq-form-title" placeholder="질문을 입력하세요."></td>
					</tr>
					<tr>
						<th>답변</th>
						<td><textarea rows="10" cols="80" name="faq-form-content" placeholder="답변 내용을 입력하세요."></textarea></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<div class="faq-insert-buttons">
								<input type="submit" value="FAQ등록">
								<input type="button" value="등록취소" onclick="location.href='../FAQ?command=faqlist'">
							</div>
						</td>
					</tr>
					
				</table>
				
			</form>
		
		</div>
		
	</main>
<%@ include file="../common/footer.jsp"%>
</body>
</html>