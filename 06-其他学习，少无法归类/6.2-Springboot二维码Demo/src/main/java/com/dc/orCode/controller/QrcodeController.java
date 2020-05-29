package com.dc.orCode.controller;

import com.dc.orCode.util.QRCodeUtil;
import com.google.zxing.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 二维码调用前端控制器
 * @Author DC
 * @Date 2020-05-29
 */
@RequestMapping("/Qrcode")
@RestController
public class QrcodeController {

    /**
     * 生成二维码
     */
    @GetMapping
    public void productcode() {

        /**
         * 访问http://localhost:8080/Qrcode生成二维码
         */

        //文字
        // QRCodeUtil.zxingCodeCreate("测试", "G:/DeskBook/Picture/",
        //         500, "G:/DeskBook/Picture/5.jpg");
        //
        // //网站
        // QRCodeUtil.zxingCodeCreate("http://36.7.135.171:10081/outer-web", "G:/DeskBook/Picture/",
        //         500, "G:/DeskBook/Picture/5.jpg");
        //
        // //图片
        // QRCodeUtil.zxingCodeCreate("http://36.7.135.172:38130/out-web/file/00028f97-d46f-429d-b75a-b61b09976927.jpg",
        //         "G:/DeskBook/Picture/", 500, "G:/DeskBook/Picture/5.jpg");

        //app下载链接
        QRCodeUtil.zxingCodeCreate("http://36.7.135.172:38130/zhwlapp/apk/gltApp.apk",
                "G:/DeskBook/Picture/", 500, "G:/DeskBook/Picture/5.jpg");
    }


    /**
     * 解析二维码
     */
    @GetMapping("/test")
    public void analysiscode() {
        Result result = QRCodeUtil.zxingCodeAnalyze("D:/voice/picture/2018/759.jpg");
        System.err.println("二维码解析内容：" + result.toString());
    }

}
