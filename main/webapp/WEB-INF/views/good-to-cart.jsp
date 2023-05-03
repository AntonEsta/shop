<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty goodtocartSuccess}">
	<script id="cart-alert-script">
		alert("Товар успешно добавлен!");
		$("#cart-alert-script").remove();
	</script>
</c:if>