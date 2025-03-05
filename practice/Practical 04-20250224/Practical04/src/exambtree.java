import java.util.Random;
import java.util.random.*;
// Java Program for Implementaion B-Tree

class BTreeNode {
    // Variables Declared
    int[] keys; // Array to store keys
    int t; // Minimum degree (defines the range for number of keys)
    BTreeNode[] children; // Array to store child pointers
    int n; // Current number of keys
    boolean leaf; // True when node is leaf, else False

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;

        keys = new int[2 * t - 1];
        children = new BTreeNode[2 * t];
        n = 0;
    }

    // Function to search given key in subtree
    // rooted with this node
    public BTreeNode search(int key) {
        int i = 0;

        while (i < n && key > keys[i])
            i++;

        if (keys[i] == key)
            return this;

        if (leaf)
            return null;

        return children[i].search(key);
    }

    // Function to insert a new key
    // in subtree rooted with this node
    public void insertNonFull(int key) {
        int i = n - 1;

        if (leaf) { //如果当前节点是叶子节点
            while (i >= 0 && key < keys[i]) {
                System.out.printf("len of keys is %d keys[i+1] is %d\n", keys.length,keys[i + 1]);
                keys[i + 1] = keys[i];
                i--; // 寻找合适的插入节点
            }
            keys[i + 1] = key;
            n++;
        } else {
            while (i >= 0 && key < keys[i])
                i--;
            i++;

            if (children[i].n == 2 * t - 1) {
                splitChild(i, children[i]);
                if (key > keys[i])
                    i++;
            }
            children[i].insertNonFull(key);
        }
    }

    // Function to split the child node
    public void splitChild(int i, BTreeNode y) {
        /*
        Each leaf node can have up to the 2t-1 keys.
        Internal nodes can have up to the 2t-1 keys and 2t children.
        */
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++)//复制 y 的后半部分键值到 z
            z.keys[j] = y.keys[j + t];//节点 y 的后半部分键值

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.children[j] = y.children[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            children[j + 1] = children[j];

        children[i + 1] = z;

        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];
        n++;
    }

    // Function to print all keys in the
    // subtree rooted with this node
    public void printInOrder() {
        int i;

        for (i = 0; i < n; i++) {
            if (!leaf)
                children[i].printInOrder();
            System.out.print(keys[i] + " ");
        }

        if (!leaf)
            children[i].printInOrder();
    }
}

class BTree {
    // Pointer to root node
    private BTreeNode root;

    // Minimum degree
    private int t;

    public BTree(int t) {
        this.t = t;
        root = null;
    }

    // Function to search a key in this tree
    public BTreeNode search(int key) {
        return (root == null) ? null : root.search(key);
    }

    // Function to insert a key into the B-tree
    public void insert(int key) {
        System.out.printf("insert elements%d \n", key);
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) { //插入满了
                BTreeNode newRoot = new BTreeNode(t, false);
                newRoot.children[0] = root;
                newRoot.splitChild(0, root);

                int i = 0;

                if (newRoot.keys[0] < key)
                    i++;

                newRoot.children[i].insertNonFull(key);
                root = newRoot;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    // Function to print the entire B-tree
    public void printBTree() {
        if (root != null)
            root.printInOrder();

        System.out.println();
    }

    public static void main(String[] args) {
        // Create a B-tree with minimum degree 3
        Random random = new Random();
        BTree bTree = new BTree(3);
        for (int i = 0; i <= 100; i++) {
            int c = random.nextInt(0, 100);
            bTree.insert(c);
        }


        System.out.print("B-tree : ");
        bTree.printBTree();

        int searchKey = 6;
        BTreeNode foundNode = bTree.search(searchKey);

        if (foundNode != null)
            System.out.println("Key " + searchKey + " found in the B-tree.");
        else
            System.out.println("Key " + searchKey + " not found in the B-tree.");
    }
}
