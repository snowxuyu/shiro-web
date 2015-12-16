package org.snowxuyu.shiro.dao;

import org.framework.basic.dao.BaseDao;
import org.snowxuyu.shiro.entity.Resources;
import org.snowxuyu.shiro.entity.Role;
import org.snowxuyu.shiro.entity.User;

import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
public interface UserDao extends BaseDao<User> {

    /**
     * 获取所有用户列表
     * @return
     */
    public List<User> listUser();

    /**
     * 根据角色获取用户
     * @param roleId
     * @return
     */
    public List<User> listByRole(String roleId);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    public User loadByUsername(String username);

    /**
     * 获取某个用户的所有资源
     * @param userId
     * @return
     */
    public List<Resources> listAllResources(String userId);

    /**
     * 获取某个用户的角色sn
     * @param userId
     * @return
     */
    public List<String> listRoleSnByUser(String userId);

    /**
     * 获取某个用户的所有角色
     * @return
     */
    public List<Role> listUserRole(String userId);


}
