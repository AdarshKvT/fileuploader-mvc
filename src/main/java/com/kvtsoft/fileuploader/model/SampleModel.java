package com.kvtsoft.fileuploader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SampleModel {

    @Id
    @GeneratedValue
    private Integer xmlId;

    public SampleModel() {
    }

    public SampleModel(Integer xmlId) {
        this.xmlId = xmlId;
    }

    public Integer getXmlId() {
        return xmlId;
    }

    public void setXmlId(Integer xmlId) {
        this.xmlId = xmlId;
    }
}
