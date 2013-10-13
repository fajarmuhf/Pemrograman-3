package Latihan;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

import Latihan.DataMahasiswa;

class Aksi extends DataMahasiswa implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "1"){
			System.out.println("Data Tersimpan");
		}
		else if(e.getActionCommand() == "2"){
			System.out.println("Data Dibatalkan");
		}
	}
}
