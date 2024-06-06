package Utils;

import javax.swing.*;
import View.*;
import Model.*;
import Controller.*;
import java.awt.event.*;
import java.sql.Connection;

public class MemberMenuBar extends JMenuBar {
    private JMenu movieReservationMenu;
    private JMenu reservationHistoryMenu;
    private JMenu logoutMenu;
    private JFrame parentFrame;
    
    private Connection con;
	private MySQLConnector db;
	
	private Connection GetConnection() {
		db = new MySQLConnector();
		db.connectToDatabase("user1", "user1");
		con = db.getConnection();
		return con;
	}
    public MemberMenuBar(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        movieReservationMenu = new JMenu("영화예매");
        movieReservationMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Movie reservation clicked");
                handleHome();
            }
        });
        add(movieReservationMenu);

        reservationHistoryMenu = new JMenu("예매내역");
        reservationHistoryMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Reservation history clicked");
            }
        });
        add(reservationHistoryMenu);

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
    
    private void handleHome() {
    	parentFrame.dispose();
    	MemberHomeView view = new MemberHomeView();
    	MemberHomeModel model = new MemberHomeModel(GetConnection());
    	MemberHomeController controller = new MemberHomeController(view, model);
    	view.setVisible(true);
    }
}
