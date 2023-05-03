<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty cartCardViewData}">
	<div class="container bg-white rounded-top" id="zero-pad">
		<div class="row d-flex justify-content-center">
			<div class="col-lg-10 col-12">
				<div class="d-flex flex-column pt-4">
					<div>
						<h5 class="text-uppercase font-weight-normal">корзина</h5>
					</div>
					<div class="font-weight-normal">Количество товаров:
						<% int goodsCount = 0; %>
						<c:forEach items="${cartCardViewData}" var="item">
							${goodsCount = goodsCount + Integer.parseInt(item.getCount())}
						</c:forEach></div>
				</div>
				<div class="d-flex flex-row px-lg-5 mx-lg-5 mobile" id="heading">
					<div class="px-lg-5 mr-lg-5" id="produc">ТОВАРЫ</div>
					<div class="px-lg-5 ml-lg-5" id="prc">ЦЕНА</div>
					<div class="px-lg-5 ml-lg-1" id="quantity">КОЛИЧЕСТВО</div>
					<div class="px-lg-5 ml-lg-3" id="total">СТОИМОСТЬ</div>
				</div>
				<c:forEach items="${cartCardViewData}" var="data">
					<c:set var="cartItemData" value="${data}" scope="request" />
					<jsp:include page="template-cart-card.jsp" />
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="container bg-light rounded-bottom py-4" id="zero-pad">
		<div class="row d-flex justify-content-center">
			<div class="col-lg-10 col-12">
				<div class="d-flex justify-content-between align-items-center">
					<div>
						<button class="btn btn-sm bg-light border border-dark">GO
							BACK</button>
					</div>
					<div class="px-md-0 px-1" id="footer-font">
						<b class="pl-md-4">СТОИМОСТЬ<span class="pl-md-4 price-rub"> <%-- <c:set var="totalcost" scope="page" value="0" /> --%>
								<%
								double totalcost = 0;
								%> <c:forEach items="${cartCardViewData}" var="item">
							${totalcost = totalcost + Integer.parseInt(item.getCount()) * Integer.parseInt(item.getPrice())}
						</c:forEach>
						</span></b>
					</div>
					<div>
						<button class="btn btn-sm bg-dark text-white px-lg-5 px-3">ОФОРМИТЬ</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>