package com.dc.websocket.payload.server;

import com.dc.websocket.model.server.Mem;
import com.dc.websocket.payload.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 內存相关信息实体VO
 * </p>
 *
 * @package: com.dc.websocket.payload.server
 * @description: 內存相关信息实体VO
 * @author: yangkai.shen
 * @date: Created in 2018-12-14 17:28
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class MemVO {
    List<KV> data = Lists.newArrayList();

    public static MemVO create(Mem mem) {
        MemVO vo = new MemVO();
        vo.data.add(new KV("内存总量", mem.getTotal() + "G"));
        vo.data.add(new KV("已用内存", mem.getUsed() + "G"));
        vo.data.add(new KV("剩余内存", mem.getFree() + "G"));
        vo.data.add(new KV("使用率", mem.getUsage() + "%"));
        return vo;
    }
}