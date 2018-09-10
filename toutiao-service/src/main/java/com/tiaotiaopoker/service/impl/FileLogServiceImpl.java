package com.tiaotiaopoker.service.impl;

import com.tiaotiaopoker.StringUtils;
import com.tiaotiaopoker.common.Pagination;
import com.tiaotiaopoker.dao.FileLogMapper;
import com.tiaotiaopoker.pojo.FileLog;
import com.tiaotiaopoker.service.FileLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiekang on 2018/9/8.
 */
@Service
public class FileLogServiceImpl implements FileLogService {
    @Autowired
    private FileLogMapper fileLogMapper;
    @Value("${file.path}")
    private String        UPLOAD_FILE_PATH;
    @Value("${file.host}")
    private String        FILE_HOST;

    private void addFileLog(String fileName,
                            String savePath,
                            String fileUrl,
                            String userId) {
        FileLog fileLog = FileLog.genFileLog( "12", savePath, fileUrl, userId );
        fileLogMapper.insertSelective( fileLog );
    }

    @Override
    public Map<String, Object> saveFile(String userId,
                                        MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring( fileName.lastIndexOf( "." ) );
        String storeFileName = StringUtils.generateShortUUID() + ext;

        String savePath = getFileSavePath( userId, storeFileName );

        File newFile = new File( savePath );

        if( !newFile.getParentFile().exists() ) {
            newFile.getParentFile().mkdirs();
        }

        file.transferTo( newFile );

        String fileUrl = getFileUrl( userId, storeFileName );

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put( "fileUrl", fileUrl );

        addFileLog( fileName, savePath, fileUrl, userId );

        return resultMap;
    }

    private String getFileSavePath(String userId,
                                   String storeFileName) {
        return UPLOAD_FILE_PATH + userId + File.separator + storeFileName;
    }

    private String getFileUrl(String userId,
                              String storeFileName) {
        return FILE_HOST + userId + "/" + storeFileName;
    }
}
