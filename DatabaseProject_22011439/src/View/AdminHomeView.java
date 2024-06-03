package View;

import javax.swing.*;
import java.awt.*;
import Utils.AdminMenuBar;

public class AdminHomeView extends JFrame {
	private AdminMenuBar menuBar;
	
	public AdminHomeView() {
        // Set up the frame
		setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create menu bar
        menuBar = new AdminMenuBar(this);
        setJMenuBar(menuBar);
	}
}
