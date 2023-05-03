package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.mappers.ViewMapper;
import orm.gateways.CartGateway;
import orm.gateways.CategoryGateway;
import orm.gateways.GoodGateway;
import orm.gateways.UserGateway;
import pojo.User;
import pojo.UserPermitionType;
import pojo.UserSessionFactory;
import pojo.UserSessionHandler;
import pojo.Validators;

@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getHeader("referer"));
		var usrSession = UserSessionHandler.handle(request.getSession());
		request.setAttribute("userSession", usrSession);
		String content = getContent(request);
		request.setAttribute("content", content);

		if (request.getParameter("action") != null) {
			content = request.getParameter("action");
		}
		try {
			request.setAttribute("categories", CategoryGateway.getAllCategories());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		switch (content) {
		case "catalog":
			try {
				try {
					int category = Integer.parseInt(request.getParameter("cat"));
					var good = GoodGateway.findById(category);
					request.setAttribute("goods", good);
					request.setAttribute("goodsViewData", ViewMapper.goodsToCatalogCardView(good));
				} catch (NumberFormatException e) {
					var goods = GoodGateway.getAllGoods();
					request.setAttribute("goods", goods);
					request.setAttribute("goodsViewData", ViewMapper.goodsToCatalogCardView(goods));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "goodtocart":
			int goodId = Integer.parseInt(request.getParameter("id"));
			int count =  Integer.parseInt(request.getParameter("count"));
			if (goodId > 0) {
				try {
					CartGateway.addGood(usrSession.getUser().getId(), goodId, count);
					request.setAttribute("goodtocartSuccess", "");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("WEB-INF/views/good-to-cart.jsp").forward(request, response);
			return;
		case "catbycat":
			try {
				int category = Integer.parseInt(request.getParameter("cat"));
				var goods = GoodGateway.findByCategory(category);
				request.setAttribute("goodsViewData", ViewMapper.goodsToCatalogCardView(goods));
				request.getRequestDispatcher("WEB-INF/views/catalog.jsp").forward(request, response);
				return;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		case "cart":
			if (!usrSession.getPrivelage().isGuest()) {
				System.out.println("cart.userId -> " + usrSession.getUser().getId());
				try {
					var cartItems = CartGateway.getCart(usrSession.getUser().getId());
					System.out.println("cart.cartItems -> " + cartItems.length);
					request.setAttribute("cartCardViewData", ViewMapper.cartItemsToCartCardViews(cartItems));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					var cartItems = CartGateway.getCart(0);
					request.setAttribute("cartCardViewData", ViewMapper.cartItemsToCartCardViews(cartItems));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		case "regform":
			if (usrSession.getPrivelage().isGuest()) {
				var regformParMap = request.getParameterMap();
				if (regformParMap.containsKey("email") && regformParMap.containsKey("name")
						&& regformParMap.containsKey("pwd")) {
					System.out.println("INFO regform: have email, name & pwd parameters");
					var email = regformParMap.get("email")[0];
					var name = regformParMap.get("name")[0];
					var pwd = regformParMap.get("pwd")[0];
					if (!email.isBlank() && !name.isBlank() && !pwd.isBlank()) {
						System.out.println("INFO regform: email, name & pwd is not blank");
						if (Validators.isEmail(email)) { // email is valid
							System.out.println("INFO signin: Incomming email is valid e-mail address");
							try {
								var usr = UserGateway.addUser(email, name, pwd, UserPermitionType.CLIENT);
								if (usr != null) { // new user created
									try {
										usrSession = UserSessionFactory.createSession(usr,
												UserGateway.getPermitionType(usr.getId()));
										System.out.println("INFO signin: new userSession -> " + usrSession);
										request.getSession().setAttribute("userSession", usrSession);
										request.setAttribute("userRegSuccess", "");
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else { // new user not created
									System.out.println("ERROR regform: User Not Created");
									request.setAttribute("regformError", "UserNotCreated");
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else { // email is invalid
							System.out.println("ERROR regform: Invalid Email");
							request.setAttribute("regformError", "InvalidEmail");
						}
					}
				}
			}
			request.getRequestDispatcher("WEB-INF/views/registration-popup.jsp").forward(request, response);
			return;
		case "signin":
			var signinParMap = request.getParameterMap();
			if (signinParMap.containsKey("email") && signinParMap.containsKey("pwd")) {
				System.out.println("INFO signin: have email & pwd parameters");
				var email = signinParMap.get("email")[0];
				var pwd = signinParMap.get("pwd")[0];
				if (!email.isBlank() && !pwd.isBlank()) {
					System.out.println("INFO signin: email & pwd is not blank");
					if (Validators.isEmail(email)) { // email is valid
						System.out.println("INFO signin: Incomming email is valid e-mail address");
						User usr = null;
						try {
							usr = UserGateway.findByEmail(email);
							System.out.println("INFO signin: Get user by email -> " + usr);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (usr != null) { // user is found
							if (pwd.equals(usr.getPwd())) {
								System.out.println("INFO signin: Incomming pass is valid");
								try {
									usrSession = UserSessionFactory.createSession(usr,
											UserGateway.getPermitionType(usr.getId()));
									System.out.println("INFO signin: new userSession -> " + usrSession);
//									request.setAttribute("userSession", usrSession);
									request.getSession().setAttribute("userSession", usrSession);
									request.setAttribute("signinSuccess", "");
								} catch (SQLException e) {
									e.printStackTrace();
								}
							} else {
								request.setAttribute("signinError", "InvalidPassword");
								System.out.println("ERROR signin: InvalidPassword");
							}
						} else { // not found user
							request.setAttribute("signinError", "UserNoFound");
							System.out.println("ERROR signin: UserNoFound");
						}
					} else { // email is invalid
						request.setAttribute("signinError", "InvalidEmail");
						System.out.println("ERROR signin: InvalidEmail");
					}
				}
			}
			request.getRequestDispatcher("WEB-INF/views/signin-popup.jsp").forward(request, response);
			return;
		case "submenu":
			try {
				var catId = Integer.parseInt(request.getParameter("cat"));
				request.setAttribute("categories", CategoryGateway.getSubcategories(catId));
				request.getRequestDispatcher("WEB-INF/views/submenu-vertical-detailed.jsp").forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "logout":
			usrSession = UserSessionFactory.createSession(null, null);
			System.out.println("session reset -> " + usrSession);
//			content = (String) request.getAttribute("lastContent");
			break;
		default:
			content = "catalog";
		}
		request.removeAttribute("userSession");
		request.setAttribute("userSession", usrSession);
		request.getSession().setAttribute("lastContent", content);
		request.setAttribute("content", content); 
		request.getRequestDispatcher("WEB-INF/views/main.jsp").forward(request, response);
	}

	private String getContent(HttpServletRequest request) {
		if (request.getParameter("catalog") != null) {
			return "catalog";
		}
		if (request.getParameter("cart") != null) {
			return "cart";
		}
		if (request.getParameter("goodtocart") != null) {
			return "goodtocart";
		}
		if (request.getParameter("submenu") != null) {
			return "submenu";
		}
		if (request.getParameter("catbycat") != null) {
			return "catbycat";
		}
		if (request.getParameter("regform") != null) {
			return "regform";
		}
		if (request.getParameter("signin") != null) {
			return "signin";
		}
		if (request.getParameter("logout") != null) {
			return "logout";
		}
		return "catalog";
	}

}
