import javax.swing.*;
import java.awt.*;

public class belajarGUI{
	public static void main(String[] arg){
		//1. Create the frame.
		JFrame frame = new JFrame();
		frame.setTitle("Belajar GUI");
		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setLocation(300, 300);
		
        mainPanel.setBackground(new Color(107, 106, 104));
		JButton btn = new JButton("Hello World");
        btn.setPreferredSize(new Dimension(20, 20));
		btn.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
        mainPanel.add(btn);
        
        JLabel labelku = new JLabel("Hello World");
        labelku.setPreferredSize(new Dimension(20, 20));
		labelku.setForeground( new Color(0 ,191, 255));
        labelku.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        mainPanel.add(labelku);
        
        frame.add(mainPanel);
        //startButton.addActionListener(frame);
        
        //Display the window.
        frame.setSize(300, 300);
        frame.setVisible(true);
	}
}
