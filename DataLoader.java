import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class DataLoader {
    private static final Pattern CSV_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    private static final Pattern PRICE_CLEANER = Pattern.compile("[^0-9.]");

    public static void loadData(String filePath, RedBlackTree tree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = CSV_PATTERN.split(line);
                if (fields.length < 4) {
                    continue;
                }
                String productId = fields[0].replace("\"", "").trim();
                String name = fields[1].replace("\"", "").trim();
                String category = fields[2].replace("\"", "").trim();
                String priceField = fields[3].replace("\"", "").trim();
                priceField = PRICE_CLEANER.matcher(priceField).replaceAll("");
                double price = 0.0;
                try {
                    price = Double.parseDouble(priceField);
                } catch (NumberFormatException e) {
                    continue;
                }
                try {
                    tree.insert(new Product(productId, name, category, price));
                } catch (IllegalArgumentException e) {
                    // Ignore duplicate products silently
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}