package com.ruoyi.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author renchen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDeviceDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String msg;
}
