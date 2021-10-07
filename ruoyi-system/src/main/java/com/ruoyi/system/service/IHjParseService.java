package com.ruoyi.system.service;

import com.xy.format.hbt212.exception.T212FormatException;

import java.io.IOException;
import java.text.ParseException;

/**
 * The interface Hj parse service.
 */
public interface IHjParseService {
    /**
     * Process.
     *
     * @param s the s
     */
    void process(String s) throws IOException, T212FormatException, ParseException;
}
