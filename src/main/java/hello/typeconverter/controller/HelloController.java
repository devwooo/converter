package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        // HTTP 요청 파라미터는 모두 문자로 처리된다.
        String data = request.getParameter("data");
        Integer intVal = Integer.valueOf(data);
        System.out.println("intVal : " + intVal);
        return "Ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);
        //@RequestPram 어노테이션을 사용하면 스프링이 중간에서 타입을 변환해주었기 떄문에 바로 사용 가능하다.
        //@ModelAttribute, @PathVariable 모두 동일 하다.=
        return "Ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP= " + ipPort.getIp());
        System.out.println("ipPort PORT= " + ipPort.getPort());
        return "Ok";
    }

}
