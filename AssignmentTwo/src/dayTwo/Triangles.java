/**
 * 
 */
package dayTwo;

/**
 * @author logan
 *
 */
public class Triangles implements Shape {
	double height;
	double base;
	
	public Triangles(double height, double base) {
		this.height = height;
		this.base = base;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("The height of this triangle is " + this.height);
		System.out.println("The base of this triangle is " + this.base);
		System.out.println("The area of this triangle is " + (this.height*this.base) / 2);
	}

	@Override
	public double calculateArea(double one, double two) {
		
		return (this.height*this.base) / 2;
	}

}
