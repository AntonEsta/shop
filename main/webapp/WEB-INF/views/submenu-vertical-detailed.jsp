<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty categories}">
	<div class="nav flex-column">
		<c:forEach items="${categories}" var="sub">
			<a class="nav-link active" onclick="showCategoryGoods(${sub.getId()})">${sub.getTitle()}</a>
		</c:forEach>
	</div>
</c:if>