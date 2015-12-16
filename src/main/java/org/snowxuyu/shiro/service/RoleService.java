package org.snowxuyu.shiro.service;

import org.framework.basic.service.BaseService;
import org.framework.basic.system.ResponseEntity;
import org.snowxuyu.shiro.entity.Role;

/**
 * Created by snow on 2015/11/21.
 */
public interface RoleService extends BaseService<Role> {

    ResponseEntity addRole(Role role);
    ResponseEntity deleteRole(String roleId);
    ResponseEntity loadRole(String roleId);
    ResponseEntity updateRole(Role role);
    ResponseEntity listRole();
    ResponseEntity loadUserRole(String userId, String roleId);

    ResponseEntity addUserRole(String userId, String roleId);

    ResponseEntity deleteUserRole(String userId, String roleId);

    ResponseEntity addRoleResources(String roleId, String resourcesId);

    ResponseEntity deleteRoleResources(String roleId, String resourcesId);

    ResponseEntity listRoleResources(String roleId);

    ResponseEntity loadRoleResources(String roleId, String resourcesId);
}
