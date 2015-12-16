package org.snowxuyu.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.framework.basic.constant.Constants;
import org.framework.basic.service.impl.BaseServiceImpl;
import org.framework.basic.system.BaseException;
import org.framework.basic.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snowxuyu.shiro.dao.RoleDao;
import org.snowxuyu.shiro.dao.RoleResourcesDao;
import org.snowxuyu.shiro.dao.UserRoleDao;
import org.snowxuyu.shiro.entity.Resources;
import org.snowxuyu.shiro.entity.Role;
import org.snowxuyu.shiro.entity.RoleResources;
import org.snowxuyu.shiro.entity.UserRole;
import org.snowxuyu.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private RoleResourcesDao roleResourcesDao;

    @Override
    public ResponseEntity addRole(Role role) {
        logger.debug("======>>addRole:{}", JSONObject.toJSONString(role));
        ResponseEntity resp = new ResponseEntity();
        try {
            int result = roleDao.insert(role);
            if (1 == result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("角色添加成功");
            } else {
                resp.setStatus(Constants.System.ERROR);
                resp.setMessage("角色添加失败");
                resp.setError("数据库操作失败");
            }
        } catch (BaseException e) {
            logger.debug(e.getMessage());
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("角色添加失败");
            resp.setError("数据库操作失败");
            resp.setData(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseEntity deleteRole(String roleId) {
        logger.debug("=======>> deleteRole"+roleId);
        ResponseEntity resp = new ResponseEntity();
        try {
            int result = roleDao.deleteById(roleId);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("角色删除成功！");
            }
        } catch (BaseException e) {
            logger.debug(e.getMessage(), e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("角色删除失败，数据库操作失败!");
            resp.setData(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseEntity loadRole(String roleId) {
        logger.debug("========>> loadRole"+roleId);
        ResponseEntity resp = new ResponseEntity();
        try {
            Role role = roleDao.getById(roleId);
            resp.setData(role);
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("获取角色成功");
        } catch (BaseException e) {
            logger.debug(e.getMessage(),e);
            resp.setData(e.getMessage());
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("角色获取失败");
        }
        return resp;
    }

    @Override
    public ResponseEntity updateRole(Role role) {
        logger.debug("=========>> updateRole{}", JSONObject.toJSONString(role));
        ResponseEntity resp = new ResponseEntity();
        try {
            int result = roleDao.update(role);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("角色更新成功");
            }
        } catch (BaseException e) {
            logger.debug(e.getMessage(), e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("角色更新失败");
            resp.setData(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseEntity listRole() {
        logger.debug("=========>>roleService listrole");
        ResponseEntity resp = new ResponseEntity();
        try {
            List<Role> roles = roleDao.listRole();
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setData(roles);
            resp.setMessage("角色列表查询成功");
        } catch (Exception e) {
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("角色列表查询失败");
            logger.debug(e.getMessage(), e);
        }
        logger.debug("roleService listRole resp"+resp);
        return resp;
    }

    @Override
    public ResponseEntity loadUserRole(String userId, String roleId) {
        logger.debug("========>> loadUserRole"+userId+"---"+roleId);
        ResponseEntity resp = new ResponseEntity();
        try {
            UserRole userRole = roleDao.loadUserRole(userId, roleId);
            resp.setData(userRole);
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("用户角色查询成功");
        } catch (BaseException e) {
            logger.debug(e.getMessage(), e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("用户角色获取失败");
            resp.setData(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseEntity addUserRole(String userId, String roleId) {
        logger.debug("=====>> addUserRole"+userId+"----"+roleId);
        ResponseEntity resp = new ResponseEntity();
        UserRole ur = roleDao.loadUserRole(userId, roleId);
        if (null == ur) {
            UserRole entity = new UserRole();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            try {
                int result = userRoleDao.insert(entity);
                if (1==result) {
                    resp.setStatus(Constants.System.SUCCESSS);
                    resp.setMessage("添加用户角色成功");
                } else {
                    resp.setStatus(Constants.System.ERROR);
                    resp.setMessage("添加用户角色失败");
                }
            } catch (BaseException e) {
                logger.debug(e.getMessage(), e);
                resp.setStatus(Constants.System.ERROR);
                resp.setMessage("添加用户角色失败，数据库操作异常");
                resp.setData(e.getMessage());
            }

        } else {
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("该用户角色已经存在");
        }
      return resp;
    }

    @Override
    public ResponseEntity deleteUserRole(String userId, String roleId) {
        logger.debug("=======>> deleteUserRole"+userId+"---"+roleId);
        ResponseEntity resp = new ResponseEntity();
        UserRole userRole = roleDao.loadUserRole(userId, roleId);
        if (null!=userRole) {
            try {
                int result = userRoleDao.deleteByPrimaryKey(userRole);
                if (1==result) {
                    resp.setStatus(Constants.System.SUCCESSS);
                    resp.setMessage("用户角色删除成功");
                } else {
                    resp.setStatus(Constants.System.ERROR);
                    resp.setMessage("用户角色删除失败，操作数据库异常");
                }
            } catch (BaseException e) {
                logger.debug(e.getMessage(),e);
                resp.setStatus(Constants.System.ERROR);
                resp.setMessage("数据库操作异常，用户角色删除失败");
                resp.setData(e.getMessage());
            }

        } else {
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("用户角色不存在");
        }
        return resp;
    }

    @Override
    public ResponseEntity addRoleResources(String roleId, String resourcesId) {
        logger.debug("=======>> addRoleResources:"+roleId+"---"+resourcesId);
        ResponseEntity resp = new ResponseEntity();
        RoleResources roleResources = roleDao.loadRoleResources(roleId, resourcesId);
        if (null== roleResources) {
            RoleResources entity = new RoleResources();
            entity.setRoleId(roleId);
            entity.setResourcesId(resourcesId);
            int result = roleResourcesDao.insert(entity);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("添加角色资源成功");
            } else {
                resp.setStatus(Constants.System.ERROR);
                resp.setMessage("角色资源添加失败");
            }
        } else {
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("该角色资源已经存在");
        }
        return resp;
    }

    @Override
    public ResponseEntity deleteRoleResources(String roleId, String resourcesId) {
        logger.debug("======>> deleteRoleResources:"+roleId+"---"+resourcesId);
        ResponseEntity resp = new ResponseEntity();
        RoleResources roleResources = roleDao.loadRoleResources(roleId, resourcesId);
        if(null!=roleResources) {
            int result = roleResourcesDao.deleteByPrimaryKey(roleResources);
            if (1==result) {
                resp.setStatus(Constants.System.SUCCESSS);
                resp.setMessage("角色资源删除成功");
            } else {
                resp.setStatus(Constants.System.ERROR);
                resp.setMessage("角色资源删除失败,数据库操作出错");
            }
        } else {
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("该角色资源不存在");
        }
        return resp;
    }

    @Override
    public ResponseEntity listRoleResources(String roleId) {
        logger.debug("=======>> listroleResourcrs"+roleId);
        ResponseEntity resp = new ResponseEntity();
        try {
            List<Resources> resourcesList = roleDao.listRoleResource(roleId);
            resp.setData(resourcesList);
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("获取角色资源列表成功");
        } catch (BaseException e) {
            logger.debug(e.getMessage(),e);
            resp.setStatus(Constants.System.ERROR);
            resp.setMessage("获取角色资源列表失败，数据库操作失败");
        }
        return resp;
    }

    @Override
    public ResponseEntity loadRoleResources(String roleId, String resourcesId) {
        logger.debug("=========>>loadRoleResources:"+roleId+"---"+resourcesId);
        ResponseEntity resp = new ResponseEntity();
        try {
            RoleResources roleResources = roleDao.loadRoleResources(roleId, resourcesId);
            resp.setData(roleResources);
            resp.setStatus(Constants.System.SUCCESSS);
            resp.setMessage("获取角色资源成功");
        } catch (BaseException e) {
            logger.debug(e.getMessage(), e);
            resp.setStatus(Constants.System.ERROR);
            resp.setData(e.getMessage());
            resp.setMessage("获取角色资源失败");
        }
        return resp;
    }


}
