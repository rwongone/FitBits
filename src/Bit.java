
public class Bit { // working with only two-headed bits
	int n;
	Fitting a = null;
	Fitting b = null;
	public Bit(int n) {
		this.n = n;
		this.a = null;
		this.b = null;
	}
	public void addFitting(Fitting f) {
		if (a == null) {
			a = f;
		} else if (b == null) {
			b = f;
		}
	}
	public void printFittings() {
		if (n > 1) {
			System.out.println(a.type + " " + a.size + " " + a.gender + " / " + b.type + " " + b.size + " " + b.gender);	
		} else {
			System.out.println(a.type + " " + a.size + " " + a.gender);
		}
	}
	public String toString() {
		return a.type + " " + a.size + " " + a.gender + " / " + b.type + " " + b.size + " " + b.gender + "\n";
	}
	public void flip () {
		//System.out.println("flipping");
		Fitting temp = this.a;
		a = b;
		b = temp;
	}
	public boolean complements (Bit other) {
		//System.out.println("checking bit complement");
		if (this.a.complements(other.a)){
			//System.out.println("a");
			this.flip();
		} else if (this.a.complements(other.b)){
			//System.out.println("b");
			this.flip();
			other.flip();
		} else if (this.b.complements(other.a)){
			//System.out.println("c: " + this.b.gender + " " + other.a.gender);
			//no flips necessary
		} else if (this.b.complements(other.b)){
			//System.out.println("d");
			other.flip();
		} else {
			return false;
		}
		return true;
	}
}
