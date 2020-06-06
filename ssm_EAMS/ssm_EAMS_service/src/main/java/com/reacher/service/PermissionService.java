package com.reacher.service;

import com.reacher.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll() throws Exception;

    List<Permission> finAll() throws Exception;

    void save(Permission permission) throws Exception;
}
