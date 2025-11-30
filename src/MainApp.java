import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Kelas MainApp - Aplikasi utama untuk manajemen restoran.
 * Mendemonstrasikan penggunaan:
 * - Abstraksi dan Inheritance (MenuItem, Makanan, Minuman, Diskon)
 * - Encapsulation (private attributes dengan getter/setter)
 * - Polymorphism (metode tampilMenu())
 * - Exception Handling (try-catch)
 * - File I/O (simpan/muat menu dan struk)
 * - Struktur kontrol (do-while, switch, for, while, if)
 */
public class MainApp {
    private static Menu menu;
    private static Pesanan pesananAktif;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        menu = new Menu();
        pesananAktif = new Pesanan();

        // Inisialisasi menu default
        menu.inisialisasiMenuDefault();

        int pilihan;

        // Menggunakan do-while untuk perulangan menu utama
        do {
            tampilkanMenuUtama();
            pilihan = bacaInputInteger("Pilih opsi (1-7): ");

            // Menggunakan switch untuk navigasi menu
            switch (pilihan) {
                case 1:
                    tambahItemBaru();
                    break;
                case 2:
                    tampilkanMenu();
                    break;
                case 3:
                    terimaPesanan();
                    break;
                case 4:
                    tampilkanStrukPesanan();
                    break;
                case 5:
                    simpanMenuKeFile();
                    break;
                case 6:
                    muatMenuDariFile();
                    break;
                case 7:
                    System.out.println("\n========================================");
                    System.out.println("  Terima kasih telah menggunakan aplikasi!");
                    System.out.println("      Sampai jumpa kembali!");
                    System.out.println("========================================\n");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan pilih 1-7.");
            }
        } while (pilihan != 7);

        scanner.close();
    }

    /**
     * Menampilkan menu utama aplikasi
     */
    private static void tampilkanMenuUtama() {
        System.out.println("\n========================================");
        System.out.println("      SISTEM MANAJEMEN RESTORAN");
        System.out.println("========================================");
        System.out.println("1. Tambah Item Baru (Makanan/Minuman/Diskon)");
        System.out.println("2. Tampilkan Menu");
        System.out.println("3. Terima Pesanan");
        System.out.println("4. Tampilkan Struk Pesanan");
        System.out.println("5. Simpan Menu ke File");
        System.out.println("6. Muat Menu dari File");
        System.out.println("7. Keluar");
        System.out.println("========================================");
    }

    /**
     * Membaca input integer dengan exception handling
     * @param prompt Pesan prompt untuk user
     * @return nilai integer yang diinput
     */
    private static int bacaInputInteger(String prompt) {
        int nilai = -1;
        boolean valid = false;

        // Menggunakan while loop untuk validasi input
        while (!valid) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                nilai = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka! Silakan coba lagi.");
            }
        }
        return nilai;
    }

    /**
     * Membaca input double dengan exception handling
     * @param prompt Pesan prompt untuk user
     * @return nilai double yang diinput
     */
    private static double bacaInputDouble(String prompt) {
        double nilai = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                nilai = Double.parseDouble(input);
                if (nilai < 0) {
                    System.out.println("Error: Nilai tidak boleh negatif!");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka! Silakan coba lagi.");
            }
        }
        return nilai;
    }

    /**
     * Menu 1: Tambah Item Baru (Makanan, Minuman, atau Diskon)
     */
    private static void tambahItemBaru() {
        System.out.println("\n--- TAMBAH ITEM BARU ---");
        System.out.println("Pilih jenis item:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon");

        int jenisItem = bacaInputInteger("Pilih (1-3): ");

        switch (jenisItem) {
            case 1:
                tambahMakanan();
                break;
            case 2:
                tambahMinuman();
                break;
            case 3:
                tambahDiskon();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    /**
     * Menambah item Makanan baru
     */
    private static void tambahMakanan() {
        System.out.println("\n--- Tambah Makanan Baru ---");
        System.out.print("Nama makanan: ");
        String nama = scanner.nextLine().trim();

        double harga = bacaInputDouble("Harga (Rp): ");

        System.out.print("Jenis makanan (contoh: Nasi, Mie, Lauk): ");
        String jenis = scanner.nextLine().trim();

        Makanan makananBaru = new Makanan(nama, harga, jenis);
        menu.tambahItem(makananBaru);
    }

    /**
     * Menambah item Minuman baru
     */
    private static void tambahMinuman() {
        System.out.println("\n--- Tambah Minuman Baru ---");
        System.out.print("Nama minuman: ");
        String nama = scanner.nextLine().trim();

        double harga = bacaInputDouble("Harga (Rp): ");

        System.out.print("Jenis minuman (contoh: Dingin, Panas, Jus): ");
        String jenis = scanner.nextLine().trim();

        Minuman minumanBaru = new Minuman(nama, harga, jenis);
        menu.tambahItem(minumanBaru);
    }

    /**
     * Menambah item Diskon baru
     */
    private static void tambahDiskon() {
        System.out.println("\n--- Tambah Diskon Baru ---");
        System.out.print("Nama diskon: ");
        String nama = scanner.nextLine().trim();

        double persentase = -1;
        boolean valid = false;

        while (!valid) {
            persentase = bacaInputDouble("Persentase diskon (contoh: 10 untuk 10%): ");
            if (persentase < 0 || persentase > 100) {
                System.out.println("Error: Persentase harus antara 0-100!");
            } else {
                valid = true;
            }
        }

        Diskon diskonBaru = new Diskon(nama, 0, persentase / 100);
        menu.tambahItem(diskonBaru);
    }

    /**
     * Menu 2: Tampilkan Menu (menggunakan Polymorphism)
     */
    private static void tampilkanMenu() {
        menu.tampilkanSemuaMenu();
    }

    /**
     * Menu 3: Terima Pesanan (menggunakan while loop)
     */
    private static void terimaPesanan() {
        // Reset pesanan untuk pesanan baru
        pesananAktif.resetPesanan();

        System.out.println("\n--- TERIMA PESANAN ---");
        menu.tampilkanMenuDenganNomor();

        int jumlahItemPesan = menu.getJumlahItemPesan();
        if (jumlahItemPesan == 0) {
            System.out.println("Menu kosong. Silakan tambah menu terlebih dahulu.");
            return;
        }

        System.out.println("Masukkan pesanan (ketik 0 untuk selesai):");

        // Menggunakan while loop untuk input pesanan
        while (true) {
            int nomorMenu = bacaInputInteger("\nNomor menu (0 untuk selesai): ");

            if (nomorMenu == 0) {
                break;
            }

            // Exception Handling untuk IndexOutOfBoundsException
            try {
                if (nomorMenu < 1 || nomorMenu > jumlahItemPesan) {
                    throw new IndexOutOfBoundsException("Nomor menu tidak valid!");
                }

                MenuItem itemTerpilih = menu.getItemByNomor(nomorMenu);
                if (itemTerpilih != null) {
                    int kuantitas = bacaInputInteger("Kuantitas: ");
                    if (kuantitas > 0) {
                        pesananAktif.tambahItem(itemTerpilih, kuantitas);
                    } else {
                        System.out.println("Kuantitas harus lebih dari 0!");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Terapkan diskon jika ada pesanan
        if (!pesananAktif.isEmpty()) {
            terapkanDiskon();
        }

        // Tampilkan struk setelah pesanan selesai
        pesananAktif.tampilkanStruk();

        // Tanyakan apakah ingin simpan struk
        if (!pesananAktif.isEmpty()) {
            System.out.print("Simpan struk ke file? (y/n): ");
            String jawaban = scanner.nextLine().trim().toLowerCase();
            if (jawaban.equals("y") || jawaban.equals("ya")) {
                pesananAktif.simpanStrukKeFile();
            }
        }
    }

    /**
     * Terapkan diskon ke pesanan
     */
    private static void terapkanDiskon() {
        ArrayList<Diskon> daftarDiskon = menu.getAllDiskon();

        if (daftarDiskon.isEmpty()) {
            System.out.println("Tidak ada diskon tersedia.");
            return;
        }

        System.out.println("\n--- DISKON TERSEDIA ---");
        System.out.println("0. Tanpa diskon");

        // Menggunakan for loop untuk menampilkan diskon
        for (int i = 0; i < daftarDiskon.size(); i++) {
            Diskon d = daftarDiskon.get(i);
            System.out.printf("%d. %s (%.0f%%)%n", i + 1, d.getNama(), d.getDiskon() * 100);
        }

        int pilihanDiskon = bacaInputInteger("Pilih diskon (0-" + daftarDiskon.size() + "): ");

        // Menggunakan if untuk validasi pilihan
        if (pilihanDiskon > 0 && pilihanDiskon <= daftarDiskon.size()) {
            try {
                Diskon diskonTerpilih = daftarDiskon.get(pilihanDiskon - 1);
                pesananAktif.terapkanDiskon(diskonTerpilih);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Pilihan diskon tidak valid!");
            }
        } else if (pilihanDiskon == 0) {
            System.out.println("Tidak menggunakan diskon.");
        } else {
            System.out.println("Pilihan tidak valid. Tidak menggunakan diskon.");
        }
    }

    /**
     * Menu 4: Tampilkan Struk Pesanan
     */
    private static void tampilkanStrukPesanan() {
        System.out.println("\n--- TAMPILKAN STRUK ---");
        System.out.println("1. Tampilkan struk pesanan aktif");
        System.out.println("2. Baca struk dari file");

        int pilihan = bacaInputInteger("Pilih (1-2): ");

        switch (pilihan) {
            case 1:
                pesananAktif.tampilkanStruk();
                break;
            case 2:
                System.out.print("Masukkan nama file struk (contoh: struk_ORD-xxx.txt): ");
                String fileName = scanner.nextLine().trim();
                Pesanan.muatStrukDariFile(fileName);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    /**
     * Menu 5: Simpan Menu ke File
     */
    private static void simpanMenuKeFile() {
        System.out.println("\n--- SIMPAN MENU KE FILE ---");
        menu.simpanKeFile();
    }

    /**
     * Menu 6: Muat Menu dari File
     */
    private static void muatMenuDariFile() {
        System.out.println("\n--- MUAT MENU DARI FILE ---");
        menu.muatDariFile();
    }
}
