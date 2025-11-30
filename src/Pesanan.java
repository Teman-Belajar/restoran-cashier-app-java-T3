import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Kelas Pesanan untuk mencatat item pesanan dan kuantitasnya.
 * Implementasi File I/O untuk menyimpan dan memuat struk pesanan.
 */
public class Pesanan {
    // Encapsulation: atribut private
    private ArrayList<MenuItem> itemPesanan;
    private ArrayList<Integer> kuantitas;
    private Diskon diskonDiterapkan;
    private String nomorPesanan;
    private Date tanggalPesanan;

    /**
     * Konstruktor untuk Pesanan
     */
    public Pesanan() {
        this.itemPesanan = new ArrayList<>();
        this.kuantitas = new ArrayList<>();
        this.diskonDiterapkan = null;
        this.nomorPesanan = generateNomorPesanan();
        this.tanggalPesanan = new Date();
    }

    // Getter methods
    public ArrayList<MenuItem> getItemPesanan() {
        return itemPesanan;
    }

    public ArrayList<Integer> getKuantitas() {
        return kuantitas;
    }

    public Diskon getDiskonDiterapkan() {
        return diskonDiterapkan;
    }

    public String getNomorPesanan() {
        return nomorPesanan;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    /**
     * Generate nomor pesanan unik
     * @return String nomor pesanan
     */
    private String generateNomorPesanan() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return "ORD-" + sdf.format(new Date());
    }

    /**
     * Menambahkan item ke pesanan
     * @param item MenuItem yang akan ditambahkan
     * @param qty Kuantitas item
     */
    public void tambahItem(MenuItem item, int qty) {
        // Cek apakah item sudah ada di pesanan
        for (int i = 0; i < itemPesanan.size(); i++) {
            if (itemPesanan.get(i).getNama().equals(item.getNama())) {
                // Update kuantitas jika item sudah ada
                kuantitas.set(i, kuantitas.get(i) + qty);
                System.out.println(item.getNama() + " x" + qty + " ditambahkan. Total: " + kuantitas.get(i));
                return;
            }
        }
        // Tambah item baru
        itemPesanan.add(item);
        kuantitas.add(qty);
        System.out.println(item.getNama() + " x" + qty + " ditambahkan ke pesanan.");
    }

    /**
     * Menerapkan diskon ke pesanan
     * @param diskon Diskon yang akan diterapkan
     */
    public void terapkanDiskon(Diskon diskon) {
        this.diskonDiterapkan = diskon;
        System.out.println("Diskon '" + diskon.getNama() + "' (" + (diskon.getDiskon() * 100) + "%) diterapkan.");
    }

    /**
     * Menghitung subtotal pesanan (sebelum diskon)
     * @return double subtotal
     */
    public double hitungSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < itemPesanan.size(); i++) {
            subtotal += itemPesanan.get(i).getHarga() * kuantitas.get(i);
        }
        return subtotal;
    }

    /**
     * Menghitung nilai diskon
     * @return double nilai diskon
     */
    public double hitungDiskon() {
        if (diskonDiterapkan == null) {
            return 0;
        }
        return hitungSubtotal() * diskonDiterapkan.getDiskon();
    }

    /**
     * Menghitung total pesanan (setelah diskon)
     * @return double total akhir
     */
    public double hitungTotal() {
        return hitungSubtotal() - hitungDiskon();
    }

    /**
     * Cek apakah pesanan kosong
     * @return true jika pesanan kosong
     */
    public boolean isEmpty() {
        return itemPesanan.isEmpty();
    }

    /**
     * Menampilkan struk pesanan
     */
    public void tampilkanStruk() {
        if (isEmpty()) {
            System.out.println("Pesanan kosong. Tidak ada struk untuk ditampilkan.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n================================================");
        System.out.println("            STRUK PESANAN RESTORAN");
        System.out.println("================================================");
        System.out.println("No. Pesanan : " + nomorPesanan);
        System.out.println("Tanggal     : " + sdf.format(tanggalPesanan));
        System.out.println("------------------------------------------------");
        System.out.println("RINCIAN PESANAN:");
        System.out.println("------------------------------------------------");

        // Tampilkan detail item pesanan
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int qty = kuantitas.get(i);
            double totalItem = item.getHarga() * qty;
            System.out.printf("%-20s %d x Rp %,10.0f = Rp %,10.0f%n",
                item.getNama(), qty, item.getHarga(), totalItem);
        }

        System.out.println("------------------------------------------------");
        double subtotal = hitungSubtotal();
        System.out.printf("Subtotal:                          Rp %,10.0f%n", subtotal);

        if (diskonDiterapkan != null) {
            double nilaiDiskon = hitungDiskon();
            System.out.printf("Diskon (%s - %.0f%%):            -Rp %,10.0f%n",
                diskonDiterapkan.getNama(), diskonDiterapkan.getDiskon() * 100, nilaiDiskon);
        }

        System.out.println("------------------------------------------------");
        System.out.printf("TOTAL:                             Rp %,10.0f%n", hitungTotal());
        System.out.println("================================================");
        System.out.println("      Terima kasih atas kunjungan Anda!");
        System.out.println("================================================\n");
    }

    /**
     * Menyimpan struk pesanan ke file teks
     * Implementasi File I/O
     */
    public void simpanStrukKeFile() {
        if (isEmpty()) {
            System.out.println("Pesanan kosong. Tidak ada struk untuk disimpan.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fileName = "struk_" + nomorPesanan + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("================================================");
            writer.println("            STRUK PESANAN RESTORAN");
            writer.println("================================================");
            writer.println("No. Pesanan : " + nomorPesanan);
            writer.println("Tanggal     : " + sdf.format(tanggalPesanan));
            writer.println("------------------------------------------------");
            writer.println("RINCIAN PESANAN:");
            writer.println("------------------------------------------------");

            for (int i = 0; i < itemPesanan.size(); i++) {
                MenuItem item = itemPesanan.get(i);
                int qty = kuantitas.get(i);
                double totalItem = item.getHarga() * qty;
                writer.printf("%-20s %d x Rp %,10.0f = Rp %,10.0f%n",
                    item.getNama(), qty, item.getHarga(), totalItem);
            }

            writer.println("------------------------------------------------");
            double subtotal = hitungSubtotal();
            writer.printf("Subtotal:                          Rp %,10.0f%n", subtotal);

            if (diskonDiterapkan != null) {
                double nilaiDiskon = hitungDiskon();
                writer.printf("Diskon (%s - %.0f%%):            -Rp %,10.0f%n",
                    diskonDiterapkan.getNama(), diskonDiterapkan.getDiskon() * 100, nilaiDiskon);
            }

            writer.println("------------------------------------------------");
            writer.printf("TOTAL:                             Rp %,10.0f%n", hitungTotal());
            writer.println("================================================");
            writer.println("      Terima kasih atas kunjungan Anda!");
            writer.println("================================================");

            System.out.println("Struk berhasil disimpan ke file '" + fileName + "'!");
        } catch (IOException e) {
            System.out.println("Error saat menyimpan struk: " + e.getMessage());
        }
    }

    /**
     * Memuat struk pesanan dari file teks
     * @param fileName Nama file struk
     */
    public static void muatStrukDariFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File struk '" + fileName + "' tidak ditemukan.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("\n--- Isi File Struk: " + fileName + " ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--- Akhir File ---\n");
        } catch (IOException e) {
            System.out.println("Error saat membaca struk: " + e.getMessage());
        }
    }

    /**
     * Reset pesanan untuk pesanan baru
     */
    public void resetPesanan() {
        itemPesanan.clear();
        kuantitas.clear();
        diskonDiterapkan = null;
        nomorPesanan = generateNomorPesanan();
        tanggalPesanan = new Date();
    }
}
