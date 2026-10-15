package model.dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import model.Layanan;
import model.Penitipan;
import model.Perawatan;

public class LayananDAO {

    public ArrayList<Layanan> tampilkanSemua() throws SQLException {
        ArrayList<Layanan> daftar = new ArrayList<>();
        String sql = "SELECT * FROM layanan ORDER BY id_layanan";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(buatObjek(rs));
            }
        }

        return daftar;
    }

    public Layanan cariById(int id) throws SQLException {
        String sql = "SELECT * FROM layanan WHERE id_layanan = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return buatObjek(rs);
                }
            }
        }

        return null;
    }

    public int tambah(Layanan layanan) throws SQLException {
        String sql = "INSERT INTO layanan "
                + "(kategori, nama_layanan, catatan, harga, nama_hewan, nama_pemilik, jenis_hewan, umur_hewan, lama_penitipan) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, layanan.getKategori());
            ps.setString(2, layanan.getNamaLayanan());
            ps.setString(3, layanan.getCatatan());
            ps.setInt(4, layanan.getHarga());
            ps.setString(5, layanan.getNamaHewan());
            ps.setString(6, layanan.getNamaPemilik());
            ps.setString(7, layanan.getJenisHewan());
            ps.setInt(8, layanan.getUmurHewan());

            if (layanan instanceof Penitipan) {
                ps.setInt(9, ((Penitipan) layanan).getLamaPenitipan());
            } else {
                ps.setNull(9, Types.INTEGER);
            }

            int baris = ps.executeUpdate();

            if (baris == 0) {
                throw new SQLException("Gagal menambahkan data, tidak ada baris yang terpengaruh.");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        throw new SQLException("Gagal mendapatkan ID baru dari database.");
    }

    public boolean update(Layanan layanan) throws SQLException {
        String sql = "UPDATE layanan SET nama_layanan = ?, catatan = ?, harga = ?, nama_hewan = ?, " + "nama_pemilik = ?, jenis_hewan = ?, umur_hewan = ?, lama_penitipan = ? WHERE id_layanan = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, layanan.getNamaLayanan());
            ps.setString(2, layanan.getCatatan());
            ps.setInt(3, layanan.getHarga());
            ps.setString(4, layanan.getNamaHewan());
            ps.setString(5, layanan.getNamaPemilik());
            ps.setString(6, layanan.getJenisHewan());
            ps.setInt(7, layanan.getUmurHewan());

            if (layanan instanceof Penitipan) {
                ps.setInt(8, ((Penitipan) layanan).getLamaPenitipan());
            } else {
                ps.setNull(8, Types.INTEGER);
            }

            ps.setInt(9, layanan.getIdLayanan());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapus(int id) throws SQLException {
        String sql = "DELETE FROM layanan WHERE id_layanan = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Layanan buatObjek(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_layanan");
        String kategori = rs.getString("kategori");
        String namaLayanan = rs.getString("nama_layanan");
        String catatan = rs.getString("catatan");
        int harga = rs.getInt("harga");
        String namaHewan = rs.getString("nama_hewan");
        String namaPemilik = rs.getString("nama_pemilik");
        String jenisHewan = rs.getString("jenis_hewan");
        int umurHewan = rs.getInt("umur_hewan");

        if (kategori.equalsIgnoreCase("Penitipan")) {
            int lamaPenitipan = rs.getInt("lama_penitipan");
            return new Penitipan(id, catatan, harga, namaHewan, namaPemilik, jenisHewan, umurHewan, lamaPenitipan);
        } else {
            return new Perawatan(id, namaLayanan, catatan, harga, namaHewan, namaPemilik, jenisHewan, umurHewan);
        }
    }
}