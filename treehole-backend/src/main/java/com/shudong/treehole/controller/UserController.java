package com.shudong.treehole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shudong.treehole.entity.OpenIdJson;
import com.shudong.treehole.service.TestService;
import com.shudong.treehole.service.UserinfoService;
import com.shudong.treehole.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

/**
 * @program: treehole
 * @description: 登录类
 * @author: 王珺玉
 * @create: 2022-06-05 10:48
 **/

@RestController
@RequestMapping("login")
public class UserController {
    @Autowired
    TestService testService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserinfoService userinfoService;

    @RequestMapping("/hello")
    public String sayHello(){
        return testService.testHello();
    }

    private final String appID = "wx104a11bcd485d12c";
    private final String appSecret = "appSecret(altered)";


    @PostMapping("/login")
    @ResponseBody
    public Result getOpenId(String code,String name,String avatar) throws IOException {

        if (code == null || Objects.equals(code,"")){
            return new Result(10001,"参数为空",null);
        }
        String result = "";
        try{
            //请求微信服务器，用code换取openid。
            result = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid="+appID+"&secret="+appSecret+"&js_code=" +code + "&grant_type=authorization_code",String.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(90001,"请求微信服务器失败",null);
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        System.out.println(result);
        System.out.println(openIdJson.getOpenid());
        System.out.println(openIdJson.getSession_key());
        System.out.println(openIdJson.getErrcode());
        System.out.println(openIdJson.getErrmsg());

        if(openIdJson.getErrcode() == 0){
            Long uid = userinfoService.saveInfoAndGetID(name,avatar,openIdJson.getOpenid());
            if(uid == 0L) return new Result(80002,"用户创建失败",null);
            openIdJson.setOpenid(uid.toString());
        }
        return new Result(0,"成功",openIdJson);
    }
}
