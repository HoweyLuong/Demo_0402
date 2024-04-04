package Project1;

import java.io.*;
import java.util.*;

public class Lab1
{

    /**
     *  Problem 1: Determine if two given binary search trees store the same numbers.
        Iterative Solution
     */
    private static boolean problem1Iterative(Node t1, Node t2)
    {
    	// First of all set up the two stack, so that we can get compared of each node in the stack
    	Stack<Node> temp1 = new Stack<>();
        Stack<Node> temp2 = new Stack<>();
        // Now use the while loop to check the condition if it is not empty and it is not null
        while(!temp1.isEmpty() || !temp2.isEmpty() || t1!= null || t2 != null) {
        	while(t1!= null || t2 != null) {
        		// Check if the node 1 is not null
        		if(t1!= null) {
        			temp1.push(t1);
        			t1= t1.left; // Now node t1 goes to the left
        		}
        		// After that check if Node t2 is not null 
        		if(t2!= null) {
        			temp2.push(t2);
        			t2= t2.left; // Now node t2 goes to the left
        		}
        }
        //Now it is the time to check the condition which can be use && to see if the stack is not empty
        	if(!temp1.isEmpty() && !temp2.isEmpty()) {
        		  t1 = temp1.pop();
                  t2 = temp2.pop();
                  if (t1.key != t2.key) { // If after the compared both of the subtree is not correct, the condition becomes false
                      return false;
                  }
                  t1 = t1.right;
                  t2 = t2.right;

        	}else if (!temp1.isEmpty() || !temp2.isEmpty()) { // Check the condition one more time to see are there any problem in the code
        		return false;
        	}

        }
        // After all the condition check return true, if not anything fall
        return true;
    }
        
     /**
     *  Problem 1: Determine if two given binary search trees store the same numbers.
        Recursive Solution
     */
    private static boolean problem1Recursive(Node t1, Node t2) {
    	 // First of all check if the t1 is null, it matches the t2
        if (t1 == null) {
            return true;
        }

        // If t2 is null but t1 is not, they will cause the false
        if (t2 == null) {
            return false;
        }

        // This find method will see if the key in t1 is not present in t2, then it will return to false	
        if (!find(t2, t1.key)) {
            return false;
        }
	if(!find(t1,t2.key)) {
	    return false;
	}

        // After all return use the recursive from the left and the right of t1 to check the condition, to check all the node is in 
        return problem1Recursive(t1.left, t2) && problem1Recursive(t1.right, t2);
    }
    
    
    

    /**
     *  Problem 2: Determine the sum of all node's keys between min and max (inclusive).
        Iterative Solution
     */
    private static int problem2Iterative(Node root, int min, int max) {
    
    	// First of all set up the total so that in can compare each of that.
    	int total = 0; 
    	// set up the new stack 
        Stack<Node> temp = new Stack<>();
        temp.push(root);// First of all to have the root in the binary search tree
        // Check the condition if the stack is not empty
        while (!temp.isEmpty()) {
            Node n1 = temp.pop();
            // Check if the key is smaller or equal to max or it will larger or equal to n1 
            if (n1.key >= min && n1.key <= max) {
                total += n1.key;
            }
            //check the condition if n1 is not null and it larger then the min
            if (n1.left != null && n1.key > min) {
                temp.push(n1.left);
            }
            // Next check the condition if n1 in the right is not null and it smaller than the max
            if (n1.right != null && n1.key < max) {
                temp.push(n1.right);
            }
        }
        // After all of that return to the total of the problem
        return total;
    }
    
    
    
     /**
     *  Problem 2: Determine the sum of all node's keys between min and max (inclusive).
        Recursive Solution
     */
    private static int problem2Recursive(Node root, int min, int max)
    {
    	//First of all check if the root null or not, if yes return the number to 0
    	 if (root == null) {
             return 0;
         }
    	 // set up the total to check what of that
         int total = 0;
         //check if the root is larger or equal to the min and smaller and equal to the max
         if (root.key >= min && root.key <= max) {
             total += root.key;
         }
         // check one more time if the root is larger than min
         if (root.key > min) {
             total += problem2Recursive(root.left, min, max);
         }
      // check one more time if the root is smaller than max
         if (root.key < max) {
             total += problem2Recursive(root.right, min, max);
         }
         //after all check all the condition
         return total;
    }



    // ---------------------------------------------------------------------
    // Do not change any of the code below!

    private static class Node
    {
        public Node left = null;
        public Node right = null;

        public int key;

        public Node(int key)
        {
            this.key = key;
        }
    }

    private static void insert(Node root, int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return;
        }

        for (Node node = root;;)
        {
            if (key < node.key)
            {
                if (node.left == null)
                {
                    node.left = new Node(key);
                }

                node = node.left;
            }
            else if (key > node.key)
            {
                if (node.right == null)
                {
                    node.right = new Node(key);
                }

                node = node.right;
            }
            else // key = node.key
            {
                // Nothing to do, because no value to update.
                break;
            }
        }
    }
    
    
    private static boolean find(Node root, int key){
      
      if(root == null)
      {
         return false;
      }
    
      if(root.key == key)
      {
           return true;
      }
      
      if(key < root.key)
      {
         return find(root.left, key);
      }
      else
      {
         return find(root.right, key);
      }
    
    }
    

    private static int[] getInOrder(Node root)
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                orderList.add(node.key);
                node = node.right;
            }
            else
            {
                stack.push(node);
                node = node.left;
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    private static int[] getPreOrder(Node root)
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                node = node.right;
            }
            else
            {
                orderList.add(node.key);
                stack.push(node);
                node = node.left;
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    private static int[] getPostOrder(Node root)
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        Stack<Integer> stackCtr = new Stack<Integer>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        stack.push(root);
        stackCtr.push(0);

        while (!stack.empty())
        {
            int ctr = stackCtr.pop();
            Node node = stack.peek();

            if (ctr == 0)
            {
                // First visit.
                stackCtr.push(1);

                if (node.left != null)
                {
                    stack.push(node.left);
                    stackCtr.push(0);
                }
            }
            else if (ctr == 1)
            {
                // Second visit.
                // Left subtree done.
                stackCtr.push(2);

                if (node.right != null)
                {
                    stack.push(node.right);
                    stackCtr.push(0);
                }
            }
            else // ctr >= 2
            {
                // Third visit.
                // Right subtree done.
                stack.pop();
                orderList.add(node.key);
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    // ---------------------------------------------------------------------

    private static final int LabNo = 1;
    private static final String classNum = "CS 302";

    private static final Random rng = new Random(654321);

    public static void main(String args[])
    {
        System.out.println(classNum + " -- Lab " + LabNo);

        testProblems(1, 1);
        testProblems(1, 2);
        testProblems(2, 1);
        testProblems(2, 2);
    }

    private static boolean testProblem1(int[][] testCase, int style)
    {
        int[] tree1 = testCase[0];
        int[] tree2 = testCase[1];

        boolean solution = testCase[2][0] == 1;

        Node root1 = new Node(tree1[0]);
        Node root2 = new Node(tree2[0]);

        for (int i = 1; i < tree1.length; i++)
        {
            insert(root1, tree1[i]);
            insert(root2, tree2[i]);
        }
        
        boolean answer;
        
        if(style == 1)
        {
           answer = problem1Iterative(root1, root2); 
        }else{
           answer = problem1Recursive(root1, root2);
        } 

        return solution == answer;
    }

    private static boolean testProblem2(int[][] testCase, int style)
    {
        int[] tree = testCase[0];
        int[] range = testCase[1];
        int solution = testCase[2][0];

        Node root = new Node(tree[0]);

        for (int i = 1; i < tree.length; i++)
        {
            insert(root, tree[i]);
        }

        int answer;
        
        if(style == 1)
        {
           answer = problem2Iterative(root, range[0], range[1]); 
        }else{
           answer = problem2Recursive(root, range[0], range[1]);
        } 

        return answer == solution;
    }

    private static void testProblems(int prob, int style)
    {
        int noOfLines = 100000;

        System.out.println("-- -- -- -- --");
        
        switch (style)
        {
            case 1:
                  System.out.println(noOfLines + " test cases for problem " + prob + " iterative solution.");
                  break;
            case 2:
                  System.out.println(noOfLines + " test cases for problem " + prob + " recursive solution.");
                  break;
        }
        
        boolean passedAll = true;

        for (int i = 1; i <= noOfLines; i++)
        {
            boolean passed = false;
            boolean exce = false;
            int[][] testCase = null;

            try
            {
                switch (prob)
                {
                    case 1:
                        testCase = createProblem1(i);
                        passed = testProblem1(testCase, style);
                        break;

                    case 2:
                        testCase = createProblem2(i);
                        passed = testProblem2(testCase, style);
                        break;
                }
            }
            catch (Exception ex)
            {
                passed = false;
                exce = true;
            }

            if (!passed)
            {
                System.out.println("Test " + i + " failed!" + (exce ? " (Exception)" : ""));

                if (prob == 1)
                {
                    System.out.println("  tree 1: " + Arrays.toString(testCase[0]));
                    System.out.println("  tree 2: " + Arrays.toString(testCase[1]));
                }
                else
                {
                    System.out.println("    tree: " + Arrays.toString(testCase[0]));
                    System.out.println("   range: " + Arrays.toString(testCase[1]));
                }
                passedAll = false;
                break;
            }
        }

        if (passedAll)
        {
            System.out.println("All test passed.");
        }

    }

    private static void shuffle(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int rndInd = rng.nextInt(arr.length - i) + i;

            int tmp = arr[i];
            arr[i] = arr[rndInd];
            arr[rndInd] = tmp;
        }
    }

    private static int[] getRandomNumbers(int size)
    {
        int maxSize = size * 10;

        int[] integers = new int[maxSize];
        for (int i = 0; i < maxSize; i++)
        {
            integers[i] = i;
        }

        shuffle(integers);

        return Arrays.copyOf(integers, size);
    }

    private static int[][] createProblem1(int max)
    {
        int maxSize = max < 250 ? max : 250;
        int size = rng.nextInt(maxSize) + 5;

        int equal = rng.nextInt(2);

        int[] tree1 = getRandomNumbers(size);
        int[] tree2 = tree1.clone();

        if (equal == 0)
        {
            int ind = rng.nextInt(tree1.length);
            tree1[ind]+=6543;    
        }
        
        int chance = rng.nextInt(10);
        
        if(chance == 2)
        {
            int ind1 = rng.nextInt(tree1.length);
            int ind2 = rng.nextInt(tree1.length);
            int temp = tree1[ind1];
            tree1[ind1] = tree1[ind2];
            tree1[ind2] = temp; 
        
        }


        return new int[][]
        {
            tree1,
            tree2,
            new int[] { equal }
        };
    }

    private static int[][] createProblem2(int max)
    {
        int maxSize = max < 250 ? max : 250;
        int size = rng.nextInt(maxSize) + 5;

        int[] keys = getRandomNumbers(2 * size);

        int minKey = keys[rng.nextInt(2 * size)];
        int maxKey = keys[rng.nextInt(2 * size)];

        shuffle(keys);

        int[] tree = Arrays.copyOf(keys, size);

        int sum = 0;

        for (int i = 0; i < tree.length; i++)
        {
            if (tree[i] >= minKey && tree[i] <= maxKey)
            {
                sum += tree[i];
            }
        }

        return new int[][]
        {
            tree,
            new int[] { minKey, maxKey },
            new int[] { sum }
        };
    }

}
 
