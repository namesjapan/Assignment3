public class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final double price;

    public Product(String productId, String name, String category, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ID: " + productId + ", Name: " + name + ", Category: " + category + ", Price: " + price;
    }
}