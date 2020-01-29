/**
 * 
 */
package dayTwo;

import java.util.Arrays;

/**
 * @author logan
 *
 */
public class ConstructArray {
//
//	/**
//	 * @param args
	
//	 */
	
	
	public static void main(String[] args) {
		
	
		int[][] arr = { { 1, 2, 3, 4 }, { 8, 7, 6, 5 }, {9, 10, 11, 12 }, {16, 15, 14, 13} }; 
		System.out.println(findMax(arr));
		System.out.println(Arrays.toString(findMaxIndex(arr)));
	}
	public static int findMax(int arr [][]) {
		int row =4;
		int col=4;
		int maxValue = Integer.MIN_VALUE;
		for(int i=0; i<row; i++) {
			for(int j =0; j<col; j++) {
				if(arr[i][j]> maxValue) {
					maxValue= arr[i][j];
				}
			}
		}
		return maxValue;
	}
	
	public static int[] findMaxIndex(int arr [][]) {
		int row = 4;
		int col = 4;
		int maxValue = Integer.MIN_VALUE;
		int[] answerArr = new int [2];
		for(int i=0; i<row; i++) {
			for(int j =0; j<col; j++) {
				if(arr[i][j]> maxValue) {
					maxValue= arr[i][j];
					answerArr[0]=i;
					answerArr[1]=j;
				}
			}
		}
		return answerArr;
		
	}
	
} 


