package JeremyMarvel_UAS_OOP_BL;
import java.time.LocalDate;

public class JeremyMarvel_Ticket_BL extends JeremyMarvel_Item_BL {
    private LocalDate validUntil;

    public JeremyMarvel_Ticket_BL(int id, String name, double price, int stock, String category, LocalDate validUntil) {
        super(id, name, price, stock, category);
        this.validUntil = validUntil;
    }

    public LocalDate getValidUntil() { return validUntil; }
    public void setValidUntil(LocalDate validUntil) { this.validUntil = validUntil; }

    @Override
    public String printDetail() {
        return "[Ticket] " + name + " | Rp" + price + " | Berlaku sampai: " + validUntil;
    }

 
}
