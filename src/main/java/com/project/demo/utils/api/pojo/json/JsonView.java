package com.project.demo.utils.api.pojo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wenwucao Date: 16/11/23 Time: 下午3:33
 * @version \$Id$
 */
public class JsonView<T> {

    public final int status;

    public final String message;

    public final T data;

    @JsonCreator
    public JsonView(@JsonProperty("status") int status, @JsonProperty("message") String message,
                    @JsonProperty("data") T data) {

        this.status = status;
        this.message = message;
        this.data = data;
    }

}
