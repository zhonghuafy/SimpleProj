//package org.fe.ek.test.proj.basic.config;
//
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import org.fe.ek.test.model.constant.SysConst;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @program: SimpleProj
// * @description: MybatisPlusConfig
// * @author: Wang Zhenhua
// * @create: 2020-02-26
// * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
// **/
//@Configuration
//public class MybatisPlusConfig {
//
//    /**
//     *   mybatis-plus page tool
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor page = new PaginationInterceptor();
//        page.setDialectType("mysql");
//        page.setLimit(SysConst.MAX_PAGE_SIZE);
//        return page;
//    }
//}
