package com.ruoyi.web.controller.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.AjaxResult;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Sa-Token 测试控制器
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/test")
public class SaTokenTestController {
    /**
     * 测试 Sa-Token 状态
     */
    @GetMapping("/satoken")
    public AjaxResult testSaToken() {
        AjaxResult result = AjaxResult.success();

        try {
            // 检查是否登录
            boolean isLogin = StpUtil.isLogin();
            result.put("isLogin", isLogin);

            if (isLogin) {
                // 获取 token 值
                String tokenValue = StpUtil.getTokenValue();
                result.put("tokenValue", tokenValue);

                // 获取登录 ID
                Object loginId = StpUtil.getLoginId();
                result.put("loginId", loginId);

                // 获取 Session
                Object loginUser = StpUtil.getSession().get("loginUser");
                result.put("hasLoginUser", loginUser != null);

                // Token 详细信息
                result.put("tokenInfo", StpUtil.getTokenInfo());
            } else {
                result.put("message", "未登录");
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("errorClass", e.getClass().getName());
        }

        return result;
    }
}
