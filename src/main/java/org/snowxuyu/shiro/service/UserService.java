package org.snowxuyu.shiro.service;

import org.framework.basic.service.BaseService;
import org.framework.basic.system.ResponseEntity;
import org.snowxuyu.shiro.dto.RequestUser;
import org.snowxuyu.shiro.entity.User;

import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
public interface UserService extends BaseService<User> {
	ResponseEntity addUser(RequestUser request);
	ResponseEntity deleteUser(String id);
	ResponseEntity updateUser(User user);
	ResponseEntity loadUser(String id);
	ResponseEntity loadByUsername(String username);
	ResponseEntity login(String username, String password);
	ResponseEntity list();
	ResponseEntity listByRole(String roleId);
	ResponseEntity listAllResource(String userId);
	ResponseEntity listRoleSnByUser(String userId);
	ResponseEntity listUserRole(String userId);
	ResponseEntity addUserList(List<RequestUser> list);
}
