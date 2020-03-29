package model;

public class turingChar {
	private char character;
	private turingChar nextTuringChar;
	private turingChar prevTuringChar;
	
	private static int cont;
	
	public turingChar(char ch) {
		character = ch;
		cont++;
	}
	
	public turingChar getNextTuringChar() {
		return nextTuringChar;
	}
	public void setNextTuringChar(turingChar ch) {
		nextTuringChar = ch;
	}
	public turingChar getPrevTuringChar() {
		return prevTuringChar;
	}
	public void setPrevTuringChar(turingChar ch) {
		prevTuringChar = ch;
	}
	
	public char getCharacter() {
		return character;
	}
	public static int size() {
		return cont;
	}
	public static void reduceCont() {
		cont--;
	}
	public static void restartCont() {
		cont = 0;
	}
}
