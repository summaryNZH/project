/*
 * Copyright (c) 2017 wormpex.com. All Rights Reserved.
 */
package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.demo.utils.api.json.JsonUtil;
import com.project.demo.utils.api.pojo.Money;


import java.io.IOException;


/**
 * @author wenwucao Date: 17/2/4 Time: 下午11:59
 * @version \$Id$
 */
public class MoneySerializer extends JsonSerializer<Money> {

    @Override
    public void serialize(Money money, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeRawValue(money != null ? money.toScaleMoneyString() : "null");
    }

    public static void main(String args[]) {
        /*System.out.println(JsonUtil.toJson(Money.of("1212.21222")));
        System.out.println(JsonUtil.toJson(Money.of("1213")));
        System.out.println(JsonUtil.toJson(Money.of("1213.2")));
        System.out.println(JsonUtil.toJson(Money.of("1213.27")));*/


        String money= JsonUtil.toJson(Money.of("1213.27"));
        System.out.println(money);

        System.out.println(JsonUtil.of("",Money.class));
        System.out.println(JsonUtil.of(null,Money.class));
        System.out.println(JsonUtil.of(money,Money.class));


        //System.out.println(JsonUtil.of(JsonUtil.toJson(Money.of("1213.27")),Money.class));
    }
}