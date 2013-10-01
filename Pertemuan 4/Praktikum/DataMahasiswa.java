package Praktikum;

//memanggil library di Java
import javax.swing.*;
//memanggil library di Layout
import java.awt.*;
import Praktikum.FileChooserDemo;

public class DataMahasiswa {
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
		
		JLabel jenkel = new JLabel("JENIS KELAMIN");
		JRadioButton laki = new JRadioButton("Laki-Laki");
		JRadioButton perempuan = new JRadioButton("Perempuan");
		ButtonGroup inputBGroup = new ButtonGroup();
		jenkel.setForeground(new Color(0,0,139));
		jenkel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		laki.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		laki.setForeground(new Color(0,0,139));
		perempuan.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		perempuan.setForeground(new Color(0,0,139));
		
		inputBGroup.add(laki);
		inputBGroup.add(perempuan);
		
		JPanel inputJenkel = new JPanel();
		inputJenkel.setLayout(new GridLayout(2,1));
		inputJenkel.add(laki);
		inputJenkel.add(perempuan);
		
		JLabel asal_sekolah = new JLabel("ASAL SEKOLAH");
		asal_sekolah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		asal_sekolah.setForeground(new Color(0,0,139));
		String[] AsalSekolahStrings = { "SMA - IPA", "SMA - IPS", "SMA - BAHASA", "SMK" };
		JComboBox inputAsalSekolah = new JComboBox(AsalSekolahStrings);
		inputAsalSekolah.setSelectedIndex(0);
		inputAsalSekolah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputAsalSekolah.setForeground(new Color(0,0,139));
		
		JLabel nama_sekolah = new JLabel("NAMA SEKOLAH");
		JTextField inputNamaSekolah = new JTextField(15);
		nama_sekolah.setForeground(new Color(0,0,139));
		nama_sekolah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputNamaSekolah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel alamat_sekolah = new JLabel("ALAMAT SEKOLAH");
		JTextField inputAlamatSekolah = new JTextField(15);
		alamat_sekolah.setForeground(new Color(0,0,139));
		alamat_sekolah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputAlamatSekolah.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputAlamatSekolah.setForeground(new Color(0,0,139));
		
		JLabel fotoku = new JLabel("FOTO");
		fotoku.setForeground(new Color(0,0,139));
		fotoku.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		JButton inputFoto = new JButton("Open a File...");
		inputFoto.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		inputFoto.addActionListener(new FileChooserDemo());
		inputFoto.setActionCommand("1");
		
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
		
		JPanel gridku = new JPanel();
		gridku.setLayout(new GridLayout(7,2));
		gridku.add(username);
		gridku.add(inputUser);
		gridku.add(nim);
		gridku.add(inputNim);
		gridku.add(jenkel);
		gridku.add(inputJenkel);
		gridku.add(asal_sekolah);
		gridku.add(inputAsalSekolah);
		gridku.add(nama_sekolah);
		gridku.add(inputNamaSekolah);
		gridku.add(alamat_sekolah);
		gridku.add(inputAlamatSekolah);
		gridku.add(fotoku);
		gridku.add(inputFoto);
		
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
