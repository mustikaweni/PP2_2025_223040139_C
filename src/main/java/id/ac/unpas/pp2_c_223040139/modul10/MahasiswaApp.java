/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul10;

/**
 *
 * @author ACER
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;


public class MahasiswaApp extends JFrame {

    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear;
    JTable tableMahasiswa;
    DefaultTableModel model;
    public MahasiswaApp() {
        // Setup Frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // 2. Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);
        
        // Gabungkan panel form dan tombol dibagian atas (NORTH)
       
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);
        
        // --- PANEL PENCARIAN BARU (Ditempatkan di SOUTH_OF_FORM) ---
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        
        panelCari.add(new JLabel("Cari Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        
        // Posisikan panel pencarian di atas tabel, di bawah form input
        add(panelCari, BorderLayout.SOUTH);
        
        // Listener Tombol Cari
    btnCari.addActionListener(e -> cariData());
    // Agar bisa mencari saat menekan Enter di kolom teks
    txtCari.addActionListener(e -> cariData());
        
        // 2. Tabel Data (Menampilkan Data)
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

           // Listener Tombol Cari
        btnCari.addActionListener(e -> cariData());
        // Listener field Cari (Opsional: agar bisa tekan Enter)
        txtCari.addActionListener(e -> cariData());
        // --- Event Listeners ---

        // Listener Klik Tabel (Untuk mengambil data saat baris diklik)
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Aksi Tombol Simpan (CREATE)
        btnSimpan.addActionListener(e -> tambahData());

        // Aksi Tombol Edit (UPDATE)
        btnEdit.addActionListener(e -> ubahData());

        // Aksi Tombol Hapus (DELETE)
        btnHapus.addActionListener(e -> hapusData());
        
        // Aksi Tombol Clear
        btnClear.addActionListener(e -> kosongkanForm());
        
        // Jika ada tombol Cari
        btnCari.addActionListener(e -> cariData());

 {
        cariData();
    }

    }   
        private void loadData(String sql) {
    model.setRowCount(0); // Reset tabel
    try {
        Connection conn = koneksiDB.configDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);

        int no = 1;
        while (res.next()) {
            model.addRow(new Object[]{
                no++,
                res.getString("nama"),
                res.getString("nim"),
                res.getString("jurusan")
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
    }
} 
        private void loadData() {
    loadData("SELECT * FROM mahasiswa");
}

// Buat fungsi loadData tanpa parameter (untuk dipanggil di konstruktor)

    private void cariData() {
    
    String kataKunci = txtCari.getText().trim();
    String query;
    
    if (kataKunci.isEmpty()) {
        query = "SELECT * FROM mahasiswa"; 
    } else {
        query = "SELECT * FROM mahasiswa WHERE nama LIKE '%" + kataKunci + "%' OR nim LIKE '%" + kataKunci + "%'";
    }
    
    loadData(query); 
}
    
    // FUNGSI UTAMA UNTUK MENAMPILKAN DATA (Bisa menerima query custom)
    private void tampilkanData(String sqlQuery) {
        model.setRowCount(0); // Reset tabel
        try {
            Connection conn = koneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sqlQuery);

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }
    // 2. CREATE (Menambah Data)
    private void tambahData() {
        try {
            // --- START: VALIDASI INPUT ---
        String nama = txtNama.getText().trim();
        String nim = txtNIM.getText().trim();
        
        // Periksa apakah field Nama atau NIM kosong
        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                                          "Data tidak boleh kosong!", 
                                          "Input Error", 
                                          JOptionPane.ERROR_MESSAGE);
            return; // Batalkan proses penyimpanan jika ada field yang kosong
        } 
        // 2. CEK APAKAH NIM SUDAH ADA 
        String sqlCek = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        Connection conn = koneksiDB.configDB();
        PreparedStatement pstCek = conn.prepareStatement(sqlCek);
        pstCek.setString(1, nim);
        ResultSet rs = pstCek.executeQuery();
        
        if (rs.next()) {
            int jumlah = rs.getInt(1);
            if (jumlah > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal Simpan: NIM " + nim + " sudah terdaftar!", 
                    "Peringatan", 
                    JOptionPane.WARNING_MESSAGE);
                return; // Berhenti di sini, jangan lanjut ke INSERT
            }
        }
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());

            pst.execute();

            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }
    // 3. UPDATE (Mengubah Data berdasarkan NIM)
    private void ubahData() {
        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = koneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); // Kunci update

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }
    // 4. DELETE (Menghapus Data)
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = koneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNIM.getText());

            pst.execute();

            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
        
        
    }private void kosongkanForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
    }

    public static void main(String[] args) {
        // Menjalankan Aplikasi
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}

