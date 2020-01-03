package com.xlh.demo.springboot.dubbo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年11月24日 17:06 胡晓磊 Exp $
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -6194735483150717528L;

    private Long id;

    private String userName;

    private Short startAge;

    private Short endAge;
}
