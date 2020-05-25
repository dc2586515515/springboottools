package com.dc.websocket.payload;

import com.dc.websocket.model.Server;
import com.dc.websocket.payload.server.CpuVO;
import com.dc.websocket.payload.server.JvmVO;
import com.dc.websocket.payload.server.MemVO;
import com.dc.websocket.payload.server.SysFileVO;
import com.dc.websocket.payload.server.SysVO;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 服务器信息VO
 * </p>
 *
 * @package: com.dc.websocket.payload
 * @description: 服务器信息VO
 * @author: yangkai.shen
 * @date: Created in 2018-12-14 17:25
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class ServerVO {
    List<CpuVO> cpu = Lists.newArrayList();
    List<JvmVO> jvm = Lists.newArrayList();
    List<MemVO> mem = Lists.newArrayList();
    List<SysFileVO> sysFile = Lists.newArrayList();
    List<SysVO> sys = Lists.newArrayList();

    public ServerVO create(Server server) {
        cpu.add(CpuVO.create(server.getCpu()));
        jvm.add(JvmVO.create(server.getJvm()));
        mem.add(MemVO.create(server.getMem()));
        sysFile.add(SysFileVO.create(server.getSysFiles()));
        sys.add(SysVO.create(server.getSys()));
        return null;
    }
}
