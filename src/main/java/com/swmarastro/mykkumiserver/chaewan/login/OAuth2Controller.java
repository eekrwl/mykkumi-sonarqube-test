package com.swmarastro.mykkumiserver.chaewan.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OAuth2Controller {
    @GetMapping("/oauth2/kakao")
    public RedirectView oauth2Kakao(@RequestParam("code") String code) {
        String redirectUri = "http://localhost:8080/login/oauth2/code/kakao?code=" + code;
        return new RedirectView(redirectUri);
    }
}
