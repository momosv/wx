package com.cn.xt.mp.base.mybatisConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 10:59
 **/
@Configuration
public class PageHelperConf {

    /**
     * 分页插件
     * @return
     */

    @Bean("pageHelper")
    public com.github.pagehelper.PageHelper pageHelper() {
        com.github.pagehelper.PageHelper pageHelper = new com.github.pagehelper.PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        p.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        p.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
        p.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
