package org.snowxuyu.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.framework.basic.constant.Constants;
import org.framework.basic.service.impl.BaseServiceImpl;
import org.framework.basic.system.BaseException;
import org.framework.basic.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snowxuyu.shiro.dao.ResourcesDao;
import org.snowxuyu.shiro.entity.Resources;
import org.snowxuyu.shiro.service.ResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
@Service("resourcesService")
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ResourcesDao resourcesDao;


    @Override
    public ResponseEntity addResources(Resources resources) {
        logger.debug("=======>>addResources", JSONObject.toJSONString(resources));
        ResponseEntity resp = new ResponseEntity();
        try {
            int result = resourcesDao.insert(resources);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("添加资源成功");
            }
        } catch (BaseException e) {
            logger.debug(e.getMessage(), e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("添加资源失败");
        }
        return resp;
    }

    @Override
    public ResponseEntity deleteResources(String resourcesId) {
        logger.debug("========>> deleteResources"+resourcesId);
        ResponseEntity resp = new ResponseEntity();
        try {
            int result = resourcesDao.deleteById(resourcesId);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("删除资源成功");
            }
        } catch (BaseException e) {
            logger.debug(e.getMessage(),e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("删除资源失败，数据库操作出错");
        }
        return resp;
    }

    @Override
    public ResponseEntity updateResources(Resources resources) {
        logger.debug("=======>> updateResources", JSONObject.toJSONString(resources));
        ResponseEntity resp = new ResponseEntity();
        try {
            int result = resourcesDao.update(resources);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("更新资源成功");
            }
        } catch (BaseException e) {
            logger.debug(e.getMessage(), e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("更正资源失败，数据库操作失败");
            resp.setData(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseEntity loadResources(String resourcesId) {
        logger.debug("==========>> loadResources"+resourcesId);
        ResponseEntity resp = new ResponseEntity();
        try {
            Resources resources = resourcesDao.getById(resourcesId);
            resp.setData(resources);
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("获取资源成功");
        } catch (BaseException e) {
            logger.debug(e.getMessage(),e);
            resp.setMessage("获取资源失败， 数据库操作失败");
            resp.setStatus(Constants.System.ERROR);
            resp.setData(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseEntity listResources() {
        logger.debug("resourcesService listResources");
        ResponseEntity resp = new ResponseEntity();
        try {
            List<Resources> resourcesList = resourcesDao.listResources();
            resp.setData(resourcesList);
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("资源列表查询成功");
        } catch (Exception e) {
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("资源列表查询失败");
        }
        logger.debug("resourcesService listresources resp"+resp);
        return resp;
    }
}
