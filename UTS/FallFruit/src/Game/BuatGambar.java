package Game;

import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

class BuatGambar extends JLabel {
	private BufferedImage bi, biFiltered;
	private double SkalaL,SkalaT;
    int w, h;
    private int indexGambar;
    private int indexSaveGambar;
    private String changeUrl;
    private String urlName;
    private int point;
    private int status;
    private int livesKurang;
    
    public BuatGambar(String url,double Scalelebar,double Scaletinggi) {
        try {
            bi = ImageIO.read(new File(url));
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            SkalaL = Scalelebar;
            SkalaT = Scaletinggi;
            urlName = url;
            
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                Graphics2D big = (Graphics2D) bi2.getGraphics();
                big.scale(Scalelebar,Scaletinggi);
                big.drawImage(bi, 0, 0, null);
                biFiltered = bi = bi2;
            }
        } catch (IOException e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
    }
    
    public void changeImage(String url){
		 try {
			bi = ImageIO.read(new File(url));
			BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D big = (Graphics2D) bi2.getGraphics();
            big.scale(SkalaL,SkalaT);
            big.drawImage(bi, 0, 0, null);
            biFiltered = bi = bi2; 
		} catch (IOException e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
        urlName = url;
        repaint();
	}

    public Dimension getPreferredSize() {
		return new Dimension((int) Math.round(w*SkalaL), (int) Math.round(h*SkalaT));
    }

    public void paint(Graphics g) {
		int wi = (int) Math.round(w*SkalaL);  
        int hi = (int) Math.round(h*SkalaT);
        //Rotate  
        //g.drawImage(biFiltered, 0, 0, wi, hi, wi, 0, 0, hi, null);
        g.drawImage(biFiltered, (getWidth()/2)-(wi/2) , 0 , null);
    }
    
    public void setIndexGambar(int i){
		indexGambar = i;
	}
	
	public int getIndexGambar(){
		return indexGambar;
	}
	
	public void setIndexSaveGambar(int i){
		indexSaveGambar = i;
	}
	
	public int getIndexSaveGambar(){
		return indexSaveGambar;
	}
	
    public void setChangeUrl(String x){
		changeUrl=x;
	}
	
	public String getChangeUrl(){
		return changeUrl;
	}
	
	public String getUrlName(){
		return urlName;
	}
	
	public void setPoint(int x){
		point=x;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setStatus(int x){
		status=x;
	}
	
	public int getStatus(){
		return status;
	}
	
	public void setLivesKurang(int x){
		livesKurang = x;
	}
	
	public int getLivesKurang(){
		return livesKurang;
	}
}
