package JeremyMarvel_UAS_OOP_BL;

public class JeremyMarvel_Recording_BL extends JeremyMarvel_Item_BL {
    private int durationInMinutes;

    public JeremyMarvel_Recording_BL(int id, String name, double price, int stock, String category, int duration) {
        super(id, name, price, stock, category);
        this.durationInMinutes = duration;
    }

    public int getDurationInMinutes() { return durationInMinutes; }
    public void setDurationInMinutes(int duration) { this.durationInMinutes = duration; }

    @Override
    public String printDetail() {
        return "[Recording] " + name + " | Rp" + price + " | Durasi: " + durationInMinutes + " menit";
    }
}

