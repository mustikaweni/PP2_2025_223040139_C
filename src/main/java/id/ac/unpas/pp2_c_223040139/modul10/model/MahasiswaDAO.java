/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul10.model;
import id.ac.unpas.pp2_c_223040139.modul10.Mahasiswa;
import id.ac.unpas.pp2_c_223040139.modul10.koneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    public List<Mahasiswa> getMahasiswa(String sql) throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        try (Connection conn = koneksiDB.configDB();
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(sql)) {
            while (res.next()) {
                list.add(new Mahasiswa(res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
            }
        }
        return list;
    }

    public boolean isNimExists(String nim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        try (Connection conn = koneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            ResultSet rs = pst.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public void insert(Mahasiswa m) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        try (Connection conn = koneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, m.getNama());
            pst.setString(2, m.getNim());
            pst.setString(3, m.getJurusan());
            pst.execute();
        }
    }

    public void update(Mahasiswa m) throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        try (Connection conn = koneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, m.getNama());
            pst.setString(2, m.getJurusan());
            pst.setString(3, m.getNim());
            pst.executeUpdate();
        }
    }

    public void delete(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (Connection conn = koneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            pst.execute();
        }
    }
}