import javax.swing.*;
import java.awt.event.*;

public class DemoSwingMenu{
	public static void main(String[] arg){
		JFrame fr = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		
		JMenuItem miFileNew = new JMenuItem("Menu");
		JMenuItem miFileOpen = new JMenuItem("Open");
		
		JMenu miFileRencent = new JMenu("Rencent");
		int jumSubmenu = 3;
		for(int i=0;i<jumSubmenu;i++){
			miFileRencent.add(new JMenuItem("File "+(i+1)));
		}
		
		menuFile.add(miFileNew);
		menuFile.add(miFileOpen);
		
		menuFile.addSeparator();
		menuFile.add(miFileRencent);
		
		menuBar.add(menuFile);
		
		fr.setJMenuBar(menuBar);
		
		final JPopupMenu pop = new JPopupMenu();
		JMenuItem miCut = new JMenuItem("Cut");
		JMenuItem miCopy = new JMenuItem("Copy");
		 
		pop.add(miCut);
		pop.add(miCopy);
		
		JPanel panel = new JPanel();
		fr.getContentPane().add(panel);
		
		panel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getButton() != MouseEvent.BUTTON3){
					return;
				}
				pop.show(
				e.getComponent(),
				e.getX(),
				e.getY()
				);
			}
		});
		
		fr.setSize(400,400);
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
	}
}
