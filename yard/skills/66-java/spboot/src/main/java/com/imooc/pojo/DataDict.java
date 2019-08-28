package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "data_dict")
public class DataDict {
    @Id
    private Integer id;

    /**
     * 数据字典类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 数据字典类型代码
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 数据键
     */
    private String ddkey;

    /**
     * 数据值
     */
    private String ddvalue;

    /**
     * 是否显示，1：显示；2：不显示
     */
    @Column(name = "is_show")
    private Integer isShow;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据字典类型名称
     *
     * @return type_name - 数据字典类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置数据字典类型名称
     *
     * @param typeName 数据字典类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取数据字典类型代码
     *
     * @return type_code - 数据字典类型代码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置数据字典类型代码
     *
     * @param typeCode 数据字典类型代码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取数据键
     *
     * @return ddkey - 数据键
     */
    public String getDdkey() {
        return ddkey;
    }

    /**
     * 设置数据键
     *
     * @param ddkey 数据键
     */
    public void setDdkey(String ddkey) {
        this.ddkey = ddkey;
    }

    /**
     * 获取数据值
     *
     * @return ddvalue - 数据值
     */
    public String getDdvalue() {
        return ddvalue;
    }

    /**
     * 设置数据值
     *
     * @param ddvalue 数据值
     */
    public void setDdvalue(String ddvalue) {
        this.ddvalue = ddvalue;
    }

    /**
     * 获取是否显示，1：显示；2：不显示
     *
     * @return is_show - 是否显示，1：显示；2：不显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示，1：显示；2：不显示
     *
     * @param isShow 是否显示，1：显示；2：不显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}