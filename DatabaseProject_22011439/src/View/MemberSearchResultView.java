package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import Utils.MemberMenuBar;

public class MemberSearchResultView extends JFrame {
    private MemberMenuBar menuBar;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    private JLabel resultCountLabel;

    public MemberSearchResultView() {
        // Set up the frame
        setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create menu bar
        menuBar = new MemberMenuBar(this);
        setJMenuBar(menuBar);

        // Create components
        resultCountLabel = new JLabel("N개의 영화가 검색되었습니다.", SwingConstants.CENTER);
        resultCountLabel.setFont(new Font("Times", Font.BOLD, 18));
        
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(resultsPanel);

        // Layout components
        setLayout(new BorderLayout());
        add(resultCountLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setResults(List<Map<String, String>> movieDetailsList, ActionListener reservationListener) {
        resultsPanel.removeAll();
        resultCountLabel.setText(movieDetailsList.size() + "개의 영화가 검색되었습니다.");

        for (Map<String, String> movieDetails : movieDetailsList) {
            JPanel moviePanel = new JPanel(new BorderLayout());
            JPanel movieInfoPanel = new JPanel(new GridBagLayout());
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            GridBagConstraints gc = new GridBagConstraints();
            gc.insets = new Insets(10, 10, 10, 10);
            gc.anchor = GridBagConstraints.WEST;

            JLabel movieNameLabel = new JLabel(movieDetails.get("movie_name"));
            JLabel releaseDateLabel = new JLabel(movieDetails.get("release_date"));
            JLabel filmRatesLabel = new JLabel(movieDetails.get("film_rates"));
            JButton detailButton = new JButton("상세보기");
            JButton reserveButton = new JButton("예매하기");

            reserveButton.setActionCommand(String.valueOf(movieDetails.get("movie_id")));
            reserveButton.addActionListener(reservationListener);
            
            // Movie Info Panel
            gc.gridx = 0;
            gc.gridy = 0;
            movieInfoPanel.add(new JLabel("영화명"), gc);

            gc.gridx = 1;
            gc.gridy = 0;
            movieInfoPanel.add(movieNameLabel, gc);

            gc.gridx = 0;
            gc.gridy = 1;
            movieInfoPanel.add(new JLabel("등급"), gc);

            gc.gridx = 1;
            gc.gridy = 1;
            movieInfoPanel.add(filmRatesLabel, gc);

            gc.gridx = 0;
            gc.gridy = 2;
            movieInfoPanel.add(new JLabel("개봉일"), gc);

            gc.gridx = 1;
            gc.gridy = 2;
            movieInfoPanel.add(releaseDateLabel, gc);

            // Button Panel
            buttonPanel.add(detailButton);
            buttonPanel.add(reserveButton);

            moviePanel.add(movieInfoPanel, BorderLayout.WEST);
            moviePanel.add(buttonPanel, BorderLayout.EAST);

            resultsPanel.add(moviePanel);

            // Add a separator line
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            resultsPanel.add(separator);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
