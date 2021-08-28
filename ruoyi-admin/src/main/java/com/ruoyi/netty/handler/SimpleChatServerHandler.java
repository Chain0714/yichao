/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ruoyi.netty.handler;

import com.alibaba.fastjson.JSON;
import com.ruoyi.netty.ChannelRepository;
import com.ruoyi.system.service.IHjParseService;
import com.xy.format.hbt212.core.T212Mapper;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * event handler to process receiving messages
 *
 * @author Jibeom Jung akka. Manty
 */
@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class SimpleChatServerHandler extends ChannelInboundHandlerAdapter {

    private final ChannelRepository channelRepository;

    @Autowired
    private IHjParseService hjParseService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Assert.notNull(this.channelRepository, "[Assertion failed] - ChannelRepository is required; it must not be null");

        ctx.fireChannelActive();
        if (log.isDebugEnabled()) {
            log.debug(ctx.channel().remoteAddress() + "");
        }
        String remoteAddress = ctx.channel().remoteAddress().toString();

//        ctx.writeAndFlush("Your remote address is " + remoteAddress + ".\r\n");

        if (log.isDebugEnabled()) {
            log.debug("Bound Channel Count is {}", this.channelRepository.size());
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String stringMessage = (String) msg;
        log.info("read message:{}", stringMessage);
        try {
            hjParseService.process(stringMessage+"\r\n");
        } catch (Exception e) {
            log.info("parse exception,msg:{},e:{}",msg, e);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }


}
