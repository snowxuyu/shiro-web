package org.snowxuyu.shiro.service;

import org.framework.basic.service.BaseService;
import org.framework.basic.system.ResponseEntity;
import org.snowxuyu.shiro.entity.Resources;

/**
 * Created by snow on 2015/11/21.
 */
public interface ResourcesService extends BaseService<Resources> {
    ResponseEntity addResources(Resources resources);
    ResponseEntity deleteResources(String resourcesId);
    ResponseEntity updateResources(Resources resources);
    ResponseEntity loadResources(String resourcesId);
    ResponseEntity listResources();
}
