package com.ruoyi.framework.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysRoleService;

import cn.dev33.satoken.stp.StpInterface;

/**
 * 自定义权限验证接口扩展
 * 
 * @author ruoyi
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 根据用户ID查询权限
        return new ArrayList<>(menuService.selectMenuPermsByUserId(Long.valueOf(loginId.toString())));
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 根据用户ID查询角色
        List<SysRole> roles = roleService.selectRolesByUserId(Long.valueOf(loginId.toString()));
        List<String> roleKeys = new ArrayList<>();
        for (SysRole role : roles) {
            roleKeys.add(role.getRoleKey());
        }
        return roleKeys;
    }
}
