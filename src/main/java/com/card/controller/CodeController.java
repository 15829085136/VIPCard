package com.card.controller;

import com.card.util.SendMessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;


/**
 * Created by 69027 on 2017/11/2.
 */
@Controller
public class CodeController {
    @RequestMapping("/getCode")
    public String getCode(@RequestParam("phone")String phone) {
        long c=System.currentTimeMillis();
        String s=String.valueOf(c);
        String code=s.substring(s.length()-4,s.length());

        Jedis jedis = new Jedis("47.95.222.74",6379);
        jedis.set("phone",code);
        jedis.expire("phone",60);
        jedis.close();

        SendMessageUtil.sendMesaage(phone,code);

        return "register";

    }
}
