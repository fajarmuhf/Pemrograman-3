package Calc;

import java.util.Scanner;
import java.util.InputMismatchException;

public class kalkulator{
	public static void main(String[] arg){
		Scanner data = new Scanner(System.in);
		int pilih = 0;
		float angka1=0;
		float angka2=0;
		float operasi=0;
		float hasil=0;	
		do{
			System.out.println("================");
			System.out.println("Menu Pilihan");
			System.out.println("1.Input");
			System.out.println("2.Tampil");
			System.out.println("3.Keluar");
			System.out.println("================");
			System.out.println("Masukan Pilihan Anda : ");
			pilih = data.nextInt();
			if(pilih == 1){
				int kesalahan = 0;
				do{
					try{
						System.out.print("Masukan Angka Pertama : ");
						angka1 = data.nextFloat();
						System.out.print("Masukan Angka Pertama : ");
						angka2 = data.nextFloat();
						System.out.println("Masukan Operasi Pilihan");					
						System.out.println("================");
						System.out.println("1.+");
						System.out.println("2.-");
						System.out.println("3./");
						System.out.println("4.*");
						System.out.println("================");
						operasi = data.nextFloat();
						kesalahan = 0;
					}
					catch(InputMismatchException ime){
						System.out.println("Maaf Anda salah memasukan");
						data.next();
						kesalahan = 1;
					}
				}while(kesalahan != 0);
			}
			else if(pilih == 2){
				if(operasi == 1){
					hasil = angka1 + angka2;
				}
				else if(operasi == 2){
					hasil = angka1 - angka2;
				}
				else if(operasi == 3){
					hasil = angka1 / angka2;
				}
				else if(operasi == 4){
					hasil = angka1 * angka2;
				}
				if(operasi >= 1 && operasi <= 4){
					System.out.println("Hasil dari operasi dua bilangan : "+hasil);
				}
				else{
					System.out.println("Maaf Anda Belum menginput dengan Benar ");
				}
			}
		}while(pilih != 3);
		System.out.println("Terima Kasih sudah menggunakan program ini...");
	}
}
