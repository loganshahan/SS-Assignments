/**
 * 
 */
package dayTwo;

/**
 * @author logan
 *
 */
public class Rectangles implements Shape {
	double height;
	double width;
	
	public Rectangles(double height, double width) {
		this.height = height;
		this.width = width;
	}
	
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("The height of this rectangles is " + this.height);
		System.out.println("The width of this rectangles is " + this.width);
		System.out.println("The area of this rectangles is " + this.height*this.width);
	}

	@Override
	public double calculateArea(double one, double two) {
		// TODO Auto-generated method stub
		
		return this.height*this.width;
		
	}

}
