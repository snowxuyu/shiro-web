package org.snowxuyu.shiro.dao;

import org.framework.basic.dao.BaseDao;
import org.snowxuyu.shiro.entity.Resources;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by snow on 2015/11/21.
 */
public interface ResourcesDao extends BaseDao<Resources> {

    /**
     * 获取所有资源
     * @return
     */
    public List<Resources> listResources();
}
