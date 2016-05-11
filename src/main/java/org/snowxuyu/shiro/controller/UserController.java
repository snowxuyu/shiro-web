package org.snowxuyu.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import org.framework.basic.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snowxuyu.shiro.dto.RequestUser;
import org.snowxuyu.shiro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by snow on 2015/11/21.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;


    @RequestMapping(value = "/listUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity listUser() {
        logger.debug("========>>userController listUser");
        ResponseEntity resp = new ResponseEntity();
        resp = userService.list();
        return resp;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addUser(RequestUser request) {
        logger.debug("========>>addUser{}", JSONObject.toJSONString(request));
        ResponseEntity resp = new ResponseEntity();
        resp = userService.addUser(request);
        return resp;
    }

    @RequestMapping(value = "/addUserList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addUserList() {
       // logger.debug("========>>addUser{}", JSONObject.toJSONString(list));
        ResponseEntity resp = new ResponseEntity();
        ArrayList<RequestUser> list = new ArrayList<RequestUser>();
        for (int i=0; i <100; i++) {
            RequestUser user = new RequestUser();
            user.setUserName("ewrewr"+i);
            user.setPassWord("123"+i);
            user.setNickName("赫克曼"+i);
            user.setStatus("1");
            list.add(user);
        }
        resp = userService.addUserList(list);
        return resp;
    }
    
}
