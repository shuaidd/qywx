package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-13 17:32
 **/
@XmlRootElement(name = "xml")
public class UnitEventData extends BaseEventData {

    @XmlElement(name = "Id")
    private Integer id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "ParentId")
    private Integer parentId;

    @XmlElement(name = "Order")
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
