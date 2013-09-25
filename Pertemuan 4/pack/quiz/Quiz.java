package pack.quiz;

//memanggil library di Java
import javax.swing.*;
//memanggil library di Layout
import java.awt.*;

public class Quiz{
	public static void main(String[] arg){
		//elemen Atas
		JLabel Judul = new JLabel("Form Data Mahasiswa");
		Judul.setForeground(new Color(0,0,139));
		Judul.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//elemen tengah
		JLabel username = new JLabel("NAMA");
		JTextField inputUser = new JTextField(15);
		username.setForeground(new Color(0,0,139));
		username.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputUser.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel nim = new JLabel("NIM");
		JTextField inputNim = new JTextField(15);
		nim.setForeground(new Color(0,0,139));
		nim.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputNim.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel ipk = new JLabel("IPK");
		JTextField inputIpk = new JTextField(15);
		ipk.setForeground(new Color(0,0,139));
		ipk.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputIpk.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//elemen bawah
		JButton Oke = new JButton("SIMPAN");
		Oke.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JButton Batal = new JButton("BATAL");
		Batal.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//footer
		JLabel Bawah = new JLabel("Dibuat untuk tugas quiz 1");
		Bawah.setForeground(new Color(0,0,139));
		Bawah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		//membuat panel
		JPanel bungkus = new JPanel();
		bungkus.setBackground(new Color(0,191,255));
		bungkus.setLayout(new BorderLayout());
		
		JPanel flowku = new JPanel();
		flowku.setLayout(new FlowLayout());
		flowku.add(Judul);
		
		JPanel gridku = new JPanel();
		gridku.setLayout(new GridLayout(3,2));
		gridku.add(username);
		gridku.add(inputUser);
		gridku.add(nim);
		gridku.add(inputNim);
		gridku.add(ipk);
		gridku.add(inputIpk);
		
		JPanel flowku2 = new JPanel();
		flowku2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		flowku2.add(Oke);
		flowku2.add(Batal);
		
		JPanel flowku3 = new JPanel();
		flowku3.setLayout(new FlowLayout(FlowLayout.LEADING));
		flowku3.add(Bawah);
		
		JPanel gridku2 = new JPanel();
		gridku2.setLayout(new GridLayout(2,1));
		gridku2.add(flowku2);
		gridku2.add(flowku3);
		
		bungkus.add(flowku,BorderLayout.PAGE_START);
		bungkus.add(gridku,BorderLayout.CENTER);
		bungkus.add(gridku2,BorderLayout.PAGE_END);
		
		//membuat frame
		JFrame bingkai = new JFrame("Form Mahasiswa");
		//memasukan yang dibungkus di panel kedalam frame
		bingkai.getContentPane().add(bungkus);
		//mengeset ukuran bingkai
		bingkai.setSize(300,160);
		//meletakan bingkai dengan posisi ditengah
		bingkai.setLocationRelativeTo(null);
		//menampilkan
		bingkai.setVisible(true);
		bingkai.pack();
	}
}
