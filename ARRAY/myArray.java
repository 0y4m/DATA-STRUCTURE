import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class myArray {
    int[] arr;
    int size, index, ctr;

    public myArray(int s) {
        size = s;
        arr = new int[size];
        index = -1;
        ctr = 0;
    }

    public boolean isFull() {
        return ctr >= size;
    }

    public boolean isEmpty() {
        return ctr == 0;
    }

    // insert()
    public void insert(int val) {
        if (!isFull()) {
            arr[++index] = val;
            ctr++;
        } else {
            System.out.println("Array is full!");
        }
    }

    // view()
    public void view() {
        for (int i = 0; i < index + 1; i++) {
            System.out.print("[" + arr[i] + "]");
        }
    }

    // count()
    public int count() {
        return ctr;
    }

    // remove()
    public void delete(int idx) {
        if (idx >= 0 && idx < ctr) {
            arr[idx] = 0;
        } else {
            System.out.println("Invalid index for deletion.");
        }
        ctr--;
    }

    // edit()
    public void update(int idx, int newVal) {
        if (idx >= 0 && idx < ctr) {
            arr[idx] = newVal;
        } else {
            System.out.println("Invalid index for updating.");
        }
    }

    // search()
    public int search(int val) {
        for (int i = 0; i < ctr; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    // sort()
    public void sortAscending() {
        Arrays.sort(arr, 0, ctr);
    }

    public void sortDescending() {
        Arrays.sort(arr, 0, ctr);
        for (int i = 0; i < ctr / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[ctr - i - 1];
            arr[ctr - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
          int size = 0;
            while (size <= 0) {
                 try {
                    System.out.println("===============================");
                    System.out.print("Enter the size of the array: ");                   
                    size = scan.nextInt();
                     System.out.println("===============================");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    scan.next();                 }
            }
            System.out.println("===============================");

            myArray m = new myArray(size);
            System.out.println("Insert elements into the array:");

            while (true) {
                System.out.print("Enter an element (0 to stop): ");
                int val = 0;
                try {
                    val = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a positive number.");
                    scan.next();
                    continue;
                }

                System.out.println("===============================");

                if (val == 0) {
                    break;
                }
                m.insert(val);
            }
            System.out.println("\nOriginal Array:");
            m.view();
            System.out.println("\nCount: " + m.count());

            while (true) {
                System.out.println("===========================================================================================");
                System.out.print("Choose an operation ([1] = delete | [2] = update | [3] = search | [4] = sort | [5] = exit): ");
                int operation = 0;
                try {
                    operation = scan.nextInt();
                    System.out.println("===========================================================================================");
                } catch (InputMismatchException e) {
                    System.out.println("===========================================================================================");
                    System.out.println("Invalid input. Please enter an integer.");
                    scan.next();
                    continue;
                }
                
                if (operation == 1) { // 1 = delete
                    System.out.print("Enter the index to delete: ");
                    int index = 0;
                    try {
                        index = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.next(); 
                        continue;
                    }
                    m.delete(index);
                    System.out.println("===============================");

                } else if (operation == 2) { // 2 = update
                    System.out.print("Enter the index to update: ");
                    int idx = 0;
                    try {
                        idx = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.next();
                        continue;
                    }
                    System.out.print("Enter the new value to update: ");
                    int newVal = 0;
                    try {
                        newVal = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.next(); 
                        continue;
                    }
                    m.update(idx, newVal);

                } else if (operation == 3) { // 3 = search
                    System.out.print("Enter the value to search: ");
                    int val = 0;
                    try {
                        val = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.next();
                        continue;
                    }
                    int searchResult = m.search(val);
                    if (searchResult != -1) {
                        System.out.println("Value found at index: " + searchResult);
                    } else {
                        System.out.println("The value is not found in the array.");
                    }

                } else if (operation == 4) { // 4 = sort
                    System.out.print("Enter a sorting order ( 0 = ascending | 1 = descending ): ");
                    int choice = 0;
                    try {
                        choice = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter 0 or 1 for sorting order.");
                        scan.next(); 
                        continue;
                    }
                    if (choice == 0) {
                        m.sortAscending();
                    } else if (choice == 1) {
                        m.sortDescending();
                    } else {
                        System.out.println("Invalid sorting choice.");
                    }

                } else if (operation == 5) { // 5 = exit
                     System.out.println("The program exit successfully.");
                     System.exit(0);
                } else {
                    System.out.println("Please choose the correct operation [1] = delete | [2] = update | [3] = search | [4] = sort | [5] = exit.");
                }
                System.out.println("\nArray After Operation:");
                m.view();
                System.out.println("\nCount: " + m.count());
            }
        }
    }
}
