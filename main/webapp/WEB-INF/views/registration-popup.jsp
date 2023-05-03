<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="registration-popup"
	class="vh-100 bg-image w-100 h-100 fixed-top">
	<div class="mask d-flex align-items-center h-100 gradient-custom-3">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-9 col-lg-7 col-xl-6">
					<div class="card" style="border-radius: 15px;">
						<div class="card-body p-5">
							<h2 class="text-uppercase text-center mb-5">Регистрация</h2>
							<c:choose>
								<c:when test="${not empty userRegSuccess}">
								<% System.out.println("INFO reg-popup: the user is already registered"); %>
									<h2 class="text-uppercase text-center mb-5">
										Вы зарегистрированы!<br> Приятных покупок!
									</h2>
									<script>
										setTimeout(function() {
											location.reload();
										}, 3000);
									</script>
								</c:when>
								<c:when test="${userSession.getPrivelage().isGuest()}">
								<% System.out.println("INFO reg-popup: the user is guest"); %>
									<form>

										<div class="form-outline mb-4">
											<input type="text" id="registration-popup-name"
												class="form-control form-control-lg" /> <label
												class="form-label" for="registration-popup-name">Ваше
												имя</label>
										</div>

										<div class="form-outline mb-4">
											<input type="email" id="registration-popup-email"
												class="form-control form-control-lg" /> <label
												class="form-label" for="registration-popup-email">Ваш
												Email</label>
										</div>

										<div class="form-outline mb-4">
											<input type="password" id="registration-popup-pwd"
												class="form-control form-control-lg" /> <label
												class="form-label" for="registration-popup-pwd">Пароль</label>
										</div>

										<div class="form-outline mb-4">
											<input type="password" id="registration-popup-pwd-confirm"
												class="form-control form-control-lg" /> <label
												class="form-label" for="registration-popup-pwd-confirm">Повторите
												пароль</label>
										</div>

										<!--  <div class="form-check d-flex justify-content-center mb-5">
									<input class="form-check-input me-2" type="checkbox" value=""
										id="form2Example3cg" /> <label class="form-check-label"
										for="form2Example3g"> I agree all statements in <a
										href="#!" class="text-body"><u>Terms of service</u></a>
									</label>
								</div>  -->

										<div class="d-flex justify-content-center">
											<button type="button"
												class="btn btn-success btn-primary btn-lg gradient-custom-4 text-body mx-2"
												onclick="regUsr();">ЗАРЕГИСТРИРОВАТЬСЯ</button>
											<button type="button"
												class="btn btn-secondary btn-block btn-lg text-body mx-2"
												onclick="hideRegForm()">ОТМЕНА</button>
										</div>

										<p class="text-center text-muted mt-5 mb-0">
											У Вас уже есть регистрация? <a
												onclick="showSignInForm();hideRegForm();"
												class="fw-bold text-body text-decoration-underline">Вход</a>
										</p>

									</form>
								</c:when>
								<c:otherwise>
									<% System.out.println("INFO reg-popup: the user is already logged in"); %>
									<h2 class="text-uppercase text-center mb-5">
										Регистрация не требуется:)<br> Приятных покупок!
									</h2>
									<script>
										setTimeout(function() {
											location.reload();
										}, 3000);
									</script>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>