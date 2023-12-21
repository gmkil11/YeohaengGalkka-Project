package gazua.controllers.mains;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gazua.entities.Member;
import gazua.entities.Room;
import gazua.models.room.config.RoomConfigInfoService;
import gazua.repositories.FileInfoRepository;
import gazua.repositories.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    public final RoomRepository repository;
    public final FileInfoRepository fileInfoRepository;
    public final RoomConfigInfoService roomConfigInfoService;


    @GetMapping("/{roomNum}")
    public String viewRoom(@PathVariable Long roomNum, Model model, HttpSession session) {
        Object member = session.getAttribute("loginMember");

        Room room = roomConfigInfoService.get(roomNum);

        System.out.println(member);
        System.out.println(room);
        model.addAttribute("room", room);
        model.addAttribute("member", member);

        return "front/room/room";
    }

    @PostMapping("/{roomNum}/pay")
    public String payRoomPost(@PathVariable Long roomNum, Model model, HttpServletRequest request, HttpSession session) {
        Room room = roomConfigInfoService.get(roomNum);
        Object member = session.getAttribute("loginMember");



        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String count = request.getParameter("count");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd");
        LocalDate startDateParse = LocalDate.parse(startDate, formatter);
        LocalDate endDateParse = LocalDate.parse(endDate, formatter);
        Period diff = Period.between(startDateParse, endDateParse);

        int daysDiff = diff.getDays();
        int price = Integer.parseInt(room.getRoomPr());
        int totalPr = daysDiff * price;

        DecimalFormat df = new DecimalFormat("###,###");
        String totalPrParse = df.format(totalPr);

        model.addAttribute("member", member);
        model.addAttribute("room", room);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("count", count);
        model.addAttribute("days", daysDiff);
        model.addAttribute("totalPr", totalPr);
        model.addAttribute("totalPrParse", totalPrParse);
        System.out.println(member );

        return "front/room/room_pay";
    }

}
