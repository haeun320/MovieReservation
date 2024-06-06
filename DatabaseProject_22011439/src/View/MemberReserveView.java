package View;

import javax.swing.*;

import Controller.MemberReserveController;
import Model.MemberReserveModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import Utils.MemberMenuBar;
import Utils.MySQLConnector;

public class MemberReserveView extends JFrame {
    private MemberMenuBar menuBar;
    private JList<String> theaterList;
    private JList<String> dateList;
    private JList<String> timeList;
    private DefaultListModel<String> theaterListModel;
    private DefaultListModel<String> dateListModel;
    private DefaultListModel<String> timeListModel;
    private JButton selectSeatButton;
    private Map<String, List<Map<String, String>>> schedules;

    public MemberReserveView() {
        // Set up the frame
        setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        menuBar = new MemberMenuBar(this);
        setJMenuBar(menuBar);

        // Create components
        theaterListModel = new DefaultListModel<>();
        dateListModel = new DefaultListModel<>();
        timeListModel = new DefaultListModel<>();

        theaterList = new JList<>(theaterListModel);
        dateList = new JList<>(dateListModel);
        timeList = new JList<>(timeListModel);

        dateList.setEnabled(false);
        timeList.setEnabled(false);

        selectSeatButton = new JButton("좌석 선택");
        selectSeatButton.setEnabled(false);

        JLabel theaterLabel = new JLabel("극장");
        JLabel dateLabel = new JLabel("날짜");
        JLabel timeLabel = new JLabel("시간");

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.fill = GridBagConstraints.BOTH;

        // Theater list
        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(theaterLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        panel.add(new JScrollPane(theaterList), gc);

        // Date list
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        panel.add(dateLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        panel.add(new JScrollPane(dateList), gc);

        // Time list
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        panel.add(timeLabel, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        panel.add(new JScrollPane(timeList), gc);

        // Select seat button
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 3;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.CENTER;
        panel.add(selectSeatButton, gc);

        add(panel);

        // Add listeners for selection changes
        theaterList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedTheater = theaterList.getSelectedValue();
                updateDateList(selectedTheater);
            }
        });

        dateList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedTheater = theaterList.getSelectedValue();
                String selectedDate = dateList.getSelectedValue();
                updateTimeList(selectedTheater, selectedDate);
            }
        });

        timeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (timeList.getSelectedValue() != null) {
                    selectSeatButton.setEnabled(true);
                } else {
                    selectSeatButton.setEnabled(false);
                }
            }
        });
    }

    public void setSchedules(Map<String, List<Map<String, String>>> schedules) {
        this.schedules = schedules;
        theaterListModel.clear();
        dateListModel.clear();
        timeListModel.clear();
        selectSeatButton.setEnabled(false);

        for (String theater : schedules.keySet()) {
            theaterListModel.addElement(theater);
        }
    }

    private void updateDateList(String theater) {
        dateListModel.clear();
        timeListModel.clear();
        selectSeatButton.setEnabled(false);
        dateList.setEnabled(true);
        timeList.setEnabled(false);

        Set<String> uniqueDates = new TreeSet<>();
        List<Map<String, String>> theaterSchedules = schedules.get(theater);

        for (Map<String, String> schedule : theaterSchedules) {
            uniqueDates.add(schedule.get("screening_start_date"));
        }

        for (String date : uniqueDates) {
            dateListModel.addElement(date);
        }
    }

    private void updateTimeList(String theater, String date) {
        timeListModel.clear();
        selectSeatButton.setEnabled(false);
        timeList.setEnabled(true);

        List<Map<String, String>> theaterSchedules = schedules.get(theater);

        for (Map<String, String> schedule : theaterSchedules) {
            if (schedule.get("screening_start_date").equals(date)) {
                timeListModel.addElement(schedule.get("screening_start_time"));
            }
        }
    }

    public void addSelectSeatListener(ActionListener listener) {
        selectSeatButton.addActionListener(listener);
    }
    

    
    public static void main(String[] args) {
		MemberReserveView view = new MemberReserveView();
		Connection con;
		MySQLConnector db = new MySQLConnector();
		db.connectToDatabase("user1", "user1");
		con = db.getConnection();
		MemberReserveModel model = new MemberReserveModel(con);
		MemberReserveController controller = new MemberReserveController(view, model, 1);
		view.setVisible(true);
	}
}
