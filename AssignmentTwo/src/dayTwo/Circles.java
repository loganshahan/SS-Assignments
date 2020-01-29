/**
 * 
 */
package dayTwo;

/**
 * @author logan
 *
 */
public class Circles implements Shape {

	double radius;
	double pie = 3.14;
	
	public Circles(double radius) {
		this.radius = radius;
		
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("The radius of this circle is " + this.radius);
//		System.out.println("The width of this circle is " + this.width);
		System.out.println("The area of this circle is " + (this.radius*this.radius) / pie);
	}

	@Override
	public double calculateArea(double one, double two) {
		// TODO Auto-generated method stub
		return (this.radius*this.radius) / pie;
	}

}
