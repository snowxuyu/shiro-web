package org.snowxuyu.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import org.framework.basic.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snowxuyu.shiro.dto.RequestRole;
import org.snowxuyu.shiro.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by snow on 2015/11/21.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/listRole", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity listRole() {
        logger.debug("=======>>rolecontroller");
        ResponseEntity resp = new ResponseEntity();
        resp = roleService.listRole();
        return resp;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResponseEntity addRole(RequestRole request) {
        logger.debug("========>>addRole{}", JSONObject.toJSONString(request));
        ResponseEntity resp = new ResponseEntity();
        //TODO
        return resp;
    }
}
