package com.lanou.admin_info.controller;

import com.lanou.admin_info.service.AdminInfoService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/28.
 */
@Controller
public class AdminInfoController {

    @Resource
    private AdminInfoService service;



}
