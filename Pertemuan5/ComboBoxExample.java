<<<<<<< HEAD
package Pertemuan5;

import java.awt.event.*;
import javax.swing.*; 

public class ComboBoxExample {
	    public static void main(String[] args) { 
	        JFrame frame = new JFrame("JComboBox Example"); 
	        String[] options = { "Option 1", "Option 2", "Option 3", "Option 4" }; 

	        // Membuat JComboBox dengan opsi 
	        JComboBox<String> comboBox = new JComboBox<>(options); 

	        // Tambahkan pendengar aksi 
	        comboBox.addActionListener((ActionEvent e) -> {
	            // Mengambil item yang dipilih
	            String selected = (String) comboBox.getSelectedItem();
	            System.out.println("Selected: " + selected); 
	        }); 

	        // Atur layout dan tambahkan ke frame 
	        frame.setLayout(null); 
	        comboBox.setBounds(50, 50, 150, 20); 
	        frame.add(comboBox); 

	        // Konfigurasi frame 
	        frame.setSize(300, 200); 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        frame.setVisible(true); 
	    } 


}
=======
package Pertemuan5;

import java.awt.event.*;
import javax.swing.*; 

public class ComboBoxExample {
	    public static void main(String[] args) { 
	        JFrame frame = new JFrame("JComboBox Example"); 
	        String[] options = { "Option 1", "Option 2", "Option 3", "Option 4" }; 

	        // Membuat JComboBox dengan opsi 
	        JComboBox<String> comboBox = new JComboBox<>(options); 

	        // Tambahkan pendengar aksi 
	        comboBox.addActionListener((ActionEvent e) -> {
	            // Mengambil item yang dipilih
	            String selected = (String) comboBox.getSelectedItem();
	            System.out.println("Selected: " + selected); 
	        }); 

	        // Atur layout dan tambahkan ke frame 
	        frame.setLayout(null); 
	        comboBox.setBounds(50, 50, 150, 20); 
	        frame.add(comboBox); 

	        // Konfigurasi frame 
	        frame.setSize(300, 200); 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        frame.setVisible(true); 
	    } 


}
>>>>>>> 45f4a98bb3e148e0612dcef4ac1e973aabb33e44
