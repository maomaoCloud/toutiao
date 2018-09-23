package com.tiaotiaopoker.service;

import java.util.Map;

/**
 * Created by xiekang on 2018/9/23.
 */
public interface SearchService {
    Map<String, Object> searchForMatch(String userId,
                                       String kw);
}
