import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class BelajarInternalFrame{
	public static void main(String []args){
		JFrame frame = new JFrame();
		frame.setTitle("Belajar Menggunakan Frame");
		frame.setSize(800,600);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		frame.setVisible(true);
		
		JDesktopPane desktop = new JDesktopPane();
		JInternalFrame inframe = new JInternalFrame();
		
		inframe.setTitle("Ini adalah internal frame");
		inframe.setSize(400,400);
		inframe.setClosable(true);
		inframe.setResizable(true);
		inframe.setMaximizable(true);
		
		inframe.add(new JLabel("Contoh Kotak Dialog"));
		
		desktop.add(inframe);
		inframe.setVisible(true);
		
		frame.setContentPane(desktop);
		frame.setVisible(true);
	} 
}
