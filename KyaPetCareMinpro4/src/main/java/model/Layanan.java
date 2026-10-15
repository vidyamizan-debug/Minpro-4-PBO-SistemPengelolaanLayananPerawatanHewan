package model;

public abstract class Layanan {
    private final int idLayanan;
    protected String namaLayanan;
    protected String catatan;
    protected int harga;
    protected String namaHewan;
    protected String namaPemilik;
    protected String jenisHewan;
    protected int umurHewan;

    protected Layanan(int idLayanan, String namaLayanan, String catatan, int harga, String namaHewan, String namaPemilik, String jenisHewan, int umurHewan) {
        this.idLayanan = idLayanan;

        setNamaLayanan(namaLayanan);
        setCatatan(catatan);
        setHarga(harga);
        setNamaHewan(namaHewan);
        setNamaPemilik(namaPemilik);
        setJenisHewan(jenisHewan);
        setUmurHewan(umurHewan);
    }

    public int getIdLayanan() {
        return idLayanan;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public String getCatatan() {
        return catatan;
    }

    public int getHarga() {
        return harga;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public int getUmurHewan() {
        return umurHewan;
    }

    public void setNamaLayanan(String namaLayanan) {
        if (namaLayanan == null || namaLayanan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama layanan tidak boleh kosong!");
        }
        this.namaLayanan = namaLayanan;
    }

    public void setCatatan(String catatan) {
        if (catatan == null || catatan.trim().isEmpty()) {
            throw new IllegalArgumentException("Catatan tidak boleh kosong!");
        }
        this.catatan = catatan;
    }

    public void setHarga(int harga) {
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0!");
        }
        this.harga = harga;
    }

    public void setNamaHewan(String namaHewan) {
        if (namaHewan == null || namaHewan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama hewan tidak boleh kosong!");
        }
        this.namaHewan = namaHewan;
    }

    public void setNamaPemilik(String namaPemilik) {
        if (namaPemilik == null || namaPemilik.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pemilik tidak boleh kosong!");
        }
        this.namaPemilik = namaPemilik;
    }

    public void setJenisHewan(String jenisHewan) {
        if (jenisHewan == null || jenisHewan.trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis hewan tidak boleh kosong!");
        }
        this.jenisHewan = jenisHewan;
    }

    public void setUmurHewan(int umurHewan) {
        if (umurHewan <= 0) {
            throw new IllegalArgumentException("Umur hewan harus lebih dari 0!");
        }
        this.umurHewan = umurHewan;
    }

    public abstract String getKategori();

    public void tampilkanInfo() {
        System.out.println("ID Layanan    : " + idLayanan);
        System.out.println("Kategori      : " + getKategori());
        System.out.println("Nama Layanan  : " + namaLayanan);
        System.out.println("Catatan       : " + catatan);
        System.out.println("Harga         : Rp" + harga);
        System.out.println("Nama Hewan    : " + namaHewan);
        System.out.println("Nama Pemilik  : " + namaPemilik);
        System.out.println("Jenis Hewan   : " + jenisHewan);
        System.out.println("Umur Hewan    : " + umurHewan + " tahun");
    }

    public final void cetakStatus() {
        System.out.println("Status        : Layanan Aktif");
    }

    public final void cetakStatus(String catatanStatus) {
        System.out.println("Status        : Layanan Aktif (" + catatanStatus + ")");
    }
}