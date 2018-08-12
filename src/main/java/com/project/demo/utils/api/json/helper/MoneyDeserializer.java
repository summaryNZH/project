/*
 * Copyright (c) 2017 wormpex.com. All Rights Reserved.
 */
package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.project.demo.utils.api.pojo.Currency;
import com.project.demo.utils.api.pojo.Money;


import java.io.IOException;
import java.math.BigDecimal;


/**
 * @author wenwucao Date: 17/2/5 Time: 下午3:07
 * @version \$Id$
 */
public class MoneyDeserializer extends JsonDeserializer<Money> {
    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        BigDecimal amount = node.get("amount").decimalValue();
        String currency = node.get("currency").asText();
        return Money.of(amount, Currency.valueOf(currency));
    }
}