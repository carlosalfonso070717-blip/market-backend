package mx.edtecdesoftware.edu.mx.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/saludar")

public class HelloWordController {
    @GetMapping("/hola")
    public String saludar(){
        return "Hello World";
    }
}