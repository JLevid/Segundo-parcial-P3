/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Levid Abimael
 */
//Clase de prueba (cliente, principio abierto/cerrado aplicado si se quieren probar más árboles)
public class Arbol {
    public static void main(String[] args) {
        TreeBuilder builder = new TreeBuilder();
        TreeNode root = builder.buildExampleTree();

        PathSumFinder finder = new PathSumFinder();
        int targetSum = 22;

        List<List<Integer>> result = finder.findPaths(root, targetSum);
        System.out.println("Caminos con suma " + targetSum + ":");
        for (List<Integer> path : result) {
            System.out.println(path);
        }
    }
}
// Estructura del arbol, clase que representa un nodo del árbol
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
//Clase encargada de encontrar los caminos (una sola responsabilidad)
class PathSumFinder {
    public List<List<Integer>> findPaths(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        findPathsRecursive(root, targetSum, new ArrayList<>(), allPaths);
        return allPaths;
    }

    private void findPathsRecursive(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (node == null) return;

        currentPath.add(node.val);
        targetSum -= node.val;

        if (node.left == null && node.right == null && targetSum == 0) {
            allPaths.add(new ArrayList<>(currentPath)); // Es una hoja y cumple la suma
        }

        findPathsRecursive(node.left, targetSum, currentPath, allPaths);
        findPathsRecursive(node.right, targetSum, currentPath, allPaths);

        currentPath.remove(currentPath.size() - 1); // backtracking
    }
}
//Clase de construcción del árbol (SRP)
class TreeBuilder {
    public TreeNode buildExampleTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        return root;
    }
}
