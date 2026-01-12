package JeremyMarvel_UAS_OOP_BL;
import java.time.LocalDate;

public class JeremyMarvel_Invoice_BL {
    private JeremyMarvel_Item_BL item;
    private int quantity;
    private double totalAmount;
    private LocalDate date;

    public JeremyMarvel_Invoice_BL(JeremyMarvel_Item_BL item, int quantity, double totalAmount, LocalDate date) {
        this.item = item;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public JeremyMarvel_Item_BL getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDate getDate() { return date; }

    public String printDetail() {
        return item.printDetail() + " | Qty: " + quantity + " | Total: Rp" + totalAmount + " | Tanggal: " + date;
    }
}
