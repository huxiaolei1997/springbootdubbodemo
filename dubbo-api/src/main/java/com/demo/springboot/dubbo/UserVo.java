package com.demo.springboot.dubbo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年11月24日 17:03 胡晓磊 Exp $
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -3227656692163585209L;

    private Long id;

    private String userName;

    private Short age;
}
