package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import Utils.MemberMenuBar;

public class MemberHomeView extends JFrame {

    private JTextField movieNameField;
    private JTextField directorNameField;
    private JTextField actorNameField;
    private JTextField genreField;
    private JButton searchButton;
	private MemberMenuBar menuBar;

    public MemberHomeView() {
        // Set up the frame
        setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create menu bar
        menuBar = new MemberMenuBar(this);
        setJMenuBar(menuBar);


        // Create components
        movieNameField = new JTextField(40);
        directorNameField = new JTextField(20);
        actorNameField = new JTextField(20);
        genreField = new JTextField(20);
        searchButton = new JButton("검색");

        JLabel titleLabel = new JLabel("예매할 영화를 검색하세요", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times", Font.BOLD, 24));

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 4;
        panel.add(titleLabel, gc);

        // Movie Name
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        panel.add(new JLabel("영화명"), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 3;
        panel.add(movieNameField, gc);

        // Director Name
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        panel.add(new JLabel("감독명"), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        panel.add(directorNameField, gc);

        // Actor Name
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 1;
        panel.add(new JLabel("배우명"), gc);

        gc.gridx = 3;
        gc.gridy = 2;
        gc.gridwidth = 1;
        panel.add(actorNameField, gc);

        // Genre
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        panel.add(new JLabel("장르"), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        panel.add(genreField, gc);

        // Search Button
        gc.gridx = 2;
        gc.gridy = 3;
        gc.gridwidth = 2;
        panel.add(searchButton, gc);

        // Add panel to frame
        add(panel);
    }

    public String getMovieName() {
        return movieNameField.getText();
    }

    public String getDirectorName() {
        return directorNameField.getText();
    }

    public String getActorName() {
        return actorNameField.getText();
    }

    public String getGenre() {
        return genreField.getText();
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
}
