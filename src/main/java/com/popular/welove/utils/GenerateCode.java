package com.popular.welove.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @Auther: lidesong
 * @Desc: 生成controller、service接口及实现、mapper及xml
 * @Date: 2020-03-23 14:44
 * @Description:
 */
public class GenerateCode {

    @Test
    public void generateCode() {
        //包名
        String packageName = "com.popular.welove";
        //生成路径
        String outputDir = "E:/welove/src/main/java";
        //作者
        String auth = "liuhang";
        //数据库驱动
        String db_driver = "com.mysql.jdbc.Driver";
        //数据链接
        String db_url = "jdbc:mysql://localhost:3306/popular?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        //数据库用户名
        String db_user = "root";
        //数据库密码
        String db_password = "admin";
        //表前缀
        String table_prefix = "tb_";
        //表名
        String tablename = "tb_user";
        //生成代码
        generateByTables(outputDir, auth, packageName, db_driver, db_url, db_user, db_password, table_prefix, tablename);
    }

    private void generateByTables(String outputDir, String auth, String packageName, String db_driver, String db_url, String db_user, String db_password, String table_prefix, String... tableNames) {
        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//AR模式
                .setEnableCache(false)//二级缓存
                .setAuthor(auth)//作者
                .setOutputDir(outputDir)
                .setBaseResultMap(true)//生成基本的resultMap
                .setServiceName("%sService")//设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
                .setFileOverride(true);//存在则覆盖
        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//数据库类型
                .setUrl(db_url)//数据链接
                .setUsername(db_user)//用户名
                .setPassword(db_password)//密码
                .setDriverName(db_driver);//数据库驱动
        //生成策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(table_prefix)//去除表前缀
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        //包配置
        PackageConfig pckInfo = new PackageConfig()
                .setParent(packageName)
                .setController("controller")
                .setService("service")
                .setMapper("mapper")
                .setXml("mapper")
                .setEntity("entity");
        //代码生成器
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(pckInfo).execute();
    }

}
