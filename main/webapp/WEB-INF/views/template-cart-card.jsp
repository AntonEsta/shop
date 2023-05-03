<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty cartItemData}">
	<div
		class="d-flex flex-row justify-content-between align-items-center pt-lg-4 pt-2 pb-3 border-bottom mobile">
		<div class="d-flex flex-row align-items-center">
			<div>
				<img src="images/200x200/${cartItemData.getImage()}" width="150"
					height="150" alt="" id="image">
			</div>
			<div class="d-flex flex-column pl-md-3 pl-1">
				<div>
					<h6>${cartItemData.getTitle()}</h6>
				</div>
				О товаре:<span class="pl-4">${cartItemData.getInfo()}</span>
			</div>
			<div class="pl-md-0 pl-1">
				<b>${cartItemData.getPrice()}</b>
			</div>
			<div class="pl-md-0 pl-2">
				<span class="fa fa-minus-square text-secondary"></span><span
					class="px-md-3 px-1">2</span><span
					class="fa fa-plus-square text-secondary"></span>
			</div>
			<div class="pl-md-0 pl-1">
				<b>${Integer.parseInt(cartItemData.getPrice()) * Integer.parseInt(cartItemData.getCount())}</b>
			</div>
			<div class="close">&times;</div>
		</div>
	</div>
</c:if>