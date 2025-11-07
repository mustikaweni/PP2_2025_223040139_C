/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul05;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ACER
 */
public class Latihan2Event {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Konversi Suhu Celcius ke Fahrenheit");
            frame.setSize(400, 180);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(3, 1, 5, 5)); 
            
            JLabel labelCelcius = new JLabel("Celcius:");
            JTextField textCelcius = new JTextField();
            JLabel labelFahrenheit = new JLabel("Fahrenheit:");
            JLabel labelHasil = new JLabel("");
            JButton buttonKonversi = new JButton("Konversi");
            
            
            
            buttonKonversi.addActionListener((ActionEvent e) -> {
                try {
                    double celcius = Double.parseDouble(textCelcius.getText());
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    labelHasil.setText(String.format("%.2f °F", fahrenheit));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
             
            frame.add(labelCelcius);
            frame.add(textCelcius);
            frame.add(labelFahrenheit);
            frame.add(buttonKonversi);
            frame.add(labelHasil);
           
            frame.setVisible(true);
        });
    }
}
                    
                
                    
            
                    
                

        
                
            
            
            
            
            
            

