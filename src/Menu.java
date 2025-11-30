import java.io.*;
import java.util.ArrayList;

/**
 * Kelas Menu untuk mengelola daftar item menu (ArrayList<MenuItem>).
 * Implementasi File I/O untuk menyimpan dan memuat data menu.
 */
public class Menu {
    // Encapsulation: atribut private
    private ArrayList<MenuItem> daftarMenu;
    private static final String FILE_MENU = "menu_data.txt";

    /**
     * Konstruktor untuk Menu
     */
    public Menu() {
        this.daftarMenu = new ArrayList<>();
    }

    // Getter untuk daftar menu
    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    /**
     * Menambahkan item menu baru ke daftar
     * @param item MenuItem yang akan ditambahkan
     */
    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        System.out.println("Item '" + item.getNama() + "' berhasil ditambahkan!");
    }

    /**
     * Menampilkan semua menu menggunakan polymorphism
     */
    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Daftar menu kosong.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("           DAFTAR MENU RESTORAN");
        System.out.println("========================================");

        // Tampilkan Makanan
        System.out.println("\n--- MAKANAN ---");
        int nomorMakanan = 1;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Makanan) {
                System.out.print(nomorMakanan + ". ");
                item.tampilMenu(); // Polymorphism
                nomorMakanan++;
            }
        }

        // Tampilkan Minuman
        System.out.println("\n--- MINUMAN ---");
        int nomorMinuman = 1;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Minuman) {
                System.out.print(nomorMinuman + ". ");
                item.tampilMenu(); // Polymorphism
                nomorMinuman++;
            }
        }

        // Tampilkan Diskon
        System.out.println("\n--- DISKON ---");
        int nomorDiskon = 1;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Diskon) {
                System.out.print(nomorDiskon + ". ");
                item.tampilMenu(); // Polymorphism
                nomorDiskon++;
            }
        }

        System.out.println("\n========================================\n");
    }

    /**
     * Mendapatkan item menu berdasarkan index
     * @param index Index item dalam daftar
     * @return MenuItem pada index tersebut
     * @throws IndexOutOfBoundsException jika index tidak valid
     */
    public MenuItem getItem(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " tidak valid. Jumlah menu: " + daftarMenu.size());
        }
        return daftarMenu.get(index);
    }

    /**
     * Mencari item menu berdasarkan nama
     * @param nama Nama item yang dicari
     * @return MenuItem jika ditemukan, null jika tidak
     */
    public MenuItem cariItemByNama(String nama) {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Mendapatkan jumlah item dalam menu
     * @return Jumlah item menu
     */
    public int getJumlahItem() {
        return daftarMenu.size();
    }

    /**
     * Menyimpan daftar menu ke file teks
     * Implementasi File I/O
     */
    public void simpanKeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_MENU))) {
            for (MenuItem item : daftarMenu) {
                writer.println(item.toFileString());
            }
            System.out.println("Menu berhasil disimpan ke file '" + FILE_MENU + "'!");
        } catch (IOException e) {
            System.out.println("Error saat menyimpan menu: " + e.getMessage());
        }
    }

    /**
     * Memuat daftar menu dari file teks
     * Implementasi File I/O dengan Exception Handling
     */
    public void muatDariFile() {
        File file = new File(FILE_MENU);
        if (!file.exists()) {
            System.out.println("File menu tidak ditemukan. Membuat menu default...");
            inisialisasiMenuDefault();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MENU))) {
            daftarMenu.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String tipe = parts[0];
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    String detail = parts[3];

                    switch (tipe) {
                        case "MAKANAN":
                            daftarMenu.add(new Makanan(nama, harga, detail));
                            break;
                        case "MINUMAN":
                            daftarMenu.add(new Minuman(nama, harga, detail));
                            break;
                        case "DISKON":
                            double diskonValue = Double.parseDouble(detail);
                            daftarMenu.add(new Diskon(nama, harga, diskonValue));
                            break;
                    }
                }
            }
            System.out.println("Menu berhasil dimuat dari file '" + FILE_MENU + "'!");
            System.out.println("Total item: " + daftarMenu.size());
        } catch (IOException e) {
            System.out.println("Error saat memuat menu: " + e.getMessage());
            inisialisasiMenuDefault();
        } catch (NumberFormatException e) {
            System.out.println("Error format data: " + e.getMessage());
            inisialisasiMenuDefault();
        }
    }

    /**
     * Inisialisasi menu default jika file tidak ada
     */
    public void inisialisasiMenuDefault() {
        daftarMenu.clear();
        // Makanan
        daftarMenu.add(new Makanan("Nasi Goreng", 25000, "Nasi"));
        daftarMenu.add(new Makanan("Mie Goreng", 22000, "Mie"));
        daftarMenu.add(new Makanan("Ayam Bakar", 35000, "Lauk"));
        daftarMenu.add(new Makanan("Sate Ayam", 30000, "Lauk"));

        // Minuman
        daftarMenu.add(new Minuman("Es Teh Manis", 8000, "Dingin"));
        daftarMenu.add(new Minuman("Es Jeruk", 10000, "Dingin"));
        daftarMenu.add(new Minuman("Kopi Hitam", 12000, "Panas"));
        daftarMenu.add(new Minuman("Jus Alpukat", 15000, "Jus"));

        // Diskon
        daftarMenu.add(new Diskon("Diskon Member", 0, 0.10));
        daftarMenu.add(new Diskon("Diskon Weekend", 0, 0.15));

        System.out.println("Menu default telah diinisialisasi.");
    }

    /**
     * Mendapatkan semua item diskon yang tersedia
     * @return ArrayList<Diskon> daftar diskon
     */
    public ArrayList<Diskon> getAllDiskon() {
        ArrayList<Diskon> diskonList = new ArrayList<>();
        for (MenuItem item : daftarMenu) {
            if (item instanceof Diskon) {
                diskonList.add((Diskon) item);
            }
        }
        return diskonList;
    }

    /**
     * Menampilkan menu dengan nomor urut untuk pemesanan
     */
    public void tampilkanMenuDenganNomor() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Daftar menu kosong.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("         PILIH MENU UNTUK PESANAN");
        System.out.println("========================================");

        int nomor = 1;
        for (MenuItem item : daftarMenu) {
            if (!(item instanceof Diskon)) {
                System.out.printf("%d. %-20s | Rp %,10.0f | %s%n", 
                    nomor, item.getNama(), item.getHarga(), item.getKategori());
                nomor++;
            }
        }
        System.out.println("========================================\n");
    }

    /**
     * Mendapatkan item berdasarkan nomor urut (hanya Makanan dan Minuman)
     * @param nomor Nomor urut (1-based)
     * @return MenuItem atau null jika tidak ditemukan
     */
    public MenuItem getItemByNomor(int nomor) {
        int current = 1;
        for (MenuItem item : daftarMenu) {
            if (!(item instanceof Diskon)) {
                if (current == nomor) {
                    return item;
                }
                current++;
            }
        }
        return null;
    }

    /**
     * Mendapatkan jumlah item yang bisa dipesan (bukan diskon)
     * @return Jumlah item yang bisa dipesan
     */
    public int getJumlahItemPesan() {
        int count = 0;
        for (MenuItem item : daftarMenu) {
            if (!(item instanceof Diskon)) {
                count++;
            }
        }
        return count;
    }
}
