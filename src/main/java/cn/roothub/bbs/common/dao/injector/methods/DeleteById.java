package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.enums.SqlMethod;
import cn.roothub.bbs.common.dao.metadata.TableInfo;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 ID 删除一条数据
 *
 * @Author: miansen.wang
 * @Date: 2019/11/19 15:21
 */
public class DeleteById extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod deleteById = SqlMethod.DELETE_BY_ID;
        String sqlScript = String.format(deleteById.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(), "#{id}");
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, deleteById.getMethod(), sqlSource, SqlCommandType.DELETE, String.class, null, Integer.class, new NoKeyGenerator(), tableInfo.getKeyProperty(), tableInfo.getKeyColumn());
    }
}
