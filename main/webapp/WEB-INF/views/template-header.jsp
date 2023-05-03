<%@page import="orm.gateways.CartGateway"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="container">
	<div class="row">
		<!-- header -->
		<div id="main-logo" class="col-2">
			<img src="images/logo_200x200.png" alt="Логотип магазина">
		</div>
		<div class="col">
			<div id="main-menu" class="navbar">
				<!-- header - main-menu -->
				<ul class="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2 py-md-0">
					<li class="nav-item col-6 col-md-auto"><a class="nav-link p-2"
						href="main">Магазин</a></li>
					<%-- <c:out value="${userSession}" /> --%>
					<c:if test="${userSession.getPrivelage().isGuest()}">
						<li class="nav-item col-6 col-md-auto"><a
							class="nav-link p-2 active" aria-current="true"
							onclick="showSignInForm();">Вход/Регистрация</a></li>
					</c:if>
					<c:if test="${not userSession.getPrivelage().isGuest()}">
						<li class="nav-item col-6 col-md-auto"><a
							class="nav-link p-2 active" aria-current="true" href="main?cart">Корзина</a></li>
					</c:if>
					<c:if test="${not userSession.getPrivelage().isGuest()}">
						<li class="nav-item col-6 col-md-auto"><a
							class="nav-link p-2 active" aria-current="true"
							onclick="logOut();">Выход</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</header>