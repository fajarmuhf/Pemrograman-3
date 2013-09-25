
//memanggil library di Java
import javax.swing.*;
//memanggil library di Layout
import java.awt.*;

public class AppNoneLayout{
	public static void main(String[] arg){
		//elemen Atas
		JLabel Judul = new JLabel("Form Password");
		Judul.setForeground(new Color(0,0,139));
		Judul.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//elemen tengah
		JLabel username = new JLabel("USERNAME");
		JTextField inputUser = new JTextField(15);
		username.setForeground(new Color(0,0,139));
		username.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputUser.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel password = new JLabel("PASSWORD");
		JTextField inputPass = new JTextField(15);
		password.setForeground(new Color(0,0,139));
		password.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputPass.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//elemen bawah
		JButton Oke = new JButton("OK");
		Oke.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JButton Batal = new JButton("BATAL");
		Batal.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//footer
		JLabel Bawah = new JLabel("Dibuat untuk tugas sesi 2");
		Bawah.setForeground(new Color(0,0,139));
		Bawah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//membuat panel
		JPanel bungkus = new JPanel();
		bungkus.setBackground(new Color(0,191,255));
		bungkus.setLayout(null);
		bungkus.add(Judul);
		bungkus.add(username);
		bungkus.add(inputUser);
		bungkus.add(password);
		bungkus.add(inputPass);
		bungkus.add(Oke);
		bungkus.add(Batal);
		bungkus.add(Bawah);
		
		//Penentuan koordinat element
		
		//menentukan koordinat Judul
		Insets insets = bungkus.getInsets();
		Dimension size = Judul.getPreferredSize();
		Judul.setBounds(100 + insets.left, 5 + insets.top, size.width, size.height);
		
		//menentukan koordinat Username
		Dimension size2 = username.getPreferredSize();
		username.setBounds(10 + insets.left, 25 + insets.top, size2.width, size2.height);
		
		//menentukan koordinat inputUser
		Dimension size3 = inputUser.getPreferredSize();
		inputUser.setBounds(105 + insets.left, 25 + insets.top, size3.width, size3.height);
		
		//menentukan koordinat Password
		Dimension size4 = password.getPreferredSize();
		password.setBounds(10 + insets.left, 50 + insets.top, size4.width, size4.height);
		
		//menentukan koordinat inputPassword
		Dimension size5 = inputPass.getPreferredSize();
		inputPass.setBounds(105 + insets.left, 50 + insets.top, size5.width, size5.height);
		
		//menentukan koordinat Oke
		Dimension size6 = Oke.getPreferredSize();
		Oke.setBounds(80 + insets.left, 75 + insets.top, size6.width, size6.height);
		
		//menentukan koordinat Batal
		Dimension size7 = Batal.getPreferredSize();
		Batal.setBounds(140 + insets.left, 75 + insets.top, size7.width, size7.height);
		
		//menentukan koordinat footer
		Dimension size8 = Bawah.getPreferredSize();
		Bawah.setBounds(10 + insets.left, 110 + insets.top, size8.width, size8.height);
		
		
		//membuat frame
		JFrame bingkai = new JFrame("Form Password");
		//memasukan yang dibungkus di panel kedalam frame
		bingkai.getContentPane().add(bungkus);
		//mengeset ukuran bingkai
		bingkai.setSize(300,160);
		//meletakan bingkai dengan posisi ditengah
		bingkai.setLocationRelativeTo(null);
		//menampilkan
		bingkai.setVisible(true);
		//mengubah color bingkai
		
	}
}
