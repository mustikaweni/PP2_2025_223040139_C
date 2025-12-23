/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul10.controller;

import id.ac.unpas.pp2_c_223040139.modul10.Mahasiswa;
import id.ac.unpas.pp2_c_223040139.modul10.model.MahasiswaDAO;
import id.ac.unpas.pp2_c_223040139.modul10.view.MahasiswaView;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class MahasiswaController {
    private MahasiswaView view;
    private final MahasiswaDAO dao;

    public MahasiswaController(MahasiswaView view, MahasiswaDAO dao) {
        this.view = view;
        this.dao = dao;

        loadData("SELECT * FROM mahasiswa");

        this.view.btnSimpan.addActionListener(e -> tambahData());
        this.view.btnEdit.addActionListener(e -> ubahData());
        this.view.btnHapus.addActionListener(e -> hapusData());
        this.view.btnCari.addActionListener(e -> cariData());
        this.view.btnClear.addActionListener(e -> kosongkanForm());

        this.view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });
    }

    private void loadData(String query) {
        try {
            view.model.setRowCount(0);
            List<Mahasiswa> list = dao.getMahasiswa(query);
            int no = 1;
            for (Mahasiswa m : list) {
                view.model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(view, e.getMessage()); }
    }

    private void tambahData() {
        try {
            String nim = view.txtNIM.getText().trim();
            if (nim.isEmpty() || dao.isNimExists(nim)) {
                JOptionPane.showMessageDialog(view, "NIM Kosong atau Sudah Ada!");
                return;
            }
            dao.insert(new Mahasiswa(view.txtNama.getText(), nim, view.txtJurusan.getText()));
            loadData("SELECT * FROM mahasiswa");
            kosongkanForm();
        } catch (Exception e) { JOptionPane.showMessageDialog(view, e.getMessage()); }
    }

    private void ubahData() {
        try {
            dao.update(new Mahasiswa(view.txtNama.getText(), view.txtNIM.getText(), view.txtJurusan.getText()));
            loadData("SELECT * FROM mahasiswa");
            kosongkanForm();
        } catch (Exception e) { JOptionPane.showMessageDialog(view, e.getMessage()); }
    }

    private void hapusData() {
        try {
            dao.delete(view.txtNIM.getText());
            loadData("SELECT * FROM mahasiswa");
            kosongkanForm();
        } catch (Exception e) { JOptionPane.showMessageDialog(view, e.getMessage()); }
    }

    private void cariData() {
        String k = view.txtCari.getText().trim();
        loadData("SELECT * FROM mahasiswa WHERE nama LIKE '%"+k+"%' OR nim LIKE '%"+k+"%'");
    }

    private void kosongkanForm() {
        view.txtNama.setText(""); view.txtNIM.setText(""); view.txtJurusan.setText("");
    }
}