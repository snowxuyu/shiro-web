package org.snowxuyu.shiro.dao;

import org.framework.basic.dao.BaseDao;
import org.snowxuyu.shiro.entity.Resources;
import org.snowxuyu.shiro.entity.Role;
import org.snowxuyu.shiro.entity.RoleResources;
import org.snowxuyu.shiro.entity.UserRole;

import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
public interface RoleDao extends BaseDao<Role> {
    /**
     * 获取所有角色
     * @return
     */
    public List<Role> listRole();

    /**
     * 根据uid和roleId获取用户角色
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole loadUserRole(String userId, String roleId);

    /**
     * 获取某个角色的资源
     * @param roleId
     * @return
     */
    public List<Resources> listRoleResource(String roleId);

    /**
     *
     * @param roleId
     * @param resourcesId
     * @return
     */
    public RoleResources loadRoleResources(String roleId, String resourcesId);


}
