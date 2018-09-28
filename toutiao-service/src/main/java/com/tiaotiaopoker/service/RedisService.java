package com.tiaotiaopoker.service;

import java.util.List;
import java.util.Set;

/**
 * Created by xiekang on 2018/9/25.
 */
public interface RedisService {
    boolean set(String key,
                Object value);

    boolean set(String key,
                Object value,
                Long expireTime);

    void remove(String... keys);

    void removePattern(String pattern);

    void remove(String key);

    boolean exists(String key);

    Object get(String key);

    void hmSet(String key,
               Object hashKey,
               Object value);

    Object hmGet(String key,
                 Object hashKey);

    void lPush(String k,
               Object v);

    List<Object> lRange(String k,
                        long l,
                        long l1);

    void add(String key,
             Object value);

    Set<Object> setMembers(String key);

    void zAdd(String key,
              Object value,
              double scoure);

    Set<Object> rangeByScore(String key,
                             double scoure,
                             double scoure1);

    Boolean expire(String key,
                   long timeout);
}
