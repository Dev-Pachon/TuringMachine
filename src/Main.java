import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		String msg = "";
		
		for(int i = 0; i< a;i++) {
			int par = 0;
			int impar = 0;
			int b = sc.nextInt();
			for(int j = 0; j< b;j++) {
				int num = sc.nextInt();
				if(num%2==0) {
					par++;
				}else impar++;
			}
			if(impar==0) {
				msg += "NO\n";
			}else if(par==0) {
				if(b%2!=0) {
					msg += "YES\n";
				}else msg += "NO\n";
				
			}else msg += "YES\n";
		}
		System.out.println(msg);
		sc.close();
	}
}
