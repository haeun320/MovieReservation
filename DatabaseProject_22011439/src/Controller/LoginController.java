package Controller;

import Model.LoginModel;
import View.LoginView;
import Model.MemberHomeModel;
import View.MemberHomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the login button
        view.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        // Get the username and password from the view
        model.setID(view.getID());
        model.setPassword(view.getPassword());
        model.setUserType(view.getUserType());

        // Validate the user
        if (model.validateUser()) {
            // Navigate to the homepage
        	if (view.getUserType().equals("member")) {
        		MemberHomeModel memberModel = new MemberHomeModel(model.getConnection());
                MemberHomeView homeView = new MemberHomeView();
                MemberHomeController homeController = new MemberHomeController(homeView, memberModel);
                homeView.setVisible(true);
        	}
            view.dispose(); // Close the login view
        } else {
            view.setMessage("Invalid username or password.");
        }
    }

    public void showView() {
        view.setVisible(true);
    }
}
