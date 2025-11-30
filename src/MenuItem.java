/**
 * Kelas abstrak MenuItem sebagai basis untuk semua item menu restoran.
 * Mendemonstrasikan konsep Abstraksi dan Encapsulation.
 */
public abstract class MenuItem {
    // Encapsulation: atribut private
    private String nama;
    private double harga;
    private String kategori;

    /**
     * Konstruktor untuk MenuItem
     * @param nama Nama item menu
     * @param harga Harga item menu
     * @param kategori Kategori item menu
     */
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Getter dan Setter untuk encapsulation
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    /**
     * Metode abstrak untuk menampilkan menu.
     * Akan diimplementasikan oleh subclass (Polymorphism).
     */
    public abstract void tampilMenu();

    /**
     * Mengkonversi MenuItem ke format string untuk disimpan ke file.
     * @return String representasi MenuItem untuk file
     */
    public abstract String toFileString();

    @Override
    public String toString() {
        return nama + " - Rp " + String.format("%,.0f", harga) + " (" + kategori + ")";
    }
}
