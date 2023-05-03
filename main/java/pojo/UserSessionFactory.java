package pojo;

public class UserSessionFactory {

	public static UserSession createSession(User user, UserPermitionType type) {
		UserSession session = null;
		if (type == null) {
			type = UserPermitionType.GUEST;
		}
		switch (type) {
		case GUEST:
			session = new UserSession(null, UserPrivelage.builder().guest(true).build());
			break;
		case ADMIN:
			session = new UserSession(user, UserPrivelage.builder().admin(true).build());
			break;
		case CLIENT:
			session = new UserSession(user, UserPrivelage.builder().client(true).build());
			break;
		default:
			break;
		}
		return session;
	}
	
}
