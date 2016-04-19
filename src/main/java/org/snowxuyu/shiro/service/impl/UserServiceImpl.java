package org.snowxuyu.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.framework.basic.constant.Constants;
import org.framework.basic.service.impl.BaseServiceImpl;
import org.framework.basic.system.BaseException;
import org.framework.basic.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snowxuyu.shiro.dao.UserDao;
import org.snowxuyu.shiro.dto.RequestUser;
import org.snowxuyu.shiro.entity.Resources;
import org.snowxuyu.shiro.entity.Role;
import org.snowxuyu.shiro.entity.User;
import org.snowxuyu.shiro.service.UserService;
import org.snowxuyu.shiro.util.ShiroKit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserDao userDao;


	@Override
	public ResponseEntity addUser(RequestUser request) {
		logger.debug("=====>> addUser{}", JSONObject.toJSONString(request));
		ResponseEntity resp = new ResponseEntity();
		try {
			User user = new User();
			if(ShiroKit.isEmpty(request.getUserName())||ShiroKit.isEmpty(request.getPassWord())) {
				throw new BaseException("用户名或者密码不能为空！");
			}
			user.setUserName(request.getUserName());
			user.setNickName(request.getNickName());
			user.setStatus(request.getStatus());
			user.setCreateName("gaoguoxiang");
			user.setUpdateName("gaoguoxiang");
			user.setPassWord(ShiroKit.md5(request.getPassWord(),request.getUserName()));
			int result = userDao.insert(user);
			if (1== result) {
				resp.setMessage("用户添加成功");
				resp.setStatus(Constants.System.SUCCESSS);
			} else {
				resp.setStatus(Constants.System.ERROR);
				resp.setMessage("用户添加失败");
			}
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("添加数据失败，数据库异常");
			resp.setData(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResponseEntity deleteUser(String id) {
		logger.debug("======>> deleteUser"+id);
		ResponseEntity resp = new ResponseEntity();
		try {
			int result = userDao.deleteById(id);
			if (1==result) {
				resp.setStatus(Constants.System.SUCCESSS);
				resp.setMessage("用户删除成功");
			} else {
				resp.setStatus(Constants.System.ERROR);
				resp.setMessage("用户删除失败，数据库操作出错");
			}
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("数据删除失败，删除异常");
			resp.setData(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResponseEntity updateUser(User user) {
		logger.debug("======>>updateUser{}", JSONObject.toJSONString(user));
		ResponseEntity resp = new ResponseEntity();
		try {
			int result = userDao.update(user);
			if (1==result) {
				resp.setStatus(Constants.System.SUCCESSS);
				resp.setMessage("用户更新成功");
			} else {
				resp.setStatus(Constants.System.ERROR);
				resp.setMessage("用户更新失败,操作异常");
				logger.debug("update result:"+result);
			}
		} catch (BaseException e) {
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("用户更新失败，是操作操作出错");
			logger.debug(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResponseEntity loadUser(String id) {
		logger.debug("======>>loadUser"+id);
		ResponseEntity resp = new ResponseEntity();
		try {
			User user = userDao.getById(id);
			resp.setData(user);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setMessage("用户查询成功");
		} catch (BaseException e) {
			resp.setStatus(Constants.System.ERROR);
			resp.setData(e.getMessage());
			resp.setMessage("用户查询失败");
			logger.debug(e.getMessage(),e);
		}
		return resp;
	}

	@Override
	public ResponseEntity loadByUsername(String username) {
		logger.debug("======>> loadByUsername:"+username);
		ResponseEntity resp = new ResponseEntity();
		try {
			User user = userDao.loadByUsername(username);
			resp.setData(user);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setMessage("根据用户名查询用户成功");
		} catch (BaseException e) {
			resp.setStatus(Constants.System.ERROR);
			resp.setData(e.getMessage());
			resp.setMessage("根据用户名查询用户出错，数据库操作异常");
		}

		return resp;
	}

	@Override
	public ResponseEntity login(String username, String password) {
		logger.debug("======>> login:"+username+"----"+password);
		ResponseEntity resp = new ResponseEntity();
		try {
			User user = userDao.loadByUsername(username);
			if (null==user) {
				throw new UnknownAccountException("用户名或者密码出错");
			}
			if (!user.getPassWord().equals(ShiroKit.md5(password, username))) {
				throw new IncorrectCredentialsException("用户名或密码出错");
			}
			if (user.getStatus() != null && "0".equals(user.getStatus())) {
				throw new LockedAccountException("用户已经被锁定");
			}
			resp.setData(user);
			resp.setStatus(Constants.System.SUCCESSS);
		} catch (BaseException e) {
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("登陆出错，查询数据异常");
			resp.setData(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResponseEntity list() {
		logger.debug("=====>>userService list");
		ResponseEntity resp = new ResponseEntity();
		try {
			List<User> userList = userDao.listUser();
			resp.setData(userList);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setMessage("用户列表查询成功");
		} catch (BaseException e) {
			resp.setMessage("用户列表查询出错，数据库查询失败");
			resp.setStatus(Constants.System.ERROR);
			resp.setData(e.getMessage());
			logger.debug(e.getMessage(),e);
		}
		return resp;
	}

	@Override
	public ResponseEntity listByRole(String roleId) {
		logger.debug("=======>> userService listByRole"+roleId);
		ResponseEntity resp = new ResponseEntity();
		try {
			List<User> userList = userDao.listByRole(roleId);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setMessage("根据角色查询用户列表成功");
			resp.setData(userList);
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("跟换角色查询用户列表失败,数据库操作异常");
			resp.setData(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResponseEntity listAllResource(String userId) {
		logger.debug("====>> userService listAllResource:"+userId);
		ResponseEntity resp = new ResponseEntity();
		try {
			List<Resources> resourcesList = userDao.listAllResources(userId);
			resp.setData(resourcesList);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setMessage("查询用户资源成功");
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("用户资源查询失败，数据库查询出错");
			resp.setData(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResponseEntity listRoleSnByUser(String userId) {
		logger.debug("=====>>"+userId);
		ResponseEntity resp = new ResponseEntity();
		try {
			List<String> list = userDao.listRoleSnByUser(userId);
			resp.setData(list);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setMessage("用户角色SN查询成功");
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setData(e.getMessage());
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("用户角色SN查询失败，数据库查询出错");
		}
		return resp;
	}

	@Override
	public ResponseEntity listUserRole(String userId) {
		logger.debug("=====>>"+userId);
		ResponseEntity resp = new ResponseEntity();
		try {
			List<Role> roleList = userDao.listUserRole(userId);
			resp.setStatus(Constants.System.SUCCESSS);
			resp.setData(roleList);
			resp.setMessage("根据用户查询角色成功");
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setStatus(Constants.System.ERROR);
			resp.setData(e.getMessage());
			resp.setMessage("根据用户查询角色失败，数据库查询失败");
		}
		return resp;
	}

	@Override
	public ResponseEntity addUserList(List<RequestUser> list) {
		logger.debug("=====>> addUserList{}", JSONObject.toJSONString(list));
		ResponseEntity resp = new ResponseEntity();
		try {
			User user = null;
			List<User> users = new ArrayList<>();
			for (RequestUser request : list) {
				user = new User();
				user.setId(null);
				user.setCreateTime(null);
				if(ShiroKit.isEmpty(request.getUserName())||ShiroKit.isEmpty(request.getPassWord())) {
					throw new BaseException("用户名或者密码不能为空！");
				}
				user.setUserName(request.getUserName());
				user.setNickName(request.getNickName());
				user.setStatus(request.getStatus());
				user.setPassWord(ShiroKit.md5(request.getPassWord(),request.getUserName()));
				users.add(user);
			}

			int result = userDao.insertBatch(users);
			if (1== result) {
				resp.setMessage("用户添加成功");
				resp.setStatus(Constants.System.SUCCESSS);
			} else {
				resp.setStatus(Constants.System.ERROR);
				resp.setMessage("用户添加失败");
			}
		} catch (BaseException e) {
			logger.debug(e.getMessage(), e);
			resp.setStatus(Constants.System.ERROR);
			resp.setMessage("添加数据失败，数据库异常");
			resp.setData(e.getMessage());
		}
		return resp;
	}
}
