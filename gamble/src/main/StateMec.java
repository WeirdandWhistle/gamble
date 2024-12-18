package main;

public class StateMec {
	
	public final int loadState = 0;
	public final int startState = 1;
	public final int menuState = 2;
	public final int diceState = 3;
	public int gameState = loadState;
	
	public StateMec() {
		
	}
	public void startGame() {
		gameState = startState;
	}

}