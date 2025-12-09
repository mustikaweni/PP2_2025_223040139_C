/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package id.ac.unpas.pp2_c_223040139.modul08.Controller;

import id.ac.unpas.pp2_c_223040139.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_223040139.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PersegiPanjangController {
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Daftarkan listener untuk tombol Hitung
        this.view.addHitungListener(new HitungListener());
        // Daftarkan listener untuk tombol Reset - Tambahan Latihan 3
        this.view.addResetListener(new ResetListener());
    }

    // Event inner class untuk tombol Hitung
    class HitungListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil input dari view
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Kirim data ke model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Hitung luas dan keliling di Model
                model.hitungLuas();
                model.hitungKeliling(); // Tambahan Latihan 2

                // 4. Ambil hasil dari Model
                double hasilLuas = model.getLuas();
                double hasilKeliling = model.getKeliling(); // Tambahan Latihan 2

                // 5. Tampilkan hasil ke view
                view.setLuas(hasilLuas);
                view.setKeliling(hasilKeliling); // Tambahan Latihan 2

            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }
    
    // Event inner class untuk tombol Reset - Tambahan Latihan 3
    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Panggil metode resetInput pada View
            view.resetInput();
        }
    }
}
