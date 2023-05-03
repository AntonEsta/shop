<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty userSession}">
	<section id="signin-popup"
		class="vh-100 bg-image w-100 h-100 fixed-top">
		<div class="mask d-flex align-items-center h-100 gradient-custom-3">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-9 col-lg-7 col-xl-6">
						<div class="card" style="border-radius: 15px;">
							<c:if test="${userSession.getPrivelage().isGuest()}">
								<div class="card-body p-5">
									<h2 class="text-uppercase text-center mb-5">Вход</h2>

									<form>

										<div
											class="form-outline mb-4
										<c:if test="${not empty signinError and (signinError eq 'InvalidEmail')}">
										 text-danger
										</c:if>
										">
											<input type="email" id="signin-popup-email"
												class="form-control form-control-lg" /> <label
												class="form-label" for="signin-popup-email">Ваш
												Email</label>
										</div>

										<div
											class="form-outline mb-4
										<c:if test="${not empty signinError and (signinError eq 'InvalidPassword')}">
										 text-danger
										</c:if>
										">
											<input type="password" id="signin-popup-password"
												class="form-control form-control-lg" /> <label
												class="form-label" for="signin-popup-password">Пароль</label>
										</div>

										<div class="d-flex justify-content-center">
											<button type="button"
												class="btn btn-success btn-primary btn-lg gradient-custom-4 text-body mx-2"
												onclick="signinUsr();">ВХОД</button>
											<button type="button"
												class="btn btn-secondary btn-block btn-lg text-body mx-2"
												onclick="hideSignInForm();">ОТМЕНА</button>
										</div>

										<p class="text-center text-muted mt-5 mb-0">
											У Вас нет ещё регистрации? <a
												onclick="showRegForm();hideSignInForm();"
												class="fw-bold text-body text-decoration-underline" style="cursor: pointer;">Регистрация</a>
										</p>
										<div class="text-left text-muted mt-3 font-weight-light">
											<p>
												Для проверки работы магазина предопределены следующие
												аккаунты:<br>
											<ul>
												<li><span>Клиент:</span><span
													class="ml-2">логин - client@mail.com</span><span
													class="ml-2">пароль - client</span></li>
												<li><span>Администратор:</span><span class="ml-2 ">логин
														- admin@mail.com</span><span class="ml-2">пароль - admin</span></li>
											</ul>
											</p>
										</div>

									</form>

								</div>
							</c:if>
							<c:if test="${not empty userSession.getUser()}">
								<div class="card-body p-5">
									<h2 class="text-uppercase text-center mb-5">Вход выполнен
										для ${userSession.getUser().getName()}!</h2>
									<script>
										setTimeout(function() {
											location.reload();
										}, 3000);
									</script>
								</div>
							</c:if>
							<c:if test="${not empty signinSuccess}">
								<div class="card-body p-5">
									<h2 class="text-uppercase text-center mb-5">Вход выполнен
										для ${userSession.getUser().getName()}!</h2>
									<script>
										setTimeout(function() {
											location.reload();
										}, 3000);
									</script>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</c:if>