package com.bbxyard.mp.tenant.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    private String name;

}
