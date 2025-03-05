import twoThreeFourTree.TwoThreeFourTree;

public class Main {

	public static void main(String[] args) {
		TwoThreeFourTree<Integer> tree = new TwoThreeFourTree<Integer>();
		int[] numbers = {13, 24, 34, 23, 98, 45, 75, 46, 30, 12, 2};

		for(int n: numbers) {
			// System.out.println("Insert: " + n); // You may uncomment this for debugging

			tree.insert(n);

			// tree.levelTraverse(); // You may uncomment this for debugging
			// System.out.println("");
		}

		tree.levelTraverse();

		// Expected tree: 
		//    13 , 24 , 45
		//   /   \    \    \
		// 2,12  23  30,34  46,75,98

		System.out.println();


		TwoThreeFourTree<String> sTree = new TwoThreeFourTree<String>();
		String[] names = { "Happy", "Natsu", "Gray", "Lucy", "Erza", 
				"Luxus", "Elfman", "Gageel", "Charla", "A black cat whoes name I forgot",
				"Luffy", "Zoro", "Jimbe", "Sanji", "Nami", 
				"Robin", "Chopper", "Franky", "Robin", "Brook"
		};

		for(String s: names) {
			// System.out.println("Insert: " + s); // You may uncomment this for debugging

			sTree.insert(s);

			// sTree.levelTraverse(); // You may uncomment this for debugging
			// System.out.println("");
		}

		sTree.levelTraverse();

		// Expected tree:
		//                                            Happy
		//                   Charla,Erza                                            Luxus,Natsu,Samji
		// A black..,Brook  Chopper,Elfman  Franky,Gageel,Gray       Jimbe,Lucy,Luffy   Nami  Robin  Zoro
	}

}
