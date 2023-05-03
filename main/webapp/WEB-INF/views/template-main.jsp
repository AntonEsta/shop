<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container">
	<div class="row">
		<!-- content -->
		<div class="col-2 border">
			<jsp:include page="menu-vertical-detailed.jsp"></jsp:include>
		</div>
		<div class="col">
			<div class="container-fluid bg-trasparent my-4 p-3"
				style="position: relative;">
				<div id="main-content"
					class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
					<c:choose>
						<c:when test="${content eq 'catalog'}">
							<jsp:include page="catalog.jsp"></jsp:include>
						</c:when>
						<c:when test="${content eq 'cart'}">
							<div class="col">
								<jsp:include page="template-cart.jsp"></jsp:include>
							</div>
						</c:when>
						<c:otherwise>
							<jsp:include page="catalog.jsp"></jsp:include>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</main>