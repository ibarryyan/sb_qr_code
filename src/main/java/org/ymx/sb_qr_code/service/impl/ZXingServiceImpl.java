package org.ymx.sb_qr_code.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ymx.sb_qr_code.service.ZXingService;
import org.ymx.sb_qr_code.utils.ZXingUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @desc: Service文件处理
 * @author: YanMingXin
 * @create: 2022/6/2-19:22
 **/
@Service
public class ZXingServiceImpl implements ZXingService {

    @Value("${img.path}")
    private String imgPath;

    /**
     * 上传地址
     */
    @Value("${file.upload.path}")
    private String filePath;

    @Override
    public String encodeImg(String format, String content, int width, int height, String logo) {
        String path = imgPath + UUID.randomUUID();
        try {
            ZXingUtil.encodeImg(path, "jpg", content, width, height, logo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path + ".jpg";
    }

    @Override
    public String decodeImg(File file) {
        return null;
    }

}
