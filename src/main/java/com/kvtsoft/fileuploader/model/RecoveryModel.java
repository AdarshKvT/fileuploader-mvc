package com.kvtsoft.fileuploader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class RecoveryModel {
    private String xmlId;
    private String permanentLink;
    private String successor;
    private String predecessor;
    private String generationId;
    private String validTo;
    private String validFrom;
    private String termPublicUrl;

    public RecoveryModel(String xmlId, String permanentLink, String successor, String predecessor, String generationId, String validTo, String validFrom, String termPublicUrl) {
        this.xmlId = xmlId;
        this.permanentLink = permanentLink;
        this.successor = successor;
        this.predecessor = predecessor;
        this.generationId = generationId;
        this.validTo = validTo;
        this.validFrom = validFrom;
        this.termPublicUrl = termPublicUrl;
    }

    public RecoveryModel() {
    }

    public String getXmlId() {
        return xmlId;
    }

    public void setXmlId(String xmlId) {
        this.xmlId = xmlId;
    }

    public String getPermanentLink() {
        return permanentLink;
    }

    public void setPermanentLink(String permanentLink) {
        this.permanentLink = permanentLink;
    }

    public String getSuccessor() {
        return successor;
    }

    public void setSuccessor(String successor) {
        this.successor = successor;
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }

    public String getGenerationId() {
        return generationId;
    }

    public void setGenerationId(String generationId) {
        this.generationId = generationId;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getTermPublicUrl() {
        return termPublicUrl;
    }

    public void setTermPublicUrl(String termPublicUrl) {
        this.termPublicUrl = termPublicUrl;
    }

    @Override
    public String toString() {
        return "ColumnNames{" +
                "xmlId='" + xmlId + '\'' +
                ", permanentLink='" + permanentLink + '\'' +
                ", successor='" + successor + '\'' +
                ", predecessor='" + predecessor + '\'' +
                ", generationId='" + generationId + '\'' +
                ", validTo='" + validTo + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", termPublicUrl='" + termPublicUrl + '\'' +
                '}';
    }
}
