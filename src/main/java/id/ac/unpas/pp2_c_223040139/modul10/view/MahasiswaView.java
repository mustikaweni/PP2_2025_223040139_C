/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul10.view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
 


public class MahasiswaView extends JFrame {
    public JTextField txtNama, txtNIM, txtJurusan, txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    public JTable tableMahasiswa;
    public DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi Mahasiswa MVC");
        setSize(600, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Form
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelForm.add(new JLabel("Nama:")); txtNama = new JTextField(); panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:")); txtNIM = new JTextField(); panelForm.add(txtNIM);
        panelForm.add(new JLabel("Jurusan:")); txtJurusan = new JTextField(); panelForm.add(txtJurusan);

        // Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan"); btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus"); btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan); panelTombol.add(btnEdit);
        panelTombol.add(btnHapus); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // Tabel
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);

        // Cari
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15); btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari:")); panelCari.add(txtCari); panelCari.add(btnCari);
        add(panelCari, BorderLayout.SOUTH);
    }
}
