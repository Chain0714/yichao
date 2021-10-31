package com.ruoyi.web.controller.pullution;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.crc.CRC16M;
import com.ruoyi.netty.ContextRepository;
import com.ruoyi.system.domain.dto.TestDeviceDto;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 因子信息Controller
 *
 * @author ruoyi
 * @date 2021-08-24
 */
@RestController
@RequestMapping("/pullution/device")
public class DeviceController extends BaseController {
    @Autowired
    private ContextRepository contextRepository;

    /**
     * 查询因子信息列表
     */
    @PreAuthorize("@ss.hasPermi('pullution:device:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        List<TestDeviceDto> collect = contextRepository.keyList().stream().map(str -> TestDeviceDto.builder().id(str).build()).collect(Collectors.toList());

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(collect);
        rspData.setTotal(collect.size());

        return rspData;
    }


    @PreAuthorize("@ss.hasPermi('pullution:device:test')")
    @PutMapping
    public AjaxResult edit(@RequestBody TestDeviceDto req) {
        try {
            String str = req.getMsg().replaceAll("\\s", "");
            logger.info("test msg:{}", str);
            byte[] sendBuf = CRC16M.getSendBuf(str);
            ChannelHandlerContext ctx = contextRepository.get(req.getId());
            ctx.writeAndFlush(Unpooled.copiedBuffer(sendBuf));
            return toAjax(true);
        } catch (Exception e) {
            logger.error("test msg exception:", e);
            return toAjax(false);
        }

    }

}
