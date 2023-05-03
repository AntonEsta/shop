<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty cardData}">
	<div class="col">
		<div id="popular-category-card-1" class="card h-200 shadow-sm">
			<img <c:out value="src=images/200x200/${cardData.getImage()}"/>
				class="card-img-top" alt="${cardData.getImageAlt()}">
			<div id="popular-category-card-body-1" class="card-body">
				<div class="clearfix mb-3">
					<a href="${cadrData.getScr()}"> <span
						id="popular-category-card-body-brand-name-1"
						class="float-start badge rounded-pill bg-primary">${cardData.getBrandName()}</span>
					</a> <span id="popular-category-card-body-price-1"
						class="float-end price-hp price-rub">
							${cardData.getPrice()}
					</span>
				</div>
				<h5 id="popular-category-card-title-1" class="card-title">
					<c:out value="${cardData.getText()}"></c:out>
				</h5>
				<div class="text-center my-4"
					style="visible:${cardData.isButtonVisible()}">
					<a onclick="${cardData.getButtonHref()}" class="btn btn-info">${cardData.getButtonTitle()}</a>
				</div>
			</div>
		</div>
	</div>
</c:if>