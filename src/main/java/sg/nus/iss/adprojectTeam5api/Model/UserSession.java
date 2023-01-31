package sg.nus.iss.adprojectTeam5api.Model;

public class UserSession {
	private User user;
	public UserSession()
	{
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserSession(User user) {
		super();
		this.user = user;
	}
}
