package Controller;

import Model.MemberReserveModel;
import View.MemberReserveView;
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
        // 좌석 선택 로직 구현
        System.out.println("좌석 선택 버튼 클릭됨");
    }
}
