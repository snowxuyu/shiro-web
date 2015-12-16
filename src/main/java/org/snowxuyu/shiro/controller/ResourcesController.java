package org.snowxuyu.shiro.controller;

import org.framework.basic.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snowxuyu.shiro.service.ResourcesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by snow on 2015/11/21.
 */
@Controller
@RequestMapping("/resources")
public class ResourcesController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ResourcesService resourcesService;

    @RequestMapping(value = "/listResources", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity listResources() {
        logger.debug("=======>>resourcesController listresources");
        ResponseEntity resp = new ResponseEntity();
        resp = resourcesService.listResources();
        return resp;
    }
}
