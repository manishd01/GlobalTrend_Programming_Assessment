import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class IntervalTreeNode {
    Interval interval;
    int maxEnd; // Max end value in the subtree rooted at this node
    IntervalTreeNode left;
    IntervalTreeNode right;

    public IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.maxEnd = interval.end;
        this.left = null;
        this.right = null;
    }
}

public class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTree() {
        root = null;
    }

    // Insert a new interval [start, end] into the interval tree
    public void insertInterval(int start, int end) {
        Interval interval = new Interval(start, end);
        root = insertInterval(root, interval);
    }

    private IntervalTreeNode insertInterval(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return new IntervalTreeNode(interval);
        }

        // Insert into left subtree
        if (interval.start < node.interval.start) {
            node.left = insertInterval(node.left, interval);
        } else {
            // Insert into right subtree
            node.right = insertInterval(node.right, interval);
        }

        // Update maxEnd in current node
        if (interval.end > node.maxEnd) {
            node.maxEnd = interval.end;
        }

        return node;
    }

    // Delete an interval [start, end] from the interval tree
    public void deleteInterval(int start, int end) {
        Interval interval = new Interval(start, end);
        root = deleteInterval(root, interval);
    }

    private IntervalTreeNode deleteInterval(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return null;
        }

        // Search for the interval to delete
        if (interval.start < node.interval.start) {
            node.left = deleteInterval(node.left, interval);
        } else if (interval.start > node.interval.start) {
            node.right = deleteInterval(node.right, interval);
        } else {
            // Found the interval to delete
            if (interval.end == node.interval.end) {
                // Case 1: Node to be deleted has no children or only one child
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }

                // Case 2: Node to be deleted has two children
                IntervalTreeNode successor = findMin(node.right);
                node.interval = successor.interval;
                node.right = deleteInterval(node.right, successor.interval);
            } else {
                // Interval to delete is within the current node's interval
                node.right = deleteInterval(node.right, interval);
            }
        }

        // Update maxEnd in current node
        if (node != null) {
            node.maxEnd = Math.max(node.interval.end, getMaxEnd(node.right));
        }

        return node;
    }

    private IntervalTreeNode findMin(IntervalTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int getMaxEnd(IntervalTreeNode node) {
        return node != null ? node.maxEnd : Integer.MIN_VALUE;
    }

    // Find all intervals that overlap with [start, end]
    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlappingIntervals(root, start, end, result);
        return result;
    }

    private void findOverlappingIntervals(IntervalTreeNode node, int start, int end, List<Interval> result) {
        if (node == null) {
            return;
        }

        // If current interval overlaps with [start, end], add it to result
        if (node.interval.start <= end && node.interval.end >= start) {
            result.add(node.interval);
        }

        // Traverse left subtree if it might contain overlapping intervals
        if (node.left != null && node.left.maxEnd >= start) {
            findOverlappingIntervals(node.left, start, end, result);
        }

        // Traverse right subtree if it might contain overlapping intervals
        if (node.right != null && node.right.interval.start <= end) {
            findOverlappingIntervals(node.right, start, end, result);
        }
    }

    public static void main(String[] args) {
        IntervalTree intervalTree = new IntervalTree();

        // Example usage
        intervalTree.insertInterval(15, 20);
        intervalTree.insertInterval(10, 30);
        intervalTree.insertInterval(5, 12);
        intervalTree.insertInterval(25, 35);
        intervalTree.insertInterval(22, 27);

        System.out.println("Intervals overlapping with [14, 23]: " + intervalTree.findOverlappingIntervals(14, 23));
        System.out.println("Intervals overlapping with [21, 24]: " + intervalTree.findOverlappingIntervals(21, 24));
    }
}
