package com.dc.websocket.controller;

import cn.hutool.core.lang.Dict;
import com.dc.websocket.model.Server;
import com.dc.websocket.payload.ServerVO;
import com.dc.websocket.util.ServerUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一次加载通过页面请求获取服务器信息，
 * 很鸡肋 ，这个控制器跟websocket没关系，可以不要，相应也要去除页面中的请求，
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping
    public Dict serverInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(serverVO);
    }

}
