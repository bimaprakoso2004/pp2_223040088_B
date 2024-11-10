package Pertemuan3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloBorderLayout extends JFrame {
    
    public HelloBorderLayout(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Membuat komponen label dan tombol
        JLabel labelPertanyaan = new JLabel("Apakah ibukota Indonesia?", JLabel.CENTER);
        JLabel labelHasil = new JLabel("Jawab pertanyaan di atas", JLabel.CENTER);
        
        JButton buttonA = new JButton("Jakarta");
        JButton buttonB = new JButton("Bandung");
        JButton buttonC = new JButton("Surabaya");
        
        // Menambahkan ActionListener pada tombol
        buttonA.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                labelHasil.setText("Jawaban anda benar");
            }
        });
        
        buttonB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                labelHasil.setText("Jawaban anda salah");
            }
        });
        
        buttonC.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                labelHasil.setText("Jawaban anda salah");
            }
        });
        
        // Menggunakan panel untuk mengatur posisi tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new HelloGridLayout(1, 3)); // Menyusun tombol dalam 1 baris, 3 kolom
        panelTombol.add(buttonA);
        panelTombol.add(buttonB);
        panelTombol.add(buttonC);
        
        // Menambahkan komponen ke frame dengan BorderLayout
        this.setLayout(new BorderLayout());
        this.add(labelPertanyaan, BorderLayout.NORTH);
        this.add(labelHasil, BorderLayout.SOUTH);
        this.add(panelTombol, BorderLayout.CENTER); // Menaruh panel tombol di tengah
        
        this.setSize(400, 200);
    }
    
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                HelloBorderLayout h = new HelloBorderLayout();
                h.setVisible(true);
            }
        });
    }
}
