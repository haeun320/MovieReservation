package Utils;

import javax.swing.*;
import View.*;
import Model.*;
import Controller.*;
import Utils.MySQLConnector;
import java.awt.event.*;
import java.sql.Connection;

public class AdminMenuBar extends JMenuBar {
	private JMenu initializeMenu;
	private JMenu manipulateDataMenu;
	private JMenuItem insertDataMenuItem;
	private JMenuItem alterDataMenuItem;
	private JMenu viewTableMenu;
	private JMenu logoutMenu;
	private JFrame parentFrame;
	
	private Connection con;
	private MySQLConnector db;
	
	private Connection GetConnection() {
		db = new MySQLConnector();
		db.connectToDatabase("root", "1234");
		con = db.getConnection();
		return con;
	}
	
	public AdminMenuBar(JFrame parentFrame) {
		this.parentFrame = parentFrame;

		initializeMenu = new JMenu("초기화");
		initializeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Initialize menu clicked");
				handleHome();
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
				System.out.println("Insert data menu item clicked");
				handleInsertData();
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
		viewTableMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("View table menu clicked");
				handleGetTable();
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

	private void handleGetTable() {
		parentFrame.dispose();
		AdminGetTableView view = new AdminGetTableView();
		AdminGetTableModel model = new AdminGetTableModel(GetConnection());
		AdminGetTableController controller = new AdminGetTableController(view, model);
		view.setVisible(true);
	}
	
	private void handleHome() {
		parentFrame.dispose();
		AdminHomeView view = new AdminHomeView();
		AdminHomeModel model = new AdminHomeModel(GetConnection());
		AdminHomeController controller = new AdminHomeController(view, model);
		view.setVisible(true);
	}
	
	private void handleInsertData() {
		parentFrame.dispose();
		AdminInsertDataView view = new AdminInsertDataView();
		AdminInsertDataModel model = new AdminInsertDataModel(GetConnection());
		AdminInsertDataController controller = new AdminInsertDataController(view, model);
		view.setVisible(true);
	}
}
