package uz.pdp.cinema_room.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @GetMapping("api/login")
//    public String get() {
//        return "login";
//    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/courses")
    public String getCourses() {
        return "courses";
    }
}