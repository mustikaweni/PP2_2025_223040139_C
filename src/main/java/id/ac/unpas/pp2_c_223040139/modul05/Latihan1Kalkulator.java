/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_223040139.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class Latihan1Kalkulator {
    public static void main(String[] args){
        JFrame frame = new JFrame ("Kalkulator Sederhana");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(300, 400);
         
         JTextField layar = new JTextField();
         frame.add(layar, BorderLayout.NORTH);
         
         JPanel panelTombol = new JPanel();
         panelTombol.setLayout(new GridLayout(4, 4, 5, 5));
         
         panelTombol.add(new JButton("7"));
         panelTombol.add(new JButton("8"));
         panelTombol.add(new JButton("9"));
         panelTombol.add(new JButton("/"));
         panelTombol.add(new JButton("4"));
         panelTombol.add(new JButton("5"));
         panelTombol.add(new JButton("6"));
         panelTombol.add(new JButton("."));
         panelTombol.add(new JButton("1"));
         panelTombol.add(new JButton("2"));
         panelTombol.add(new JButton("3"));
         panelTombol.add(new JButton("~"));
         panelTombol.add(new JButton("-"));
         panelTombol.add(new JButton("*"));
         
         frame.add(panelTombol, BorderLayout.CENTER);
         frame.setVisible(true);
             
         
    }
    
}
