package JeremyMarvel_UAS_OOP_BL;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<JeremyMarvel_Item_BL> items = new ArrayList<>();
    private static List<JeremyMarvel_Invoice_BL> invoices = new ArrayList<>();
    private static int itemIdCounter = 1;

    public static void main(String[] args) {
        loadItemsFromFile();
        if (items.isEmpty()) {
            seedItems();
        }
        while (true) {
            System.out.println("\n1. Tambah Barang\n2. Lihat Barang\n3. Beli Barang\n4. Cetak Laporan\n5. Edit Barang\n6. Hapus Barang\n7. Cari Barang\n8. Keluar");
            System.out.print("Pilih: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addItem();
                case 2 -> printListOfItem();
                case 3 -> buyItems();
                case 4 -> printInvoices();
                case 5 -> editItem();
                case 6 -> deleteItem();
                case 7 -> searchItems();
                case 8 -> exit();
            }
        }
    }

    private static int generateItemId() {
        return itemIdCounter++;
    }
    
    private static void seedItems() {
        itemIdCounter = 1; 

        items.add(new JeremyMarvel_Ticket_BL(generateItemId(), "Java Jazz 2026", 950000.0, 50, "ticket", LocalDate.of(2026, 5, 31)));
        items.add(new JeremyMarvel_Ticket_BL(generateItemId(), "The Sounds Project 2025", 200000.0, 30, "ticket", LocalDate.of(2025, 11, 15)));
        items.add(new JeremyMarvel_Merchandise_BL(generateItemId(), "T-Shirt Guns n Roses", 85000.0, 100, "merchandise", "Kaos Katun"));
        items.add(new JeremyMarvel_Merchandise_BL(generateItemId(), "Poster Noah", 75000.0, 75, "merchandise", "Art Paper"));
        items.add(new JeremyMarvel_Recording_BL(generateItemId(), "Tulus 2024", 150000.0, 25, "recording", 60));
        items.add(new JeremyMarvel_Recording_BL(generateItemId(), "Tompi - Java Jazz 2024", 125000.0, 15, "recording", 90));
        saveItemsToFile();
    }
    
    private static void addItem() {
        try {
            System.out.print("Nama Barang: ");
            String name = scanner.nextLine();
            System.out.print("Harga: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Stok: ");
            int stock = Integer.parseInt(scanner.nextLine());
            System.out.print("Kategori [ticket/merchandise/recording]: ");
            String category = scanner.nextLine().toLowerCase();

            int id = generateItemId();
            JeremyMarvel_Item_BL item = null;

            switch(category) {
                case "ticket" -> {
                    System.out.print("Valid Until (yyyy-mm-dd): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    item = new JeremyMarvel_Ticket_BL(id, name, price, stock, category, date);
                }
                case "merchandise" -> {
                    System.out.print("Tipe: ");
                    String type = scanner.nextLine();
                    item = new JeremyMarvel_Merchandise_BL(id, name, price, stock, category, type);
                }
                case "recording" -> {
                    System.out.print("Durasi (menit): ");
                    int duration = Integer.parseInt(scanner.nextLine());
                    item = new JeremyMarvel_Recording_BL(id, name, price, stock, category, duration);
                }
                default -> {
                    System.out.println("Kategori tidak valid!");
                    return;
                }
            }

            items.add(item);
            saveItemsToFile();
            System.out.println("Barang berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan input: " + e.getMessage());
        }
    }

    public static void editItem() {
        printListOfItem();
        System.out.print("Masukkan ID barang yang ingin diedit: ");
        int id = Integer.parseInt(scanner.nextLine());
        JeremyMarvel_Item_BL item = getItemById(id);
        if (item == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        System.out.print("Nama baru (kosongkan jika tidak berubah): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) item.setName(name);

        System.out.print("Harga baru (-1 jika tidak berubah): ");
        double price = Double.parseDouble(scanner.nextLine());
        if (price >= 0) item.setPrice(price);

        System.out.print("Stok baru (-1 jika tidak berubah): ");
        int stock = Integer.parseInt(scanner.nextLine());
        if (stock >= 0) item.setStock(stock);

        if (item instanceof JeremyMarvel_Ticket_BL ticket) {
            System.out.print("Tanggal baru (yyyy-mm-dd) atau kosong: ");
            String date = scanner.nextLine();
            if (!date.isEmpty()) ticket.setValidUntil(LocalDate.parse(date));
        } else if (item instanceof JeremyMarvel_Merchandise_BL merch) {
            System.out.print("Tipe baru: ");
            String type = scanner.nextLine();
            if (!type.isEmpty()) merch.setType(type);
        } else if (item instanceof JeremyMarvel_Recording_BL rec) {
            System.out.print("Durasi baru: ");
            int duration = Integer.parseInt(scanner.nextLine());
            if (duration > 0) rec.setDurationInMinutes(duration);
        }

        saveItemsToFile();
        System.out.println("Barang berhasil diedit!");
    }

    public static void deleteItem() {
        printListOfItem();
        System.out.print("Masukkan ID barang yang ingin dihapus: ");
        int id = Integer.parseInt(scanner.nextLine());
        JeremyMarvel_Item_BL item = getItemById(id);
        if (item != null) {
            items.remove(item);
            saveItemsToFile();
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan!");
        }
    }


    public static JeremyMarvel_Item_BL getItemById(int id) {
        return items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public static void printListOfItem() {
        if (items.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        for (JeremyMarvel_Item_BL item : items) {
            System.out.println(item.getId() + ". " + item.printDetail());
        }
    }

    public static void buyItems() {
        try {
            printListOfItem();
            System.out.print("Masukkan ID barang yang ingin dibeli: ");
            int id = Integer.parseInt(scanner.nextLine());
            JeremyMarvel_Item_BL item = getItemById(id);
            if (item == null) {
                System.out.println("Barang tidak ditemukan!");
                return;
            }

            System.out.print("Jumlah yang ingin dibeli: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (item.getStock() < quantity) {
                System.out.println("Stok tidak mencukupi!");
                return;
            }

            double discount = item.countDiscount(quantity);
            double totalPrice = item.getPrice() * quantity * (1 - discount);
            item.minusStock(quantity);
            invoices.add(new JeremyMarvel_Invoice_BL(item, quantity, totalPrice, LocalDate.now()));

            System.out.println("Transaksi berhasil! Total bayar: Rp" + totalPrice);
            saveItemsToFile();
        } catch (Exception e) {
            System.out.println("Error saat pembelian: " + e.getMessage());
        }
    }

    public static void printInvoices() {
        double totalRevenue = 0;
        int ticketSold = 0, merchandiseSold = 0, recordingSold = 0;

        for (JeremyMarvel_Invoice_BL inv : invoices) {
            System.out.println(inv.printDetail());
            totalRevenue += inv.getTotalAmount();

            switch (inv.getItem().getCategory()) {
                case "ticket" -> ticketSold += inv.getQuantity();
                case "merchandise" -> merchandiseSold += inv.getQuantity();
                case "recording" -> recordingSold += inv.getQuantity();
            }
        }

        System.out.println("=== Laporan Penjualan ===");
        System.out.println("Total Pendapatan: Rp" + totalRevenue);
        System.out.println("Tiket Terjual: " + ticketSold);
        System.out.println("Merchandise Terjual: " + merchandiseSold);
        System.out.println("Rekaman Terjual: " + recordingSold);

        try (PrintWriter out = new PrintWriter("Report.txt")) {
            out.println("Total Pendapatan: Rp" + totalRevenue);
            out.println("Tiket Terjual: " + ticketSold);
            out.println("Merchandise Terjual: " + merchandiseSold);
            out.println("Rekaman Terjual: " + recordingSold);
        } catch (Exception e) {
            System.out.println("Gagal menyimpan laporan: " + e.getMessage());
        }
    }

    public static void saveItemsToFile() {
        try (PrintWriter writer = new PrintWriter("Items.txt")) {
            for (JeremyMarvel_Item_BL item : items) {
                String line = item.getId() + ";" + item.getName() + ";" + item.getPrice() + ";" + item.getStock() + ";" + item.getCategory();

                if (item instanceof JeremyMarvel_Ticket_BL ticket) {
                    line += ";" + ticket.getValidUntil();
                } else if (item instanceof JeremyMarvel_Merchandise_BL merch) {
                    line += ";" + merch.getType();
                } else if (item instanceof JeremyMarvel_Recording_BL rec) {
                    line += ";" + rec.getDurationInMinutes();
                }

                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan Items.txt: " + e.getMessage());
        }
    }

    public static void loadItemsFromFile() {
        File file = new File("Items.txt");
        if (!file.exists()) return;
               

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int stock = Integer.parseInt(parts[3]);
                String category = parts[4];

                JeremyMarvel_Item_BL item = switch (category) {
                    case "ticket" -> new JeremyMarvel_Ticket_BL(id, name, price, stock, category, LocalDate.parse(parts[5]));
                    case "merchandise" -> new JeremyMarvel_Merchandise_BL(id, name, price, stock, category, parts[5]);
                    case "recording" -> new JeremyMarvel_Recording_BL(id, name, price, stock, category, Integer.parseInt(parts[5]));
                    default -> null;
                };

                if (item != null) items.add(item);
                itemIdCounter = Math.max(itemIdCounter, id + 1);
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca Items.txt: " + e.getMessage());
        }
    }
    
    public static void searchItems() {
        if (items.isEmpty()) {
            System.out.println("Tidak ada barang untuk dicari.");
            return;
        }
        System.out.println("\n--- Cari Barang ---");
        System.out.println("1. Cari berdasarkan Nama");
        System.out.println("2. Cari berdasarkan Kategori");
        System.out.print("Pilih: ");
        
        try {
            int searchChoice = Integer.parseInt(scanner.nextLine());
            List<JeremyMarvel_Item_BL> results = new ArrayList<>();

            switch (searchChoice) {
                case 1 -> {
                    System.out.print("Masukkan nama barang yang dicari: ");
                    String keyword = scanner.nextLine().toLowerCase();
                    for (JeremyMarvel_Item_BL item : items) {
                        if (item.getName().toLowerCase().contains(keyword)) {
                            results.add(item);
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Masukkan kategori barang yang dicari (ticket/merchandise/recording): ");
                    String categoryKeyword = scanner.nextLine().toLowerCase();
                    for (JeremyMarvel_Item_BL item : items) {
                        if (item.getCategory().toLowerCase().equals(categoryKeyword)) {
                            results.add(item);
                        }
                    }
                }
                default -> {
                    System.out.println("Pilihan pencarian tidak valid.");
                    return;
                }
            }

            if (results.isEmpty()) {
                System.out.println("Tidak ada barang yang ditemukan dengan kriteria tersebut.");
            } else {
                System.out.println("--- Hasil Pencarian ---");
                for (JeremyMarvel_Item_BL item : results) {
                    System.out.println(item.getId() + ". " + item.printDetail());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Harap masukkan angka untuk pilihan pencarian.");
        }
    }
    
    public static void exit() {
    	System.out.println("Terima kasih sudah menggunakan kaseer!\nSampai jumpa...");
    	System.exit(0);
    }
}
    
