package com.bbxyard.sfb.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    private static final long serialVersionUID = 159265358979323L;

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String nickName;
    private String regTime;

}
