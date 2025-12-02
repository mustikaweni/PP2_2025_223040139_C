package id.ac.unpas.modul07;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public ManajemenNilaiSiswaApp() {

        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        add(tabbedPane);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);
       
        panel.add(new JLabel(""));  
        JButton btnSimpan = new JButton("Simpan Data");
        JButton btnReset = new JButton("Reset Input");

        panel.add(btnSimpan); 
        panel.add(new JLabel("")); 
        panel.add(btnReset);
        
        // tombol Simpan
        btnSimpan.addActionListener(e -> prosesSimpan());

        // tombol Reset
        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMatkul.setSelectedIndex(0);
        });

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // tombol hapus bawah tabel
        JPanel panelButton = new JPanel();
        JButton btnHapus = new JButton("Hapus Data");
        panelButton.add(btnHapus);
        panel.add(panelButton, BorderLayout.SOUTH);

        btnHapus.addActionListener(e -> {
            int baris = tableData.getSelectedRow();
            if (baris > -1) {
                tableModel.removeRow(baris);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        return panel;
    }

    private void prosesSimpan() {
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // Validasi panjang nama minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal 3 karakter!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!",
                        "Error Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Grade menggunakan switch case
String grade ;
 switch (nilai / 10) {
    case 10://100
    case 9: //90-99
    case 8: //80-89
        grade = "A";
        break;
    case 7: //70-79
        grade = "AB";
        break;
    case 6: //60-69
        grade = "B";
        break;
    case 5: //50-55
        grade = "BC";
        break;
    case 4: //40-49
        grade = "C";
        break;
    case 3: //30-39
        grade = "D";
        break;
    default: //0-29
        grade = "E";
        break;
}


        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1);
    }
    private void hapusData() {
        int row = tableData.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
        }
    }

    private void resetInput() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
         JOptionPane.showMessageDialog(this, "Input berhasil direset!");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}
