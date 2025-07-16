package hello.typeconverter.controller;

import hello.typeconverter.converter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        // HTTP 요청 파라미터는 모두 문자로 처리된다.
        // 따라서 요청 파라미터를 자바에서 다른 타입으로 변환해서 사용하고 싶으면
        // 숫자로 변환하는 과정을 거쳐야 한다.
        String data = request.getParameter("data"); // 문자 타입 조회
        Integer intValue = Integer.valueOf(data); // 숫자 타입으로 변경
        System.out.println("intValue = " + intValue);
        return "ok";
    }


    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data, @ModelAttribute UserData user) {
        // HTTP 쿼리 스트링으로 전달하는 data=10 부분에서 10은 숫자 10이 아닌 문자 10이다
        // 스프링이 제공하는 @RequestPram 을 사용하면 이 문자 10을 숫자 10으로 편리하게 받을 수 있다.
        // 중간에서 스프링이 타입 변환을 해줬기 때문이다.
        // @ModelAttribute UserData user, @PathVariable("data") Integer data 에서도 확인할 수 있다.
        System.out.println("data = " + data);
        System.out.println("user.toString() = " + user.toString());
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        //http://localhost:8080/ip-port?ipPort=127.1.1.100:8080 호출
        System.out.println("ipPort.getIp() = " + ipPort.getIp());
        System.out.println("ipPort.getPort() = " + ipPort.getPort());
        return "ok";
    }


    @Data
    @AllArgsConstructor
    public static class UserData {
        private Integer num;
    }
}
