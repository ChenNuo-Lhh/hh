package com.baizhi.hh.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AliyunUploadUtil {
    /**
     * 阿里云开启
     */
    static OSS Front() {
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GCGfKpuAGnKA2VLjPwq";
        String accessKeySecret = "uib4XBK7MB84BwNHUq6OI1UODgctpm";
//        String bucketName = "yingxue-cn";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        return ossClient;
    }

    /**
     * 阿里云关闭
     */
    static void close(OSS ossClient) {
        ossClient.shutdown();
    }

    /**
     * 文件上传  阿里云
     * byte[]  数组上传
     * 参数:
     * picImg: MultipartFile 类型的文件
     * bucketName: 存储空间名
     */
    public static List byteUpload(MultipartFile picImg) {
        System.out.println("当前文件类型为:" + picImg.getContentType());

        byte[] bytes = null;
        try {
            bytes = picImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String bucketName = "yingxue-cn";
//        拼接name
        String route = new Date().getTime() + "-" + picImg.getOriginalFilename();

        String objectName = null;
        String intercept = null;
        if (picImg.getContentType().equals("image/jpeg") ||
                picImg.getContentType().equals("image/png") ||
                picImg.getContentType().equals("image/jpg")) {
            objectName = "yingxue/img/" + route;
        } else if (picImg.getContentType().equals("video/mp4")) {
            //如果文件为视频
            objectName = "yingxue/video/" + route;
            // 截取封面
            intercept = intercept(objectName);

        } else {
            objectName = "yingxue/" + route;
        }

        OSS ossClient = Front();

        // 上传Byte数组。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        close(ossClient);

        String url = "https://yingxue-cn.oss-cn-beijing.aliyuncs.com/" + objectName;

        String[] split = route.split("\\.");
        String name = "yingxue/video/cover/" + split[0] + ".jpg";
        if (picImg.getContentType().equals("video/mp4")) networkUpload(intercept, name);


        List list = new ArrayList();
        list.add(url);
        list.add("https://yingxue-cn.oss-cn-beijing.aliyuncs.com/" + name);

        return list;
    }

    /**
     * 文件上传  阿里云
     * 网路流上传
     * 参数:
     * signedUrl : 网络路径
     * name : 文件名
     */
    static void networkUpload(String signedUrl, String objectName) {
        // 存储空间名
        String bucketName = "yingxue-cn";
        // 创建OSSClient实例。
        OSS ossClient = Front();

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL(signedUrl).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);
        System.out.println("封面上传成功");
        // 关闭OSSClient。
        close(ossClient);
    }

    /**
     * 阿里云   文件删除
     * 参数:
     * objectName: 文件名
     * bucketName: 存储空间名
     */
    public static void deleteFile(String objectName) {
//        获取网络地址中的文件名
        String substring = objectName.substring(47);
        System.out.println(substring);

        String bucketName = "yingxue-cn";

        // 创建OSSClient实例。
        OSS ossClient = Front();
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, substring);

        // 关闭OSSClient。
        close(ossClient);
    }

    /**
     * 阿里云    视频截取封面
     * 参数:
     * objectName: 文件名
     * bucketName: 存储空间名
     */
    static String intercept(String objectName) {
        String bucketName = "yingxue-cn";
        // 创建OSSClient实例。
        OSS ossClient = Front();
        // 设置视频截帧操作。
        String style = "video/snapshot,t_1000,f_jpg,w_800,h_600";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println("网络地址为:" + signedUrl);

        // 关闭OSSClient。
        ossClient.shutdown();
        return signedUrl.toString();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String signedUrl = "https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/1606198581428-%E8%BD%A8%E9%81%93.MP4?Expires=1606199182&OSSAccessKeyId=LTAI4GGtfKpuGnKQA2LjPwAq&Signature=VHTh3rx%2BZA%2Ft2%2F%2F5yNlx0ryM13k%3D&x-oss-process=video%2Fsnapshot%2Ct_1000%2Cf_jpg%2Cw_800%2Ch_600";
        networkUpload(signedUrl, "aaaaa.bb");

    }
}