package org.ymx.sb_qr_code.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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

    @Value("${file.upload.path}")
    private String imgPath;

    @Override
    public String encodeImg(String format, String content, int width, int height, String logo) {
        String fileName = UUID.randomUUID() + "." + format;
        String text = content;
        if (ObjectUtils.isEmpty(content)) {
            text = "null";
        }
        if (!ObjectUtils.isEmpty(logo)) {
            logo = imgPath + logo;
        }
        String path = imgPath + fileName;
        try {
            ZXingUtil.encodeImg(path, format, text, width, height, logo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public String decodeImg(File file) {
        String content = null;
        try {
            content = ZXingUtil.decodeImg(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
