package ui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import model.turingChar;

public class turing {

	public static turingChar firstTuringChar;
	public static turingChar middleTuringChar;
	public static turingChar lastTuringChar;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/in_turing.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/turing_MyOutput.txt"));
		
		long pre = System.currentTimeMillis();
		String str = br.readLine();
		
		while(!str.isEmpty()) {
			firstTuringChar = null;
			middleTuringChar = null;
			lastTuringChar = null;
			
			for(int i = 0; i<str.length();) {
				if(str.charAt(i)=='0') {
					if(str.charAt(i+1)=='0') {
						if(firstTuringChar==null) {
							bw.write("#\n");
						}else					
							bw.write(firstTuringChar.getCharacter()+"\n");
						i+=2;
					}else if(str.charAt(i+1)=='1') {
						addChar(0, str.charAt(i+2));
						i+=3;
					}else if(str.charAt(i+1)=='2') {
						removeChar(0);
						i+=2;
					}
				}else if(str.charAt(i)=='1') {
					if(str.charAt(i+1)=='0') {
						if(firstTuringChar==null) {
							bw.write("#\n");
						}else {
							bw.write(middleTuringChar.getCharacter()+"\n");
						}
						i+=2;
					}else if(str.charAt(i+1)=='1') {
						addChar(1, str.charAt(i+2));
						i+=3;
					}else if(str.charAt(i+1)=='2') {
						removeChar(1);
						i+=2;	
					}
				}else if(str.charAt(i)=='2') {
					if(str.charAt(i+1)=='0') {
						if(firstTuringChar==null) {
							bw.write("#\n");
						}else
							bw.write(lastTuringChar.getCharacter()+"\n");
						i+=2;
					}else if(str.charAt(i+1)=='1') {
						addChar(2, str.charAt(i+2));
						i+=3;
					}else if(str.charAt(i+1)=='2') {
						removeChar(2);
						i+=2;
					}
				}
			}
			turingChar.restartCont();
			str = br.readLine();
		}
		bw.close();
		br.close();
		long pos = System.currentTimeMillis();
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(pos-pre+"");
		bw.close();
	}
	
	public static void addChar(int a, char ch) {
		if(firstTuringChar == null) {
			turingChar temp = new turingChar(ch);
			firstTuringChar = temp;
			middleTuringChar = temp;
			lastTuringChar = temp;
		}else {
			if(a == 0) {
				turingChar temp = firstTuringChar;
				firstTuringChar = new turingChar(ch);
				firstTuringChar.setNextTuringChar(temp);
				temp.setPrevTuringChar(firstTuringChar);
				if(turingChar.size()%2==0) {
					middleTuringChar = middleTuringChar.getPrevTuringChar();
				}
			}else if(a == 1) {
				if(turingChar.size()%2==0) {
					turingChar temp = middleTuringChar.getNextTuringChar();
					middleTuringChar.setNextTuringChar(new turingChar(ch));
					middleTuringChar.getNextTuringChar().setPrevTuringChar(middleTuringChar);
					middleTuringChar = middleTuringChar.getNextTuringChar();
					middleTuringChar.setNextTuringChar(temp);
					temp.setPrevTuringChar(middleTuringChar);
				}else {
					if(turingChar.size()==1) {
						turingChar temp = firstTuringChar;
						firstTuringChar = new turingChar(ch);
						firstTuringChar.setNextTuringChar(temp);
						temp.setPrevTuringChar(firstTuringChar);
						middleTuringChar = firstTuringChar;
					}else {
						turingChar temp = middleTuringChar.getPrevTuringChar();
						middleTuringChar.setPrevTuringChar(new turingChar(ch));
						middleTuringChar.getPrevTuringChar().setPrevTuringChar(temp);
						temp.setNextTuringChar(middleTuringChar.getPrevTuringChar());
						temp.getNextTuringChar().setNextTuringChar(middleTuringChar);
						middleTuringChar = middleTuringChar.getPrevTuringChar();
					}
				}
			}else {
				lastTuringChar.setNextTuringChar(new turingChar(ch));
				lastTuringChar.getNextTuringChar().setPrevTuringChar(lastTuringChar);
				lastTuringChar = lastTuringChar.getNextTuringChar();
				if(turingChar.size()%2!=0) {
					middleTuringChar = middleTuringChar.getNextTuringChar();
				}
			}
			
		}
	}

	
	public static void removeChar(int a) {
		if(firstTuringChar!=null) {
			if(a==0) {
				if(turingChar.size()!=1) {
					firstTuringChar = firstTuringChar.getNextTuringChar();
					firstTuringChar.setPrevTuringChar(null);
					if(turingChar.size()%2==0) {
						middleTuringChar = middleTuringChar.getNextTuringChar();
					}
				}else {
					firstTuringChar = null;
					middleTuringChar=null;
					lastTuringChar=null;
				}
				turingChar.reduceCont();
			}else if(a==1) {
				if(lastTuringChar==firstTuringChar) {
					turingChar.reduceCont();
					firstTuringChar = null;
					middleTuringChar=null;
					lastTuringChar=null;
				}else {
					if(turingChar.size()==2) {
						firstTuringChar = firstTuringChar.getNextTuringChar();
						firstTuringChar.setPrevTuringChar(null);
					}else {
						middleTuringChar.getPrevTuringChar().setNextTuringChar(middleTuringChar.getNextTuringChar());
						middleTuringChar.getNextTuringChar().setPrevTuringChar(middleTuringChar.getPrevTuringChar());
					}
					if(turingChar.size()%2==0) {
						middleTuringChar = middleTuringChar.getNextTuringChar();
					}else {
						middleTuringChar = middleTuringChar.getPrevTuringChar();
					}
					turingChar.reduceCont();
				}
			}else {
				if(turingChar.size()==1) {
					firstTuringChar = null;
					middleTuringChar=null;
					lastTuringChar=null;
				}else {
					lastTuringChar = lastTuringChar.getPrevTuringChar();
					lastTuringChar.setNextTuringChar(null);
					if(turingChar.size()%2!=0) {
						middleTuringChar = middleTuringChar.getPrevTuringChar();
					}
				}
				turingChar.reduceCont();
			}
		}
	}
}
