/**
 * 
 */
package dayTwo;

import java.util.Scanner;
/**
 * @author logan
 *
 */
public class AddInput {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		
//		System.out.println("Enter first number");
//		int firstNum = sc.nextInt();
//		System.out.println("Enter second number");
//		int secondNum = sc.nextInt();
//		
//		sum = firstNum + secondNum;
//		
//		System.out.println("Sum= " + sum);
		
		System.out.println("Enter up to 5 numbers. Enter letter to generate sum");
		while(sc.hasNextInt()) {
			int num = sc.nextInt(); 
            sum += num; 
		}
		
		System.out.println("Sum: " + sum); 

	}

}
