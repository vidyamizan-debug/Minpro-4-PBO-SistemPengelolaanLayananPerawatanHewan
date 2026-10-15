package model;

public class Perawatan extends Layanan implements Evalutable {

    public Perawatan(int idLayanan, String namaLayanan, String catatan, int harga, String namaHewan, String namaPemilik, String jenisHewan, int umurHewan) {
        super(idLayanan, namaLayanan, catatan, harga, namaHewan, namaPemilik, jenisHewan, umurHewan);
    }

    @Override
    public String getKategori() {
        return "Perawatan";
    }

    @Override
    public double hitungDiskon() {
        if (getNamaLayanan().equalsIgnoreCase("Perawatan Bulu")) {
            return harga * 0.1;
        }
        return 0;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Diskon        : Rp" + hitungDiskon());
    }
}