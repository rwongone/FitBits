import java.util.Scanner;
import java.util.Random;
import java.util.Vector;
import java.io.*;
public class Main {
	final static String INFILE = "bits";
	final static String INTERMEDIATE = "intermediate";
	static String solution = "Nothing needs to be added.";
	static int minimum = Integer.MAX_VALUE;
	public static void main(String[] args) throws FileNotFoundException {
		for (int i=0; i<1000000; i++) {
			solve();
		}
	}
	
	static void solve() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(INFILE));
		Vector<Bit> bits = new Vector<Bit>();
		solution = "Nothing needs to be added.";
		minimum = Integer.MAX_VALUE;

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
		Random r = new Random();		
		Fitting ay = bits.get(r.nextInt(bits.size())).a;
		Fitting bee = bits.get(r.nextInt(bits.size())).a;

		//if (ay != null && bee != null) {
			query(bits, ay, bee);			
		//}
		sc.close();
		return;
	}

	static void query(Vector<Bit> bits, Fitting src, Fitting dest) {
		System.out.println("SRC: " + src.toString());
		System.out.println("DEST: " + dest.toString());
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
		if (cur.complements(dest) && steps <= minimum) {
			solution = path;
			minimum = steps;
			//System.out.println("min is " + minimum);
		}
		Vector<Bit> valid = new Vector<Bit>();
		for (int i=0; i<bits.size(); i++) {
			Bit bit = bits.get(i);
			if (bit.n >= 2 && cur.complements(bit)) {
				//System.out.println(cur.toString() + " complements " + bit.toString());
				Bit newBit = new Bit(2); // new instance
				newBit.a = cur.a;
				newBit.b = bit.b;
				newBit.temp = bit.a;
				valid.add(newBit);
				bits.remove(bit);
				i--;
			}
		}
		//System.out.println(valid.size());
		for (int i=0; i<valid.size(); i++) {
			Bit b = valid.get(i);
			//System.out.println(i + ": " + b.toString());
			BFS(bits, b, dest, steps + 1, path + b.temp.toString() + " / " + b.b.toString() + "\n");

		}
	}

	static void testA() {
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
