import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        DataLoader.loadData("amazon-product-data.csv", tree);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter up to 3 product IDs to search. Type 'done' to finish early.");

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter product ID #" + i + ": ");
            String searchId = scanner.nextLine();
            if (searchId.equalsIgnoreCase("done")) {
                break;
            }
            Product result = tree.search(searchId);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("Product not found.");
            }
        }

        System.out.println("\nInsertions:");
        try {
            Product newProduct = new Product("6ab64ce3af7e45ddaf7cf3a837ee2dd4", "Fidget Toy", "Toys", 20.99);
            tree.insert(newProduct);
            System.out.println("Inserted new product: " + newProduct);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            Product duplicateProduct = new Product("4c69b61db1fc16e7013b43fc926e502d", "Duplicate Toy", "Toys", 100.99);
            tree.insert(duplicateProduct);
            System.out.println("Inserted duplicate product: " + duplicateProduct);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}