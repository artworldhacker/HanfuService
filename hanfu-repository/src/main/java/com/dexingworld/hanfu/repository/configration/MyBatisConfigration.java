package com.dexingworld.hanfu.repository.configration;

import com.dexingworld.hanfu.repository.MyBatisRepository;
import com.dexingworld.hanfu.repository.properties.DataSourceProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.io.IOException;

/**
 * Created by wangpeng on 2016/10/8.
 */
@Configuration
@MapperScan(basePackages = { "com.dexingworld.hanfu.repository" }, annotationClass = MyBatisRepository.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfigration implements TransactionManagementConfigurer {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDefaultAutoCommit(dataSourceProperties.isDefaultAutoCommit());
        dataSource.setMaxActive(dataSourceProperties.getMaxActive());
        dataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
        dataSource.setMinIdle(dataSourceProperties.getMinIdle());
        dataSource.setMaxWait(dataSourceProperties.getMaxWait());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean getSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(getDataSource());
        sqlSessionFactory.setTypeAliasesPackage("com.dexingworld.hanfu.repository.entity");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:configuration.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/*Mapper.xml"));
        return sqlSessionFactory;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }



    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
