import java.util.*;

public class Tree {
    public static void main(String[] args){
        //TreeNode root = buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
        //System.out.println(root.val);
      //  TreeNode root = minBST(new int[]{1,2,3,4,5,6,7,8,9,10});
       // System.out.println(root);
       List<List<Integer>> ans = new ArrayList<>();
       TreeNode root = new TreeNode(50);
       root.left = new TreeNode(20);
       root.left.left = new TreeNode(10);
       root.left.left.right = new TreeNode(15);
       root.left.right = new TreeNode(25);
       root.left.left.left = new TreeNode(5);
       root.right = new TreeNode(60);
       root.right.right = new TreeNode(70);
       root.right.right.right = new TreeNode(80);
       root.right.right.left = new TreeNode(65);
       //printAllCombos(root,ans,null);
       for(List<Integer> l : ans){
        for(int i : l){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
       }
    }
  
  /* Find all paths in a binary tree that sum up to a given value. Paths can start at any node,
     but they have to be going downwards
  */
    public static int totalPathsWithSum(TreeNode root, int target){
        return getTotalPaths(root, target, 0, new HashMap<>());
    }

    private static int getTotalPaths(TreeNode root, int target, int currentTotal, Map<Integer,Integer> sumMap){
        if(root == null)return 0;
        int sum = currentTotal+root.val;
        int diff = sum - target;
        int pathCount = sumMap.getOrDefault(diff, 0);
        if(diff == 0)
            pathCount++;
        sumMap.put(sum,sumMap.getOrDefault(sum, 0)+1);
        pathCount += getTotalPaths(root.left,target,sum,sumMap);
        pathCount += getTotalPaths(root.right,target,sum,sumMap);
        sumMap.put(sum,sumMap.get(sum)-1);
        return pathCount;
    }
    
    //Check if a binary tree is balanced i.e. length(left subtree) - length(right subtree) <= 1;
    public boolean isBalanced(TreeNode root) {
        return isBalanced(root,new Counter());
    }
    
    private boolean isBalanced(TreeNode root,Counter c){
        if(root == null)return true;
        Counter left = new Counter(), right = new Counter();
        boolean l = isBalanced(root.left,left);
        boolean r = isBalanced(root.right,right);
        c.count = Math.max(left.count,right.count)+1;
        return Math.abs(left.count-right.count) <= 1 && l && r;
    }
    
    class Counter{
        public int count = 0;
    }
  
  //Is the second tree a subtree of the first one? size(first) >> size(second)
  //Using preorder traversal followed by String.substring. space and time - O(NlogN + MlogM)
    public static boolean isSubTree1(TreeNode first, TreeNode second){
        if(first == null)return false;
        if(second == null)return true;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        preOrderString(first, sb1);
        preOrderString(second, sb2);
        return sb1.toString().indexOf(sb2.toString()) != -1;
    }

    private static void preOrderString(TreeNode root,StringBuilder sb){
        if(root == null)return;
        sb.append(root.val + " ");
        preOrderString(root.left, sb);
        preOrderString(root.right, sb);
    }
    //More space efficient way to find out if the second tree is a subset of the first.
    //Depending on the input, could be more time efficient as well. Worst Case: O(MN)
    public static boolean isSubTree2(TreeNode first, TreeNode second){
        if(second == null)return true;
        if(first == null)return false;
        if(first.val == second.val && compareTree(first, second)){
            return true;
        }
        return isSubTree2(first.left,second) || isSubTree2(first.right,second);
    }

    private static boolean compareTree(TreeNode first, TreeNode second){
        if(first == null && second == null)
            return true;
        if((first == null || second == null) || (first.val != second.val))
            return false;

        return compareTree(first.left,second.left) && compareTree(first.right, second.right);
    }
    //A Binary Search Tree has been created by streaming the elements of an array in sequence
    //Return all arrays which could have resulted in the given tree structure
    public static ArrayList<LinkedList<TreeNode>> getAllSequences(TreeNode root){
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();
        LinkedList<TreeNode> prefix = new LinkedList<>();
        if(root == null){
            result.add(prefix);
            return result;
        }
        prefix.add(root);

        ArrayList<LinkedList<TreeNode>> left = getAllSequences(root.left);
        ArrayList<LinkedList<TreeNode>> right = getAllSequences(root.right);

        for(LinkedList<TreeNode> l : left){
            for(LinkedList<TreeNode> r : right){
                ArrayList<LinkedList<TreeNode>> weaved = new ArrayList<>();
                weaveLists(l,r,prefix,weaved);
                result.addAll(weaved);
            }
        }

        return result;
    }

    private static void weaveLists(LinkedList<TreeNode> left, LinkedList<TreeNode> right,LinkedList<TreeNode> prefix, ArrayList<LinkedList<TreeNode>> result){
        if(left.size() == 0 || right.size() == 0){
            LinkedList<TreeNode> weaved = new LinkedList<>();
            weaved.addAll(prefix);
            weaved.addAll(left);
            weaved.addAll(right);
            result.add(weaved);
            return;
        }

        TreeNode node = left.removeFirst();
        prefix.addLast(node);
        weaveLists(left,right,prefix,result);
        prefix.removeLast();
        left.addFirst(node);

        node = right.removeFirst();
        prefix.addLast(node);
        weaveLists(left,right,prefix,result);
        prefix.removeLast();
        right.addFirst(node);
    }
    //Given two nodes in a binary tree, find the common ancestor
    public static AResult commonAncestor(TreeNode root, TreeNode n1, TreeNode n2){
        if(root == null)
            return new AResult();
        AResult left = commonAncestor(root.left, n1, n2);
        if(left.anc != null)
            return left;
        AResult right = commonAncestor(root.right, n1, n2);
        if(right.anc != null)
            return right;
        AResult res = new AResult();
        res.node1 = left.node1 || right.node1 || (root == n1);
        res.node2 = left.node2 || right.node2 || (root == n2);
        res.anc = res.node1 && res.node2 ? root : null;
        return res;
    }
    //Find the in-order successor of a node in a binary search tree (nodes have links to parents)
    public static TreeNode successor(TreeNode node){
        if(node == null)return null;
        if(node.right!= null){
            TreeNode n = node.right;
            while(n.left != null){
                n = n.left;
            }
            return n;
        }else{
            TreeNode n = node, p = node.parent;
            while(p != null){
                if(p.left == n)
                    return p;
                n = p;
                p = n.parent;
            }
        }
        return null;
    }
    //Binary Search Tree - in order traversal
    public static void inOrderTraversal(TreeNode root){
        if(root == null)return;
        inOrderTraversal(root.left);
        System.out.println(root.val);
        inOrderTraversal(root.right);
    }

    //Construct a Binary Search Tree of minimum height from a sorted array
    public static TreeNode minBST(int[] array){return minBST(array,0,array.length);}

    private static TreeNode minBST(int[] array, int start, int end){
        if(end - start < 1)
            return null;
        int mid = start + (end-start)/2;
        TreeNode root = new TreeNode(array[mid]);
        root.left = minBST(array,start,mid);
        root.right = minBST(array,mid+1,end);
        return root;
    }
  
    //Construct a Binary Search Tree from the pre-order and in-order values
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    
    private static TreeNode build(int[] preorder, int ps, int pe, int[] inorder, int is, int ie){
        if(ps > pe || is > ie)return null;
        TreeNode root = new TreeNode(preorder[ps]);
        int leftTreeSize = 0;
        for(int i = is;i<=ie;++i){
            if(inorder[i] == preorder[ps]){
                break;
            }
            leftTreeSize++;
        }
        root.left = build(preorder,ps+1,ps+leftTreeSize,inorder,is,is+leftTreeSize-1);
        root.right = build(preorder,ps+leftTreeSize+1,pe,inorder,is+leftTreeSize+1,ie);
        return root;
    }
    
    /* https://leetcode.com/problems/binary-tree-right-side-view/
    Given the root of a binary tree, imagine yourself standing on the right side of it, 
    return the values of the nodes you can see ordered from top to bottom.
    */
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>(),q2 = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if(root != null)q1.add(root);
        boolean flag = true;
        while(!q1.isEmpty()){
            TreeNode n = q1.poll();
            if(flag){
                ans.add(n.val);
                flag = false;
            }
            if(n.right != null)
                q2.add(n.right);
            if(n.left != null)
                q2.add(n.left);
            if(q1.isEmpty()){
                Queue<TreeNode> t = q1;
                q1 = q2;
                q2 = t;
                flag = true;
            }
        }
        return ans;
    }
    
    /*
    Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
    */
    public boolean isValidBST(TreeNode root) {
        return isValid(root,null,null);
    }
    
    private boolean isValid(TreeNode root,Integer min, Integer max){
        if(root == null)return true;
        if((min == null || min < root.val) && (max == null || max > root.val)){
            return isValid(root.left,min,root.val) && isValid(root.right,root.val,max);
        }
        return false;
    }
  
    //Data Structure representing a node in a binary tree
    static class TreeNode{
        int val;
        TreeNode left,right,parent;
        public TreeNode(int val){this.val = val;}
        public String toString(){
            String l = left != null ? left.toString() : "";
            String r = right != null ? right.toString() : "";
            return l + Integer.toString(val) + r;
        }
    }

    static class AResult{
        boolean node1, node2;
        TreeNode anc;
        public AResult(){node1 = false;node2 = false;anc = null;}
    }
}
