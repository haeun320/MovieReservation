package Utils;

import javax.swing.*;
import View.*;
import Model.*;
import Controller.*;
import java.awt.event.*;

public class AdminMenuBar extends JMenuBar {
	private JMenu initializeMenu;
	private JMenu manipulateDataMenu;
	private JMenuItem insertDataMenuItem;
	private JMenuItem alterDataMenuItem;
	private JMenu viewTableMenu;
	private JMenu logoutMenu;
	private JFrame parentFrame;
	
	public AdminMenuBar(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		initializeMenu = new JMenu("초기화");
		initializeMenu.addMouseListener(new MouseAdapter(){
			 @Override
	            public void mouseClicked(MouseEvent e) {
	                System.out.println("Initialize clicked");
	            }
		});
		add(initializeMenu);
		
		manipulateDataMenu = new JMenu("데이터 조작");
		add(manipulateDataMenu);
		
		 insertDataMenuItem = new JMenuItem("데이터 입력");
        alterDataMenuItem = new JMenuItem("데이터 삭제 및 변경");

		manipulateDataMenu.add(insertDataMenuItem);
		insertDataMenuItem.addActionListener(new ActionListener() {
			 @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("View table clicked");
	            }
		});
		
		manipulateDataMenu.add(alterDataMenuItem);
		alterDataMenuItem.addActionListener(new ActionListener() {
			 @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Alter data menu item clicked");
	            }
		});
		
		viewTableMenu = new JMenu("테이블 조회");
		viewTableMenu.addMouseListener(new MouseAdapter(){
			 @Override
	            public void mouseClicked(MouseEvent e) {
	                System.out.println("View table menu clicked");
	            }
		});
		add(viewTableMenu);
		
		logoutMenu = new JMenu("로그아웃");
        logoutMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Logout clicked");
                handleLogout();
            }
        });
        add(logoutMenu);
	}
	
	private void handleLogout() {
    	parentFrame.dispose();
    	LoginView loginView = new LoginView();
    	LoginModel loginModel = new LoginModel();
    	LoginController loginController = new LoginController(loginModel, loginView);
    	loginView.setVisible(true);
    }
}
