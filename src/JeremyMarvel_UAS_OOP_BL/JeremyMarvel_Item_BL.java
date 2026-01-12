package JeremyMarvel_UAS_OOP_BL;

import java.time.LocalDate;
import java.util.Random;

public abstract class JeremyMarvel_Item_BL implements Discountable, Printable {
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    protected String category;

    public JeremyMarvel_Item_BL(int id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public void addStock(int stock) { this.stock += stock; }
    public void minusStock(int stock) { if (this.stock >= stock) this.stock -= stock; }

    @Override
    public double countDiscount(int quantity) {
        double discount = 0.0;

        // Diskon 10% untuk pembelian lebih dari 3 item
        if (quantity > 3) {
            discount += 0.10; 
        }

        // Diskon di tanggal dan bulan yg sama
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        
        if ((currentDay == currentMonth) || (currentDay == currentMonth)) {
            Random rand = new Random();
            
            double flashSaleDiscount = 0.05 + (0.15 * rand.nextDouble()); // Generates a number between 0.05 and 0.20
            discount = Math.max(discount, flashSaleDiscount); // Ambil diskon tertinggi jika ada diskon lain
        }
        
        return discount;
    }

    @Override
    public abstract String printDetail();
}