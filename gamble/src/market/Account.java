package market;

import java.util.HashMap;

public class Account {

//	public double money = 0;
	public int token;
	public HashMap<Integer, Integer> accounts = new HashMap<>();

	public int addAccount(String name, int money) {

		token = name.hashCode();
		accounts.put(token, money);
//		System.out.println("Created an account with the name: " + name + "token: " + token);

		return token;
	}

	public Account() {

	}

}
