import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Trees {
    public static int kthSmallestElement(TreeNode<Integer> root, int k) {
        // First is the index of the current element, second is the kth element
        int[] indexAndKthElement = new int[2];

        recursiveFindKthSmallestElement(root, k, indexAndKthElement);
        return indexAndKthElement[1];
    }

    public static void recursiveFindKthSmallestElement(TreeNode<Integer> node, int k, int[] indexAndKthElement) {
        // Early exit if already found kth smallest element
        if (indexAndKthElement[0] == k) {
            return;
        }

        if (node.left != null) {
            recursiveFindKthSmallestElement(node.left, k, indexAndKthElement);
        }

        if (k == ++indexAndKthElement[0]) {
            indexAndKthElement[1] = node.data;
            return;
        }

        if (node.right != null) {
            recursiveFindKthSmallestElement(node.right, k, indexAndKthElement);
        }
    }

    public static int diameterOfBinaryTree(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left) + 1;
        int rightHeight = height(root.right) + 1;
        root.data =
                Math.max(leftHeight + rightHeight, Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
        return root.data;
    }

    public static int height(TreeNode<Integer> root) {
        if (root == null) {
            return -1;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    public static List<Integer> minHeightTrees(int n, int[][] edges) {
        List<Integer> leaves = new ArrayList<>();

        if (n <= 2) {
            for (int i = 0; i < n; i++) {
                leaves.add(i);
            }

            return leaves;
        }

        // Construct adjacency list
        List<Set<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        int nodesRemaining = n;
        for (int i = 0; i < n; i++) {
            if (adjacencyList.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        List<Integer> tempLeaves = new ArrayList<>();

        // Continually remove leaf nodes until reaching the root(s) of the tree
        while (nodesRemaining > 2) {
            for (int leaf : leaves) {
                if (adjacencyList.get(leaf).isEmpty()) {
                    continue;
                }
                int neighbour = adjacencyList.get(leaf).iterator().next();

                adjacencyList.get(neighbour).remove(leaf);

                if (adjacencyList.get(neighbour).size() == 1) {
                    tempLeaves.add(neighbour);
                }
            }

            nodesRemaining -= leaves.size();
            leaves = tempLeaves;
            tempLeaves = new ArrayList<>();
        }

        return leaves;
    }

    public static List<String> serialize(TreeNode<Integer> root) {
        List<String> serialized = new ArrayList<>();

        if (root == null) {
            serialized.add("_");
            return serialized;
        }

        serialized.add(String.valueOf(root.data));
        serialized.addAll(serialize(root.left));
        serialized.addAll(serialize(root.right));

        return serialized;
    }

    public static TreeNode<Integer> deserialize(List<String> stream) {
        Collections.reverse(stream);

        return deserializeHelper(stream);
    }

    public static TreeNode<Integer> deserializeHelper(List<String> stream) {
        if (stream.isEmpty()) {
            return null;
        }

        String data = stream.remove(stream.size() - 1);

        if (data.equals("_")) {
            return null;
        }

        TreeNode<Integer> root = new TreeNode<>(Integer.parseInt(data));
        root.left = deserializeHelper(stream);
        root.right = deserializeHelper(stream);

        return root;
    }

    public static boolean isBalanced(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }

        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public static TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        invertTree(root.left);
        invertTree(root.right);
        TreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public static int findMaxDepth(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(findMaxDepth(root.left), findMaxDepth(root.right)) + 1;
        }
    }

    public static List<Integer> rightSideView(TreeNode<Integer> root) {
        List<Integer> view = new ArrayList<>();
        return rightSideViewHelper(root, 0, view);
    }

    public static List<Integer> rightSideViewHelper(TreeNode<Integer> root, int height, List<Integer> view) {
        if (root == null) {
            return view;
        }

        if (height == view.size()) {
            view.add(root.data);
        }

        view = rightSideViewHelper(root.right, height + 1, view);
        view = rightSideViewHelper(root.left, height + 1, view);
        return view;
    }

    public static TreeNode<Integer> buildTree(int[] pOrder, int[] iOrder) {
        int[] pOrderIndex = new int[] {0};
        List<Integer> pOrderList = new ArrayList<>();
        List<Integer> iOrderList = new ArrayList<>();
        for (int i = 0; i < pOrder.length; i++) {
            pOrderList.add(pOrder[i]);
            iOrderList.add(iOrder[i]);
        }

        return buildTreeHelper(pOrderIndex, pOrderList, iOrderList);
    }

    public static TreeNode<Integer> buildTreeHelper(int[] index, List<Integer> pOrder, List<Integer> iOrder) {
        int data = pOrder.get(index[0]);
        TreeNode<Integer> root = new TreeNode<>(data);
        index[0]++;

        // Split iOrder
        int endLeft = 0;
        while (iOrder.get(endLeft) != data) {
            endLeft++;
        }

        root.left = endLeft == 0 ? null : buildTreeHelper(index, pOrder, iOrder.subList(0, endLeft));
        root.right =
                endLeft + 1 == iOrder.size() ? null : buildTreeHelper(index, pOrder, iOrder.subList(endLeft + 1, iOrder.size()));

        return root;
    }

    public static TreeNode<Integer> lowestCommonAncestorBST(TreeNode<Integer> root, TreeNode<Integer> node1,
                                                            TreeNode<Integer> node2) {
        if (root.data < node1.data && root.data < node2.data) {
            return lowestCommonAncestorBST(root.right, node1, node2);
        } else if (root.data > node1.data && root.data > node2.data) {
            return lowestCommonAncestorBST(root.left, node1, node2);
        } else {
            return root;
        }
    }

    public static boolean validateBst(TreeNode<Integer> root) {
        int[] prev = new int[] {Integer.MIN_VALUE};
        return validateBstHelper(prev, root);
    }

    public static boolean validateBstHelper(int[] prev, TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }

        if (!validateBstHelper(prev, root.left)) {
            return false;
        }

        if (root.data < prev[0]) {
            return false;
        }

        prev[0] = root.data;

        return validateBstHelper(prev, root.right);
    }

    public static String levelOrderTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return "None";
        }

        Queue<TreeNode<Integer>> nodes = new LinkedList<>();
        Queue<Integer> depths = new LinkedList<>();

        depths.add(0);
        nodes.add(root);
        int depth = 0;

        StringBuilder traversalString = new StringBuilder();

        while (!nodes.isEmpty()) {
            TreeNode<Integer> node = nodes.remove();
            Integer nodeDepth = depths.remove();

            if (nodeDepth != depth) {
                traversalString.append(" : ");
                depth = nodeDepth;
            } else {
                traversalString.append(", ");
            }

            traversalString.append(node.data);
            if (node.left != null) {
                nodes.add(node.left);
                depths.add(depth + 1);
            }

            if (node.right != null) {
                nodes.add(node.right);
                depths.add(depth + 1);
            }
        }

        return traversalString.substring(2);
    }

    public static int maxPathSum(TreeNode<Integer> root) {
        int[] maxSum = new int[] {Integer.MIN_VALUE};

        maxPathSumHelper(root, maxSum);

        return maxSum[0];
    }

    public static int maxPathSumHelper(TreeNode<Integer> root, int[] maxSum) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxPathSumHelper(root.left, maxSum));
        int right = Math.max(0, maxPathSumHelper(root.right, maxSum));

        if (root.data + left + right > maxSum[0]) {
            maxSum[0] = root.data + left + right;
        }

        return root.data + Math.max(left, right);
    }

    public static boolean sameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (!p.data.equals(q.data)) {
            return false;
        }

        return sameTree(p.left, q.left) && sameTree(p.right, q.right);
    }

    public static boolean isSubtree(TreeNode<Integer> root, TreeNode<Integer> subRoot) {
        boolean isCurrentSubtree = false;

        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null) {
            return false;
        } else if (root.data.equals(subRoot.data)) {
            isCurrentSubtree = isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right);
        }

        boolean isLeftSubtree = root.left != null && isSubtree(root.left, subRoot);
        boolean isRightSubtree = root.right != null && isSubtree(root.right, subRoot);

        return isCurrentSubtree || isLeftSubtree || isRightSubtree;
    }

    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        boolean[] found = new boolean[] {false, false};
        return lowestCommonAncestorHelper(root, p.data, q.data, found);
    }

    public TreeNode<Integer> lowestCommonAncestorHelper(TreeNode<Integer> root, int p, int q, boolean[] found) {
        if (root == null) {
            return null;
        }

        TreeNode<Integer> left = lowestCommonAncestorHelper(root.left, p, q, found);
        // If found both after exploring the left subtree, LCA must be in left subtree
        if (found[0] && found[1]) {
            return left;
        }

        // Check current node
        if (root.data == p) {
            found[0] = true;
        } else if (root.data == q) {
            found[1] = true;
        }

        // If we've now found both, current node must be LCA
        if (found[0] && found[1]) {
            return root;
        }

        // If neither have been found LCA must be in right subtree
        if (!found[0] && !found[1]) {
            return lowestCommonAncestorHelper(root.right, p, q, found);
        } else {
            // One has been found in left subtree, and the other will be in right subtree, so current node must be LCA
            return root;
        }
    }

    public static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
