/**
 * Kelas Minuman merupakan turunan dari MenuItem.
 * Mendemonstrasikan konsep Inheritance dan Polymorphism.
 */
public class Minuman extends MenuItem {
    // Encapsulation: atribut private
    private String jenisMinuman;

    /**
     * Konstruktor untuk Minuman
     * @param nama Nama minuman
     * @param harga Harga minuman
     * @param jenisMinuman Jenis minuman (contoh: Dingin, Panas, Jus)
     */
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    // Getter dan Setter untuk encapsulation
    public String getJenisMinuman() {
        return jenisMinuman;
    }

    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }

    /**
     * Implementasi metode abstrak tampilMenu() - Polymorphism
     */
    @Override
    public void tampilMenu() {
        System.out.printf("  [MINUMAN] %-20s | Rp %,10.0f | Jenis: %s%n", 
            getNama(), getHarga(), jenisMinuman);
    }

    /**
     * Mengkonversi Minuman ke format string untuk disimpan ke file.
     * Format: MINUMAN|nama|harga|jenisMinuman
     */
    @Override
    public String toFileString() {
        return "MINUMAN|" + getNama() + "|" + getHarga() + "|" + jenisMinuman;
    }
}
