package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import Utils.AdminMenuBar;

public class AdminHomeView extends JFrame {
	private AdminMenuBar menuBar;
	private JButton initButton;
	
	public AdminHomeView() {
        // Set up the frame
		setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create menu bar
        menuBar = new AdminMenuBar(this);
        setJMenuBar(menuBar);
        
        // Create components
        JLabel titleLabel = new JLabel("초기화");
        titleLabel.setFont(new Font("Times", Font.BOLD, 24));
        
        initButton = new JButton("데이터베이스 초기화");
        
        //Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.NONE;
        
        //title
        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(titleLabel, gc);
        
        //Button
        gc.gridx = 0;
        gc.gridy = 1;
        panel.add(initButton, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        panel.add(new JLabel(), gc);
        
        add(panel);
	}
	
	public void addInitButtonListener(ActionListener listener) {
        initButton.addActionListener(listener);
    }
}
