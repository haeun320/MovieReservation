package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberMovieView extends JFrame {
    private JLabel[] labels;

    public MemberMovieView() {
        // Set up the frame
        setTitle("Movie Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        labels = new JLabel[9];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
        }

        // Layout components
        setLayout(new GridLayout(0, 1));
        for (JLabel label : labels) {
            add(label);
        }
    }

    public void setMovieDetails(String[] details) {
        String[] labelsText = {
            "영화명: ", "상영 시간: ", "등급: ", "감독: ", "설명: ", 
            "개봉일: ", "평점: ", "배우: ", "장르: "
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(labelsText[i] + details[i]);
        }
    }
}
