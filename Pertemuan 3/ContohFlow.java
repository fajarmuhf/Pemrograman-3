//memanggil library di Java
import javax.swing.*;
//memanggil library di Layout
import java.awt.FlowLayout;

public class ContohFlow{
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
		bungkus.setLayout(new FlowLayout());
		bungkus.add(judul);
		bungkus.add(input);
		bungkus.add(button3);
		
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
