package nl.arothuis.bep.domain;

import java.math.BigDecimal;

//@SuppressWarnings("PMD.UnusedPrivateField")
public class Product {
    private final String name;
    private final Integer quantity;
    private final BigDecimal price;

    public Product(String name, Integer quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal calculateTotalPrice() {
        return BigDecimal.valueOf(this.quantity)
                .multiply(this.price);
    }
}
