//memanggil library di Java
import javax.swing.*;
//memanggil library di Layout
import java.awt.*;

public class ContohCard{
	public static void main(String[] arg){
		//elemen yang digunakan
		JButton button1 = new JButton("Tombol Button 1");
		JButton button2 = new JButton("Tombol Button 2");
		JButton button3 = new JButton("Tombol Button 3");
		//menambahkan elemen label
		JLabel judul = new JLabel("Nama : ");
		//menambahkan elemen TextField
		JTextField input = new JTextField(20);
		
		//membuat card1
		JPanel card1 = new JPanel();
		card1.add(button1);
		card1.add(button2);
		card1.add(button3);
		
		//main card
		JPanel mainCard = new JPanel();
		CardLayout card = new CardLayout();
		mainCard.setLayout(card);
		mainCard.add(card1,"Card 1 with JButtons");
		
		//membuat panel
		JPanel bungkus = new JPanel();
		
		//menambahkan elemen card ke panel
		bungkus.add(mainCard);
		
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
