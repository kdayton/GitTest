package dayton.clickatell.smsTest;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	private String user;
	private String password;

	public MyAuthenticator(final String user, final String password) {
		this.user = user;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication () {
		System.out.println("requesting authentication.for [" + getRequestingHost()+"]");
		return new PasswordAuthentication (user, password.toCharArray());
	}

}
