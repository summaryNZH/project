package com.project.demo.utils.api.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.project.demo.utils.api.json.helper.*;
import com.project.demo.utils.api.pojo.Money;
import com.project.demo.utils.api.pojo.date.DateRange;
import com.project.demo.utils.api.pojo.date.ShortDate;
import com.project.demo.utils.api.pojo.date.ShortTime;
import com.project.demo.utils.api.pojo.json.JsonView;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class JsonUtil {

    private JsonUtil() {
    }

    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        init(objectMapper);
    }

    public static void init(ObjectMapper objectMapper) {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
        module.addDeserializer(Date.class, new DateJsonDeserializer());
        module.addSerializer(Date.class, new DateJsonSerializer());
        module.addDeserializer(ShortDate.class, new ShortDateJsonDeserializer());
        module.addSerializer(ShortDate.class, new ShortDateJsonSerializer());
        module.addDeserializer(DateRange.class, new DateRangeJsonDeserializer());
        module.addSerializer(DateRange.class, new DateRangeJsonSerializer());
        module.addDeserializer(ShortTime.class, new ShortTimeJsonDeserializer());
        module.addSerializer(ShortTime.class, new ShortTimeJsonSerializer());
        module.addSerializer(Money.class,new MoneySerializer());
        module.addDeserializer(Money.class,new MoneyDeserializer());

        // sql
        module.addSerializer(java.sql.Date.class, new SqlDateJsonSerializer());
        module.addDeserializer(java.sql.Date.class, new SqlDateJsonDeserializer());
        module.addSerializer(Timestamp.class, new TimestampJsonSerializer());
        module.addDeserializer(Timestamp.class, new TimestampJsonDeserializer());
        module.addSerializer(java.sql.Time.class, new SqlTimeJsonSerializer());
        module.addDeserializer(java.sql.Time.class, new SqlTimeJsonDeserializer());

        GuavaModule guavaModule = new GuavaModule();
        objectMapper.registerModule(guavaModule);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);

        // 兼容 jackson 2.5 以下的版本, 对 Map.Entry 序列化做特殊处理
        module.addSerializer(Map.Entry.class, new JsonSerializer<Map.Entry>() {
            @Override
            public void serialize(Map.Entry entry, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                gen.writeObject(new KeyValue(entry.getKey(), entry.getValue()));
            }
        });

        objectMapper.registerModule(module);
    }

    public static ObjectMapper instance() {
        return objectMapper;
    }

    public static String of(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw Throwables.propagate(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T of(String json, Class<T> tClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T of(String json, TypeReference<T> reference) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readerFor(reference).readValue(json);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T of(String json, Class<T> clazz, Module module) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        try {
            objectMapper.registerModule(module);
            return objectMapper.readerFor(clazz).readValue(json);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> List<T> ofList(String json, Class<T> tClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class,
                tClass);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <K, V> Map<K, V> ofMap(String json, Class<K> keyClass, Class<V> valueClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        MapType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
        try {
            return objectMapper.readValue(json, mapType);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static String toJson(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Throwable t) {
            Throwables.propagate(t);
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("a", "b");
        map.put("c", "d");
        String json = toJson(new JsonView<>(0, "ok", map));
        Map<String, String> data = JsonUtil.of(json, new TypeReference<JsonView<Map<String, String>>>() {
        }).data;
        System.out.println(data);
    }

}
