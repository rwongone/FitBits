import java.util.Scanner;
import java.util.Vector;
import java.io.*;
public class Main {
	final static String INFILE = "bits";
	final static String INTERMEDIATE = "intermediate";
	static String solution = "";
	static int minimum = Integer.MAX_VALUE;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(INFILE));
		Vector<Bit> bits = new Vector<Bit>();

		while (sc.hasNext()) {
			int n = sc.nextInt();
			Bit b = new Bit(n);
			for (int i=0; i<n; i++) {
				Fitting f = new Fitting();
				f.type = sc.next();
				f.size = sc.next();
				f.gender = sc.next();
				b.addFitting(f);
			}
			bits.add(b);
		} // now bits are filled
		
		query(bits, bits.get(0).a, bits.get(14).a);

		sc.close();
		return;
	}

	static void query(Vector<Bit> bits, Fitting src, Fitting dest) {
		String path = "";
		Bit cur = new Bit(2);
		Fitting nothing = new Fitting();
		nothing.type = "";
		nothing.gender = "";
		nothing.size = "";
		cur.a = nothing;
		cur.b = src;
		Bit destBit = new Bit(2);
		destBit.a = dest;
		destBit.b = nothing;
		BFS(bits, cur, destBit, 0, path);
		System.out.println(solution);
	}
	
	static void BFS(Vector<Bit> bits, Bit cur, Bit dest, int steps, String path) { // Bits made in here can point to the elements in the vector
		if (steps > minimum) {
			return;
		}
		Vector<Bit> valid = new Vector<Bit>();
		if (cur.complements(dest) && steps <= minimum) {
			solution = path;
			minimum = steps;
		}
		for (int i=0; i<bits.size(); i++) {
			Bit b = bits.get(i);
			if (b.n >= 2 && cur.complements(b)) {
				valid.add(b);
				bits.remove(b);
				i--;
			}
		}
		for (Bit b : valid) {
			BFS(bits, b, dest, steps + 1, path + b.toString());
		}
		
	}
	
	void testA() {
		Fitting a = new Fitting();
		a.size = "a";
		a.gender = "m";
		a.type = "1";
		Fitting b = new Fitting();
		b.size = "a";
		b.gender = "f";
		b.type = "1";
		
		Bit A = new Bit(2);
		A.addFitting(a);
		A.addFitting(b);
		Bit B = new Bit(2);
		B.addFitting(a);
		B.addFitting(b);;
		
		System.out.println(A.complements(B));
	}
	
}
