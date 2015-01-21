package Game;

import java.io.*;

public class simpanText{
	public void Simpan(String input,String url){
		BufferedWriter writer = null;
		try {
			
			writer = new BufferedWriter(new FileWriter(url));
			writer.write(input);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
					
			}
		}
	}
	public String Baca(String url){
		String Isi="";
		BufferedReader br = null;
		try {
			String sCurrentLine;
			
			br = new BufferedReader(new FileReader(url));
 
			while ((sCurrentLine = br.readLine()) != null) {
				Isi += (sCurrentLine);
			}
 
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return Isi;
	}
	public String BacaByIndex(String url,int i,int j){
		String Str = Baca(url);
		String hasil = "";
		int a=0;
		int b=0;
		for (String retval: Str.split(System.getProperty("line.separator"))){
			for (String retval2: retval.split(";")){
				if(a == i && b == j){
					hasil = retval2;
				}
				b++;
			};
			a++;
		};
		return hasil;
	}
	
	public String BacaUrutByIndex(String url,int xPos,int yPos){
		String Str = Baca(url);
		String hasil = "";
		int a=0;
		int b=0;
		java.util.List<Integer> Dataku = new java.util.ArrayList<Integer>();
		java.util.List<String> Pointku = new java.util.ArrayList<String>();
		
		for (String retval: Str.split(System.getProperty("line.separator"))){
			for (String retval2: Str.split(";")){
				if(b % 2 != 0){
					Dataku.add(Integer.parseInt(retval2));
				}
				else{
					Pointku.add(retval2);
				}
				b++;
			};
			a++;
		};
		
		//Fungsi Pengurutan Ranking
		int i,j,maks,alm;
		for(i=0;i<Dataku.size()-1;i++)
		{ 
			maks=Dataku.get(i);alm=i;
			for(j=i+1;j<Dataku.size();j++)
				if (maks<Dataku.get(j)) {
					maks=Dataku.get(j);alm =j;  
				}
			int temp=Dataku.get(i);
			Dataku.set(i,Dataku.get(alm));
			Dataku.set(alm,temp);
			
			String temp2=Pointku.get(i);
			Pointku.set(i,Pointku.get(alm));
			Pointku.set(alm,temp2);                                                    
		}
		
		for(i = 0;i<Dataku.size();i++){
			/*System.out.print(Pointku.get(i)+";");
			System.out.print(Dataku.get(i)+";\n");*/
			if(xPos == 0 && yPos == i){
				hasil = String.valueOf(Dataku.get(i));
			}
			else if(xPos == 1 && yPos == i){
				hasil = Pointku.get(i);
			}
		}
		return hasil;
	}
}
