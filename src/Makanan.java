/**
 * Kelas Makanan merupakan turunan dari MenuItem.
 * Mendemonstrasikan konsep Inheritance dan Polymorphism.
 */
public class Makanan extends MenuItem {
    // Encapsulation: atribut private
    private String jenisMakanan;

    /**
     * Konstruktor untuk Makanan
     * @param nama Nama makanan
     * @param harga Harga makanan
     * @param jenisMakanan Jenis makanan (contoh: Nasi, Mie, Lauk)
     */
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    // Getter dan Setter untuk encapsulation
    public String getJenisMakanan() {
        return jenisMakanan;
    }

    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }

    /**
     * Implementasi metode abstrak tampilMenu() - Polymorphism
     */
    @Override
    public void tampilMenu() {
        System.out.printf("  [MAKANAN] %-20s | Rp %,10.0f | Jenis: %s%n", 
            getNama(), getHarga(), jenisMakanan);
    }

    /**
     * Mengkonversi Makanan ke format string untuk disimpan ke file.
     * Format: MAKANAN|nama|harga|jenisMakanan
     */
    @Override
    public String toFileString() {
        return "MAKANAN|" + getNama() + "|" + getHarga() + "|" + jenisMakanan;
    }
}
