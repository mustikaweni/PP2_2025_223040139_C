/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul10;

import id.ac.unpas.pp2_c_223040139.modul10.controller.MahasiswaController;
import id.ac.unpas.pp2_c_223040139.modul10.model.MahasiswaDAO;
import id.ac.unpas.pp2_c_223040139.modul10.view.MahasiswaView;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        // Menjalankan GUI di thread yang benar (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            // 1. Inisialisasi View (Tampilan)
            MahasiswaView view = new MahasiswaView();
            
            // 2. Inisialisasi DAO (Akses Database)
            MahasiswaDAO dao = new MahasiswaDAO();
            
            // 3. Inisialisasi Controller (Penghubung)
            // Controller secara otomatis akan mengatur listener tombol di dalam View
            new MahasiswaController(view, dao);
            
            // 4. Tampilkan aplikasi
            view.setVisible(true);
        });
    }
}
    
