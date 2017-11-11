 package movements;

import java.util.ArrayList;
import java.util.TreeSet;

public class Partition {

	private static TreeSet<String> a = new TreeSet<>();

	private static void partition(int n, int k, String prefix) {
        if (k == 0 && n == 0) {
            a.add(prefix);
            return;
        }
        if (k < 0 || n < 0) return;

        for (int i = n; i >= 0; --i) {
            partition(n-i, k-1, prefix + "" + i);
        }
    }

	public static ArrayList<String> generationSand(int sand, int numPoint) {
		partition(sand, numPoint, "");
		ArrayList<String> b = new ArrayList<>();
		b.addAll(a);
		Partition.a.clear();
		return b;
	}
	
}
