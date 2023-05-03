<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty goodsViewData}">
	<c:forEach items="${goodsViewData}" var="data">
		<c:set var="cardData" value="${data}" scope="request" />
		<jsp:include page="template-card.jsp" />
	</c:forEach>
</c:if>