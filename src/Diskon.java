/**
 * Kelas Diskon merupakan turunan dari MenuItem.
 * Mendemonstrasikan konsep Inheritance dan Polymorphism.
 * Digunakan untuk item diskon yang bisa diterapkan pada pesanan.
 */
public class Diskon extends MenuItem {
    // Encapsulation: atribut private
    private double diskon; // persentase diskon (0.0 - 1.0)

    /**
     * Konstruktor untuk Diskon
     * @param nama Nama diskon (contoh: "Diskon Hari Spesial")
     * @param harga Harga dasar (biasanya 0 untuk diskon)
     * @param diskon Persentase diskon (contoh: 0.10 untuk 10%)
     */
    public Diskon(String nama, double harga, double diskon) {
        super(nama, harga, "Diskon");
        this.diskon = diskon;
    }

    // Getter dan Setter untuk encapsulation
    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    /**
     * Implementasi metode abstrak tampilMenu() - Polymorphism
     */
    @Override
    public void tampilMenu() {
        System.out.printf("  [DISKON]  %-20s | Potongan: %.0f%%%n", 
            getNama(), diskon * 100);
    }

    /**
     * Mengkonversi Diskon ke format string untuk disimpan ke file.
     * Format: DISKON|nama|harga|diskon
     */
    @Override
    public String toFileString() {
        return "DISKON|" + getNama() + "|" + getHarga() + "|" + diskon;
    }
}
