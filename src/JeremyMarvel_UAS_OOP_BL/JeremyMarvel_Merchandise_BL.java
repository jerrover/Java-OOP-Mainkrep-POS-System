package JeremyMarvel_UAS_OOP_BL;

public class JeremyMarvel_Merchandise_BL extends JeremyMarvel_Item_BL {
    private String type;

    public JeremyMarvel_Merchandise_BL(int id, String name, double price, int stock, String category, String type) {
        super(id, name, price, stock, category);
        this.type = type;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String printDetail() {
        return "[Merchandise] " + name + " | Rp" + price + " | Stok: " + stock + " | Tipe: " + type;
    }
}
