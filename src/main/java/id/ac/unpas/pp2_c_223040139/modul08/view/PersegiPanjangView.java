/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul08.view;



import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-"); // Ubah nama label untuk Luas
    private JLabel lblHasilKeliling = new JLabel("-"); // Tambahan Latihan 2
    private JButton btnHitung = new JButton("Hitung Luas & Keliling"); // Ubah teks tombol
    private JButton btnReset = new JButton("Reset"); // Tambahan Latihan 3

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 250); // Sesuaikan ukuran
        // Grid 6 baris, 2 kolom, gap 10 horizontal dan 10 vertikal
        this.setLayout(new GridLayout(6, 2, 10, 10)); 
        this.setTitle("MVC Kalkulator");
        
        // Baris 1 & 2 (Input)
        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);

        // Baris 3 & 4 (Output)
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilLuas);
        this.add(new JLabel("Hasil Keliling:")); // Tambahan Latihan 2
        this.add(lblHasilKeliling); // Tambahan Latihan 2
        
        // Baris 5 & 6 (Tombol)
        this.add(btnHitung);
        this.add(btnReset); // Tambahan Latihan 3

        this.setVisible(true); // Tampilkan di konstruktor agar Main lebih bersih
    }

    // --- METODE LAMA (Getter & Setter) ---
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Mengubah setHasil menjadi setLuas
    public void setLuas(double luas) {
        lblHasilLuas.setText(String.valueOf(luas));
    }
    
    // Setter Keliling - Tambahan Latihan 2
    public void setKeliling(double keliling) {
        lblHasilKeliling.setText(String.valueOf(keliling));
    }

    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // --- METODE BARU (Reset Input) ---
    public void resetInput() { // Tambahan Latihan 3
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
    }

    // --- METODE BARU (Add Listener) ---
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
    // Listener untuk tombol Reset - Tambahan Latihan 3
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}