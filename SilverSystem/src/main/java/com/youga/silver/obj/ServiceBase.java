package com.youga.silver.obj;

public class ServiceBase {

    String serviceId = null;
    String serviceName = null;
    String servicePrice = null;
    String stander = null;
    String family = null;


    public ServiceBase(){}

    public ServiceBase(String serviceId, String serviceName, String servicePrice, String family) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.family = family;
    }


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getStander() {
        return stander;
    }

    public void setStander(String stander) {
        this.stander = stander;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
