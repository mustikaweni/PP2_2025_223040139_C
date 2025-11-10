package id.ac.unpas.pp2_c_223040139.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class tugas1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh BorderLayout");
            frame.setSize(400,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            frame.setLayout(new BorderLayout());

            JButton buttonNorth = new JButton("Tombol di Atas (NORTH)"); 
            JButton buttonSouth = new JButton("Tombol ada di Bawah (SOUTH)");
            JButton buttonWest = new JButton("WEST");
            JButton buttonCenter = new JButton("CENTER");
            JButton buttonEast = new JButton("EAST");

            buttonSouth.addActionListener(e -> buttonNorth.setText("Tombol SOUTH diklik!")); 
            buttonWest.addActionListener(e -> buttonNorth.setText("Tombol WEST diklik!"));
            buttonCenter.addActionListener(e -> buttonNorth.setText("Tombol CENTER diklik!"));
            buttonEast.addActionListener(e -> buttonNorth.setText("Tombol EAST diklik!"));
            buttonNorth.addActionListener(e -> buttonNorth.setText("Tombol NORTH diklik!"));

            frame.add(buttonNorth, BorderLayout.NORTH);
            frame.add(buttonSouth, BorderLayout.SOUTH);
            frame.add(buttonWest, BorderLayout.WEST);
            frame.add(buttonCenter, BorderLayout.CENTER);
            frame.add(buttonEast, BorderLayout.EAST);

            frame.setVisible(true);
        });
    }
}
