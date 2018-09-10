package com.tiaotiaopoker.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/8.
 */
public interface FileLogService {
    Map<String, Object> saveFile(String userId,
                                 MultipartFile file) throws IOException;
}
