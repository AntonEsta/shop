package pojo;

import javax.servlet.http.HttpSession;

public class UserSessionHandler {
	
	public static UserSession handle(HttpSession httpSession) {
		UserSession session = null; 
		try {
			session = (UserSession) httpSession.getAttribute("userSession");
			System.out.println("userSession -> " + session);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return session != null ? session : UserSessionFactory.createSession(null, UserPermitionType.GUEST);
	}
	
}
