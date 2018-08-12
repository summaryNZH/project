/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package com.project.demo.utils.api.pojo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author wenwucao Date: 10/29/16 Time: 11:53 AM
 * @version \$Id$
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = -6781577186340650624L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}