import java.util.*;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок	
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    // creating tree from not sorting array
    public void GenerateTree(int[] a) {
        Arrays.sort(a);
        recursForGenerateTree(null, a, 1);
    }

    // tree is balanced?
    public boolean IsBalanced(BSTNode root_node) {
        Stack<BSTNode> stack = new Stack<>(); // for search in deep
        stack.push(root_node);

        int level = root_node.Level;
        int maxLevel = level;

        while (!stack.isEmpty()) {
            BSTNode node = stack.pop();
            if (node.Level > level) {
                level = node.Level;
            } else if (node.Level < level) {
                maxLevel = level;
                level = node.Level;
            }
            if (node.LeftChild != null)
                stack.add(node.LeftChild);
            if (node.RightChild != null)
                stack.add(node.RightChild);
        }

        return Math.abs(maxLevel - level) <= 1;
    }

    private BSTNode recursForGenerateTree(BSTNode parentNode, int[] arr, int level) {
        // condition for end recursion
        if (arr.length == 0) return null;

        // assisting variables
        int length = arr.length;
        int indexCenter = length / 2;

        // create new BSTNode
        BSTNode newNode = new BSTNode(arr[indexCenter], parentNode);
        if (parentNode == null) Root = newNode;
        newNode.Level = level++;

        // using recurs
        int[] arrLeft = Arrays.copyOfRange(arr, 0, indexCenter);
        newNode.LeftChild = recursForGenerateTree(newNode, arrLeft, level);
        int[] arrRight = Arrays.copyOfRange(arr, indexCenter + 1, length);
        newNode.RightChild = recursForGenerateTree(newNode, arrRight, level);

        return newNode;
    }
}  