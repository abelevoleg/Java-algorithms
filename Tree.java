package hw6;

import hw4.Cat;

import java.util.ArrayList;

public class Tree {
    // travers
    // delete

    private class TreeNode implements Comparable {
        private Cat c;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Cat c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "c=" + c.toString() +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Cat))
                throw new ClassCastException("Not a cat");
            return c.getAge() - ((Cat) o).getAge();
        }
    }

    TreeNode root;

    public void insert(Cat c) {
        TreeNode node = new TreeNode(c);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                parent = current;
                if (c.getAge() < current.c.getAge()) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else if (c.getAge() > current.c.getAge()){
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public Cat find(int age) {
        TreeNode current = root;
        while (current.c.getAge() != age) {
            current = (age < current.c.getAge()) ? current.left : current.right;
            if (current == null) return null;
        }
        return current.c;
    }

    public int numberLevel(int age) {
        TreeNode current = root;
        int level = 1;
        while (current.c.getAge() != age) {
            current = (age < current.c.getAge()) ? current.left : current.right;
            level++;
            if (current == null) return 0;
        }
        return level;
    }

    ArrayList<Integer> heights = new ArrayList<>();
    public boolean isBalanced(TreeNode current) {
        if (current.left == null && current.right == null) {
            heights.add(numberLevel(current.c.getAge()));
            if (Math.abs(heights.get(0) - current.c.getAge()) > 1) return false;
            return true;
        } else if (current.left == null && current.right != null) {
            if (current.right.left != null && current.right.right != null)
                return false;
            else return true;
        } else if (current.right == null && current.left != null) {
            if (current.left.left != null && current.left.right != null)
                return false;
            else return true;
        }
        return isBalanced(current.left) && isBalanced(current.right);
    }

    private void preOrderTraverse(TreeNode current, int flag) {
        if (current != null) {
            System.out.print(current.c.getAge() + " ");
            if (flag == -1 || flag == 0) System.out.print("( ");
            if (flag == 1) System.out.print(") ");

            if (current.left == null && current.right != null) System.out.print("N ");
            preOrderTraverse(current.left, -1);
            if (current.left != null && current.right == null) System.out.print("N ) ");
            preOrderTraverse(current.right, 1);
        }
    }

    public void displayTree() {
        preOrderTraverse(root, 0);
    }

    public boolean delete(int age) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.c.getAge() != age) {
            parent = current;
            if (age < current.c.getAge()) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) {
                return false;
            }
        }

        // leaf
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        // one ancestor
        else if (current.right == null) {
            if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        // two ancestors
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode s = node;
        TreeNode curr = node.right;
        while (curr != null) {
            parent = s;
            s = curr;
            curr = curr.left;
        }

        if (s != node.right) {
            parent.left = s.right;
            s.right = node.right;
        }
        return s;
    }

}
// 23 22 89 25 10 18 39 53 75 27 9 16 87 33 17 23 17

// 9 (4 (2 (1, 3), 8 (6 (5, 7), N)), 13 (11 (10, 12), 15 (14, 16)))
