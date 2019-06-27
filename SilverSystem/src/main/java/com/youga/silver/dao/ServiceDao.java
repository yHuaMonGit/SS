package com.youga.silver.dao;

import com.youga.silver.obj.ServiceBase;

import java.util.List;

public interface ServiceDao {


    public List<ServiceBase> getServiceFromType(String ulType);

    public ServiceBase getSingleService(String serviceId);
}
