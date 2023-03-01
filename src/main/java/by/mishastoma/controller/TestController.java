package by.mishastoma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/")
    public String apache()
    {
        return "Apache Tomcat1.";
    }

    @GetMapping("/apache")
    public String test()
    {
        return "Apache Tomcat2.";
    }
}
