package com.ruoyi.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.sql.SqlUtil;

/**
 * 分页工具类
 * 
 * @author ruoyi
 */
public class PageUtils {
    /**
     * 设置请求分页数据
     */
    public static <T> Page<T> startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        // 创建 Page 对象
        Page<T> page = new Page<>(pageNum, pageSize);

        // 处理排序
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        if (StringUtils.isNotEmpty(orderBy)) {
            String[] orderBys = orderBy.split(",");
            for (String order : orderBys) {
                String[] parts = order.trim().split("\\s+");
                if (parts.length == 2) {
                    if ("asc".equalsIgnoreCase(parts[1])) {
                        page.addOrder(com.baomidou.mybatisplus.core.metadata.OrderItem.asc(parts[0]));
                    } else if ("desc".equalsIgnoreCase(parts[1])) {
                        page.addOrder(com.baomidou.mybatisplus.core.metadata.OrderItem.desc(parts[0]));
                    }
                }
            }
        }

        return page;
    }
}
