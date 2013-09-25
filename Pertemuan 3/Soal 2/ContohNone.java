//memanggil library di Java
import javax.swing.*;
//memanggil library di Layout
import java.awt.*;

public class ContohNone{
	public static void main(String[] arg){
		//elemen yang digunakan
		JButton button1 = new JButton("Tombol Button 1");
		JButton button2 = new JButton("Tombol Button 2");
		JButton button3 = new JButton("Tombol Button 3");
		//menambahkan elemen label
		JLabel judul = new JLabel("Nama : ");
		//menambahkan elemen TextField
		JTextField input = new JTextField(20);
		
		//membuat panel
		JPanel bungkus = new JPanel();
		bungkus.setLayout(null);
		bungkus.add(judul);
		bungkus.add(button1);
		bungkus.add(input);
		bungkus.add(button3);
		bungkus.add(button2);
		
		//menentukan koordinat Buttton1
		Insets insets = bungkus.getInsets();
		Dimension size = button1.getPreferredSize();
		button1.setBounds(25 + insets.left, 5 + insets.top, size.width, size.height);
		
		//menentukan koordinat Button2
		Dimension size2 = button2.getPreferredSize();
		button2.setBounds(200 + insets.left, 5 + insets.top, size2.width, size2.height);
		
		//menentukan koordinat Button3
		Dimension size3 = button3.getPreferredSize();
		button3.setBounds(375 + insets.left, 5 + insets.top, size3.width, size3.height);
		
		
		//membuat frame
		JFrame bingkai = new JFrame("Menggunakan Layout FlowLayout");
		//memasukan yang dibungkus di panel kedalam frame
		bingkai.getContentPane().add(bungkus);
		//mengeset ukuran bingkai
		bingkai.setSize(900,300);
		//meletakan bingkai dengan posisi ditengah
		bingkai.setLocationRelativeTo(null);
		//menampilkan
		bingkai.setVisible(true);
	}
}
