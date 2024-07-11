import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationDemo {

    public static void main(String[] args) {
        // Create a list and add some elements
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        // Demonstrate ConcurrentModificationException
        try {
            // Use an iterator to iterate over the list
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                if (element.equals("B")) {
                    // Modify the list while iterating
                    list.remove(element); // This will throw ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught!");
        }

        // Correct way to handle modification during iteration
        try {
            // Use an iterator to iterate over the list
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                if (element.equals("C")) {
                    // Use the iterator's remove method to modify the list
                    iterator.remove(); // This is safe and will not throw exception
                }
            }
            System.out.println("List after safe removal: " + list);
        } catch (ConcurrentModificationException e) {
            System.out.println("This block will not be executed.");
        }

        // Another way to handle modification during iteration: Using a second collection
        try {
            // Create a second list to store elements to be removed
            List<String> toRemove = new ArrayList<>();
            for (String element : list) {
                if (element.equals("A")) {
                    toRemove.add(element);
                }
            }
            // Remove elements from the original list outside the loop
            list.removeAll(toRemove);
            System.out.println("List after removal using a second collection: " + list);
        } catch (ConcurrentModificationException e) {
            System.out.println("This block will not be executed.");
        }
    }
}
