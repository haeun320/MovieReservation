package Controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import Model.AdminHomeModel;
import View.AdminHomeView;

public class AdminHomeController {
	private AdminHomeView view;
	private AdminHomeModel model;
	
	public AdminHomeController(AdminHomeView view, AdminHomeModel model) {
		 this.view = view;
		 this.model = model;
		 
		 view.addInitButtonListener(new ActionListener() {
			 @Override
	            public void actionPerformed(ActionEvent e) {
	                handleInit();
	            }
		 });
	}
	
	private void handleInit() {
		if(model.initializeDatabase()) {
			JOptionPane.showMessageDialog(view, "Success to init database");
		}
		else {
			JOptionPane.showMessageDialog(view, "Fail to init database");
		}
	}
	
}
