package com.cn.xt.mp.base.mybatisConfig;


import com.cn.xt.mp.base.dataSource.DatasourceConfig;
import com.cn.xt.mp.base.mybatis.wrapper.MyWrapperFactory;
import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mybatis的相关配置设置
 * @author Jfei
 *
 */

@AutoConfigureAfter(DatasourceConfig.class)
@MapperScan(value={"com.cn.xt.mp.feedback.dao.readonlydao"},sqlSessionFactoryRef="readonlySqlSessionFactory")
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "mybatis")
public class ReadonlyMybatisConfiguration {

	private static Log logger = LogFactory.getLog(ReadonlyMybatisConfiguration.class);


    //  配置类型别名
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage2;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
/*    @Value("${mybatis.readonly-mapper-locations}")
    private List<String> mapperLocations;*/

    private List<String> readonlyMapperLocations;

    public List<String> getReadonlyMapperLocations() {
        return readonlyMapperLocations;
    }

    @ConfigurationProperties(prefix = "mybatis")
    public void setReadonlyMapperLocations(List<String> readonlyMapperLocations) {
        this.readonlyMapperLocations = readonlyMapperLocations;
    }

    //  加载全局的配置文件
    @Value("${mybatis.config-location}")
    private String configLocation;


    //事务管理
    @Bean(name = "circleDataSourceTransactionManager ")
    public  DataSourceTransactionManager  annotationDrivenTransactionManager(@Qualifier("readonlyDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }


    @Bean(name = "readonlySqlSessionFactory")
    public SqlSessionFactory readonlySqlSessionFactory(@Qualifier("readonlyDataSource") DataSource datasource
            ,@Qualifier("pageHelper") PageHelper pageHelper
            ,@Qualifier("sqlPrintInterceptor")SqlPrintInterceptor sqlPrintInterceptor) throws Exception {
       try{
       //    VFS.addImplClass(SpringBootVFS.class);
         SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);

        // 读取配置
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage2);
       //设置mybatis-config.xml配置文件位置
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        //设置mapper.xml文件所在位置
           //设置mapper.xml文件所在位置
           List<Resource> rL = new ArrayList<>();
           System.out.println("momo");
           for (String mapperLocation : readonlyMapperLocations) {
               Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
               rL.addAll(Arrays.asList(resources));
               System.out.println("momo"+mapperLocation);
           }
/*        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
        List<Resource> rL= new ArrayList<>();
        rL.addAll(Arrays.asList(resource));*/
        sessionFactory.setMapperLocations(rL.toArray(new Resource[rL.size()]));
        sessionFactory.setObjectWrapperFactory(new MyWrapperFactory());
        //添加分页插件、打印sql插件
        Interceptor[] plugins = new Interceptor[]{pageHelper,sqlPrintInterceptor};
        sessionFactory.setPlugins(plugins);

        return sessionFactory.getObject();
    } catch (IOException e) {
        logger.error("mybatis resolver ws-mapper*xml is error",e);
        return null;
    } catch (Exception e) {
        logger.error("mybatis sqlSessionFactoryBean create error",e);
        return null;
    }
    }

    @Bean("circleSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("readonlySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
