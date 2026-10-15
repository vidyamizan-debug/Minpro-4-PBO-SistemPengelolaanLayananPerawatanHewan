package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Layanan;
import model.Perawatan;
import model.Penitipan;
import model.Evalutable;
import model.dao.LayananDAO;
import View.Menu;

public class CRUDKyaPetCare {
    private LayananDAO dao;
    private Scanner scanner;
    private CekKyaPetCare cek;
    private Menu menu;

    public CRUDKyaPetCare() {
        this.scanner = new Scanner(System.in);
        this.cek = new CekKyaPetCare();
        this.menu = new Menu();
        this.dao = new LayananDAO();
    }

    public void jalankan() {
        boolean berjalan = true;

        while (berjalan) {
            menu.tampilkanMenuUtama();

            int pilihan = cek.inputPilihan(scanner, "Pilih menu (1-5): ", 1, 5);

            switch (pilihan) {
                case 1:
                    tambahLayanan();
                    break;

                case 2:
                    tampilkanLayanan();
                    break;

                case 3:
                    updateLayanan();
                    break;

                case 4:
                    hapusLayanan();
                    break;

                case 5:
                    berjalan = false;
                    System.out.println("\nSampai jumpa lagi nanti!");
                    break;

                default:
                    System.out.println("Pilihan menu tidak tersedia!");
                    break;
            }
        }

        scanner.close();
    }

    private String pilihNamaPerawatan() {
        System.out.println("\n+-------------------------+");
        System.out.println("|  Pilih Jenis Perawatan  |");
        System.out.println("+-------------------------+");
        System.out.println("| 1. Mandi                |");
        System.out.println("| 2. Potong Kuku          |");
        System.out.println("| 2. Bulu                 |");
        System.out.println("+-------------------------+");
        int pilihanJenis = cek.inputPilihan(scanner, "Pilihan (1-3): ", 1, 3);

        if (pilihanJenis == 1) {
            return "Perawatan Mandi";
        } else if (pilihanJenis == 2) {
            return "Perawatan Potong Kuku";
        } else {
            return "Perawatan Bulu";
        }
    }

    public void tambahLayanan() {
        System.out.println("\n+---------------------+");
        System.out.println("| Tambah Data Layanan |");
        System.out.println("+---------------------+");
        System.out.println("| 1. Perawatan        |");
        System.out.println("| 2. Penitipan        |");
        System.out.println("+---------------------+");
        int jenis = cek.inputPilihan(scanner, "Pilih jenis layanan (1-2): ", 1, 2);

        String catatan = cek.inputString(scanner, "Catatan untuk Petugas: ");
        int harga = cek.inputAngkaPositif(scanner, "Harga: ", "Harga");
        String namaHewan = cek.inputString(scanner, "Nama Hewan: ");
        String namaPemilik = cek.inputString(scanner, "Nama Pemilik: ");
        String jenisHewan = cek.inputString(scanner, "Jenis Hewan (contoh: Kucing Persia): ");
        int umurHewan = cek.inputAngkaPositif(scanner, "Umur Hewan: ", "Umur Hewan");

        try {
            int idBaru;

            if (jenis == 1) {
                String namaLayanan = pilihNamaPerawatan();

                Perawatan layananBaru = new Perawatan(0, namaLayanan, catatan, harga, namaHewan, namaPemilik, jenisHewan, umurHewan);

                idBaru = dao.tambah(layananBaru);
            } else {
                int lamaPenitipan = cek.inputAngkaPositif(scanner, "Lama Penitipan (hari): ", "Lama Penitipan");

                Penitipan layananBaru = new Penitipan(0, catatan, harga, namaHewan, namaPemilik, jenisHewan, umurHewan, lamaPenitipan);

                idBaru = dao.tambah(layananBaru);
            }

            System.out.println("Horee! data sudah berhasil ditambahkan dengan ID " + idBaru + ".");
            tampilkanLayanan(idBaru);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data ke database: " + e.getMessage());
        }
    }

    public void tampilkanLayanan() {
        System.out.println("\n+--------------------------------------+");
        System.out.println("|             Data Layanan             |");
        System.out.println("+--------------------------------------+");

        try {
            ArrayList<Layanan> daftarLayanan = dao.tampilkanSemua();

            if (daftarLayanan.isEmpty()) {
                System.out.println("Belum ada data layanan.");
                return;
            }

            for (Layanan layanan : daftarLayanan) {
                cetakSatuLayanan(layanan);
            }

            System.out.println("+--------------------------------------+");
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data dari database: " + e.getMessage());
        }
    }

    public void tampilkanLayanan(int id) {
        try {
            Layanan layanan = dao.cariById(id);

            if (layanan == null) {
                System.out.println("Maaff, data tidak berhasil untuk ditemukan!");
                return;
            }

        System.out.println("\n+--------------------------------------+");
        System.out.println("|             Data Layanan             |");
        System.out.println("+--------------------------------------+");
        cetakSatuLayanan(layanan);
        System.out.println("+--------------------------------------+");
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data dari database: " + e.getMessage());
        }
    }

    private void cetakSatuLayanan(Layanan layanan) {
        System.out.println("----------------------------------------");
        layanan.tampilkanInfo();

        if (layanan instanceof Evalutable) {
            Evalutable diskon = (Evalutable) layanan;

            if (diskon.hitungDiskon() > 0) {
                layanan.cetakStatus("Dapat Diskon");
            } else {
                layanan.cetakStatus();
            }
        } else {
            layanan.cetakStatus();
        }
    }

    public void updateLayanan() {
        System.out.println("\n+--------------------------------------+");
        System.out.println("|          Update Data Layanan         |");
        System.out.println("+--------------------------------------+");

        Integer id = cek.inputIdBatal(scanner, "Masukkan ID Layanan (kosongkan untuk batal): ");

        if (id == null) {
            System.out.println("Dibatalkan, kembali ke menu.");
            return;
        }

        try {
            Layanan layanan = dao.cariById(id);

            if (layanan == null) {
                System.out.println("Maaff, data tidak berhasil untuk ditemukan!");
                return;
            }

            String catatan = cek.inputString(scanner, "Catatan Baru untuk Petugas: ");
            int harga = cek.inputAngkaPositif(scanner, "Harga Baru: ", "Harga");
            String namaHewan = cek.inputString(scanner, "Nama Hewan Baru: ");
            String namaPemilik = cek.inputString(scanner, "Nama Pemilik Baru: ");
            String jenisHewan = cek.inputString(scanner, "Jenis Hewan Baru (contoh: Kucing Persia): ");
            int umurHewan = cek.inputAngkaPositif(scanner, "Umur Hewan Baru: ", "Umur Hewan");

            layanan.setCatatan(catatan);
            layanan.setHarga(harga);
            layanan.setNamaHewan(namaHewan);
            layanan.setNamaPemilik(namaPemilik);
            layanan.setJenisHewan(jenisHewan);
            layanan.setUmurHewan(umurHewan);

            if (layanan instanceof Perawatan) {
                String namaLayananBaru = pilihNamaPerawatan();
                layanan.setNamaLayanan(namaLayananBaru);
            } else if (layanan instanceof Penitipan) {
                Penitipan penitipan = (Penitipan) layanan;
                int lamaPenitipan = cek.inputAngkaPositif(scanner, "Lama Penitipan Baru (hari): ", "Lama Penitipan");
                penitipan.setLamaPenitipan(lamaPenitipan);
            }

            boolean berhasil = dao.update(layanan);

            if (berhasil) {
                System.out.println("Horee! data sudah berhasil diupdate.");
                tampilkanLayanan(id);
            } else {
                System.out.println("Maaff, data gagal diupdate. Data mungkin sudah tidak ada di databasee.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data ke database: " + e.getMessage());
        }
    }

    public void hapusLayanan() {
        System.out.println("\n+--------------------------------------+");
        System.out.println("|          Hapus Data Layanan          |");
        System.out.println("+--------------------------------------+");

        Integer id = cek.inputIdBatal(scanner, "Masukkan ID Layanan (kosongkan untuk batal): ");

        if (id == null) {
            System.out.println("Dibatalkan, kembali ke menu.");
            return;
        }

        try {
            Layanan layanan = dao.cariById(id);

            if (layanan == null) {
                System.out.println("Maaff, data tidak berhasil untuk ditemukan!");
                return;
            }

            cetakSatuLayanan(layanan);
            boolean yakin = cek.inputYaTidak(scanner, "Yakin ingin menghapus data ini? (ya/tidak): ");

            if (yakin) {
                boolean berhasil = dao.hapus(id);

                if (berhasil) {
                    System.out.println("Data layanan berhasil dihapus!");
                } else {
                    System.out.println("Maaff, data gagal dihapus.");
                }
            } else {
                System.out.println("Penghapusan data dibatalkan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data dari database: " + e.getMessage());
        }
    }
}