package org.snowxuyu.shiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.framework.basic.entity.BaseEntity;

import javax.persistence.Table;

/**
 * Created by snow on 2015/11/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name="t_resources")
public class Resources extends BaseEntity {
    private String name;
    private String permission;
    private String url;
}
