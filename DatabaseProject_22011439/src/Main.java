import Model.LoginModel;
import View.LoginView;
import Controller.LoginController;

//import View.MemberHomeView;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginModel model = new LoginModel();
                LoginView view = new LoginView();
                LoginController controller = new LoginController(model, view);
                controller.showView();
            }
        });
//    	
//    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                MemberHomeView homeView = new MemberHomeView();
//                homeView.setVisible(true);
//            }
//        });
    }
}
