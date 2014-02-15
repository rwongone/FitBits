
public class Fitting {
	String type, size, gender;
	public Fitting() {
		
	}
	public void print() {
		System.out.println(this.type);
		System.out.println(this.size);
		System.out.println(this.gender);
	}
	public boolean complements (Fitting other) {
		return (this.type.equals(other.type)) && (this.size.equals(other.size)) && (!this.gender.equals(other.gender));
	}
}
