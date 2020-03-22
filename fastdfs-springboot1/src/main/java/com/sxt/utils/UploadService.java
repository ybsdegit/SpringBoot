package com.sxt.utils;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: fastdfs-demo
 * @author: 雷哥
 * @create: 2020-01-03 10:05
 **/
@Component
public class UploadService {

    @Value("${fastdfs.tracker_servers}")
    private String tracker_servers;

    @Value("${fastdfs.connect_timeout_in_seconds}")
    private int connect_timeout;

    @Value("${fastdfs.network_timeout_in_seconds}")
    private int network_timeout;

    @Value("${fastdfs.charset}")
    private String charset;

    public Map<String,Object> upload(MultipartFile multipartFile) {
        if (multipartFile == null) {
            throw new RuntimeException("文件不能为空");
        }
        // 上传至fastDFS, 返回文件id
        String fileId = this.fdfsUpload(multipartFile);
        System.out.println(fileId);
        if (StringUtils.isEmpty(fileId)) {
            System.out.println("上传失败");
            throw  new RuntimeException("上传失败");
        }
        Map<String, Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","上传成功");
        map.put("fileId",fileId);
        return map;
    }


    /**
     * 上传至fastDFS
     * @param multipartFile
     * @return 文件id
     */
    private String fdfsUpload(MultipartFile multipartFile) {
        // 1. 初始化fastDFS的环境
        initFdfsConfig();
        // 2. 获取trackerClient服务
        TrackerClient trackerClient = new TrackerClient();
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3. 获取storage服务
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            // 4. 获取storageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storeStorage);
            // 5. 上传文件 (文件字节, 文件扩展名, )
            // 5.1 获取文件扩展名
            String originalFilename = multipartFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 5.2 上传
            NameValuePair[] meta_list=null;
            String fileId = storageClient1.upload_file1(multipartFile.getBytes(), extName,null);
            return fileId;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 初始化fastDFS的环境
     */
    private void initFdfsConfig() {
        try {
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_connect_timeout(connect_timeout);
            ClientGlobal.setG_network_timeout(network_timeout);
            ClientGlobal.setG_charset(charset);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
