package Controller;

import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class MemberReserveController {
    private MemberReserveView view;
    private MemberReserveModel model;

    public MemberReserveController(MemberReserveView view, MemberReserveModel model, int movieId) {
        this.view = view;
        this.model = model;

        List<Map<String, String>> schedules = model.getScreeningSchedules(movieId);

        // Convert the schedule list to a map by theater
        Map<String, List<Map<String, String>>> scheduleMap = new HashMap<>();
        for (Map<String, String> schedule : schedules) {
            String theater = schedule.get("theater_name");
            if (!scheduleMap.containsKey(theater)) {
                scheduleMap.put(theater, new ArrayList<>());
            }
            scheduleMap.get(theater).add(schedule);
        }

        view.setSchedules(scheduleMap);

        view.addSelectSeatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSelectSeat();
            }
        });
    }

    private void handleSelectSeat() {
    	// TODO: 좌석 선택 뷰 전환 구현 
    	int scheduleId = model.getSelectedScheduleID(view.getSelectedSchedule());
    	List<Integer> seat = model.getSeatInfo(scheduleId);
    	
        MemberSeatModel seatModel = new MemberSeatModel(model.getConnection());
        MemberSeatView seatView = new MemberSeatView(seat.get(0), seat.get(1));
        MemberSeatController seatController = new MemberSeatController(seatView, seatModel, scheduleId);
        seatView.setVisible(true);
        view.dispose();
    }
}
