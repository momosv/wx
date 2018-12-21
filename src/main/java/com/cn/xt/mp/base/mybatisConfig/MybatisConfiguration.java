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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

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

@MapperScan(value={"com.cn.xt.mp.feedback.dao.dao","com.cn.xt.mp.base.mybatis.dao"})
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter(DatasourceConfig.class)
@ConfigurationProperties(prefix = "mybatis")
public class MybatisConfiguration implements TransactionManagementConfigurer {

        private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

        //  配置类型别名
        @Value("${mybatis.type-aliases-package}")
        private   String typeAliasesPackage;
/*        @Value("${mybatis.mapper-locations}")
        private  List<String> mapperLocations;*/
        //  加载全局的配置文件
        @Value("${mybatis.config-location}")
        private String configLocation;
        //--------------------上面是公共包---------------------


    private List<String> mapperLocations;

    public List<String> getMapperLocations() {
        return mapperLocations;
    }


    public void setMapperLocations(List<String> mapperLocations) {
        this.mapperLocations = mapperLocations;
    }


        @Autowired
        private DataSource dataSource;
        @Autowired
        private PageHelper pageHelper;

        // 提供SqlSeesion
        @Bean(name = "sqlSessionFactory")
        @Primary
        public SqlSessionFactory sqlSessionFactory() {
            try {
              //  VFS.addImplClass(SpringBootVFS.class);
                SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
                sessionFactoryBean.setDataSource(dataSource);
                // 读取配置 
                sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
                
                //设置mapper.xml文件所在位置
                List<Resource> rL = new ArrayList<>();
                for (String mapperLocation : mapperLocations) {
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
                    rL.addAll(Arrays.asList(resources));
                }
                sessionFactoryBean.setMapperLocations(rL.toArray(new Resource[rL.size()]));
                //设置mybatis-config.xml配置文件位置
                sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
                sessionFactoryBean.setObjectWrapperFactory(new MyWrapperFactory());
                //添加分页插件、打印sql插件
                Interceptor[] plugins = new Interceptor[]{pageHelper,sqlPrintInterceptor()};
                sessionFactoryBean.setPlugins(plugins);
                
                return sessionFactoryBean.getObject();
            } catch (IOException e) {
                logger.error("mybatis resolver ws-mapper*xml is error",e);
                return null;
            } catch (Exception e) {
                logger.error("mybatis sqlSessionFactoryBean create error",e);
                return null;
            }
        }

        @Bean
        @Primary
        public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        	return new SqlSessionTemplate(sqlSessionFactory);
        }
        
        //事务管理
        @Bean(name = "DataSourceTransactionManager ")
        @Primary
        public DataSourceTransactionManager  annotationDrivenTransactionManager() {
            return new DataSourceTransactionManager(dataSource);
        }


        //将要执行的sql进行日志打印(不想拦截，就把这方法注释掉)
        @Bean("sqlPrintInterceptor")
        public SqlPrintInterceptor sqlPrintInterceptor(){
        	return new SqlPrintInterceptor();
        }


}
