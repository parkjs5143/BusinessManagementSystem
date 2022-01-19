package com.wish.test.controller;

import com.wish.test.ifs.CrudInterface;
import com.wish.test.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;


}
