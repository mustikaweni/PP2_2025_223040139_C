/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul09;

/**
 *
 * @author ACER
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {
    
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnAppendText; // Deklarasi tombol baru
    private JFileChooser fileChooser;


public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling"); // Judul Frame
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text (Overwrite)"); // Ganti label agar lebih jelas
        btnAppendText = new JButton("Tambah Text (Append)"); // Inisialisasi tombol baru
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Tambahkan tombol ke panel
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling TEKS (Text Stream) ---
        // 1. MEMBACA FILE TEKS
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Overwrite)
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // 5. MENAMBAH FILE TEKS (Append)
        btnAppendText.addActionListener(e -> tambahFileTeks()); // Daftarkan listener untuk tombol baru
        
        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanObjek());

        // 4. MEMBACA FILE BINARY
        btnLoadBinary.addActionListener(e -> muatObjek());
        
        
        //membaca file bernama last_notes
        bacaLastNotes();
    
        setVisible(true);
    
    }

    // Baca last_notes.txt 
    private void bacaLastNotes() {
        File file = new File("last_notes.txt");
        
        // Gunakan Try-with-resources untuk otomatis 
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText(""); 
            String line;
            
            while((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            // Aplikasi hanya diam saja jika berhasil dimuat.
            
        } catch (FileNotFoundException ex) {
            // Biarkan Kosong 
        } catch (IOException ex) {
            // Gagal membaca karena alasan lain, cetak stack trace
            JOptionPane.showMessageDialog(this, "Gagal membaca last_notes.txt:" + ex.getMessage());
        }
    }
 
// Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
private void bukaFileTeks() {
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally
        
        try {
            // Membuka stream
            reader = new BufferedReader(new FileReader(file));
            textArea.setText(""); // Kosongkan area
            
            String line;
            
            // Baca baris demi baris
            while((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            
            JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
        } finally {
            // Blok Finally: Selalu dijalankan untuk menutup resource
            try {
                if (reader != null) {
                    reader.close(); 
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

// Contoh: Menulis File Teks menggunakan Try-with-Resources (Overwrite)
private void simpanFileTeks() {
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        
     
        // Default FileWriter adalah OVERWRITE (boolean append = false)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { 
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "File berhasil disimpan (Overwrite)!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
        }
    }
}

// Implementasi fitur Append
private void tambahFileTeks() {
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        
        try (
            // Gunakan FileWriter(File file, boolean append) dengan 'true' untuk mode APPEND
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))
        ) {
            // Ambil teks dari JTextArea
            String contentToAppend = textArea.getText();
            
            // Tambahkan baris baru sebelum teks baru (agar tidak menyambung di baris yang sama)
            if (file.exists() && file.length() > 0) {
                 writer.write("\n");
            }
            
            writer.write(contentToAppend);
            
            JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan (Append)!");
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan teks: " + ex.getMessage());
        }
    }
}

// Method simpanObjek(), muatObjek(), simpanConfigBinary(), muatConfigBinary() tidak diubah

private void simpanObjek() {
    // 1. Ambil data dari UI
    String username = "DefaultUser"; // Contoh data string
    int fontSize = textArea.getFont().getSize();
    
    // 2. Buat Objek Model
    UserConfig config = new UserConfig();
    config.setUsername(username);
    config.setFontsize(fontSize);

    // 3. Simpan Objek ke File menggunakan ObjectOutputStream
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_config.ser"))) {
        
        oos.writeObject(config); // Menyimpan objek
        
        JOptionPane.showMessageDialog(this, 
            "Objek UserConfig (Ukuran Font: " + fontSize + ") berhasil disimpan ke user_config.ser");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
    }
}
    private void muatObjek() {
    UserConfig config = null;
    
    // 1. Muat Objek dari File menggunakan ObjectInputStream
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_config.ser"))) {
        
        // 2. Baca objek dan lakukan Casting
        config = (UserConfig) ois.readObject();
        
        // 3. Terapkan data objek ke UI
        int loadedFontSize = config.getFontsize();
        String loadedUsername = config.getUsername();
        
        textArea.setFont(new Font("Monospaced", Font.PLAIN, loadedFontSize));
        
        JOptionPane.showMessageDialog(this, 
            "Objek UserConfig berhasil dimuat. \nUser: " + loadedUsername + ", Font: " + loadedFontSize);
            
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "File user_config.ser tidak ditemukan!");
    } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "Kelas objek tidak ditemukan: " + ex.getMessage());
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal membaca objek: " + ex.getMessage());
    }
}
    
// Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
private void simpanConfigBinary() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
        // Kita simpan ukuran font saat ini integer
        int fontSize = textArea.getFont().getSize();
        dos.writeInt(fontSize);
        
        JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
    }
}

// Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
private void muatConfigBinary() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
        // Membaca data Integer mentah
        int fontSize = dis.readInt();
        
        // Terapkan ke aplikasi
        textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new AplikasiFileIO().setVisible(true);
    });
}
}
