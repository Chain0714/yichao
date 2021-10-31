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
package com.ruoyi.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Channel Repository using HashMap
 *
 * @author Jibeom Jung akka. Manty
 */
public class ContextRepository {
    private ConcurrentMap<String, ChannelHandlerContext> ctxCache = new ConcurrentHashMap<>();

    public void put(String key, ChannelHandlerContext value) {
        ctxCache.put(key, value);
    }

    public ChannelHandlerContext get(String key) {
        return ctxCache.get(key);
    }

    public void remove(String key) { this.ctxCache.remove(key); }

    public int size() {
        return this.ctxCache.size();
    }

    public List<String> keyList(){
        return this.ctxCache.keySet().stream().collect(Collectors.toList());
    }
}
