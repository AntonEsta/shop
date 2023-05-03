<%@page import="orm.gateways.CategoryGateway"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty categories}">
	<div class="nav left-menu flex-column">
		<div class="nav-item">
			<c:forEach items="${categories}" var="cat">
				<a  
				<c:choose>
					<c:when test="${not empty cat.getSubcategories()}">
						class="nav-link active menu-item-have-subitems"
					</c:when>
					<c:otherwise>
						class="nav-link active menu-item-havent-subitems"
					</c:otherwise> 
				</c:choose>
				onClick="expendMenuItem(this,${cat.getId()});showCategoryGoods(${cat.getId()});">${cat.getTitle()}</a>
			</c:forEach>
		</div>
	</div>
</c:if>