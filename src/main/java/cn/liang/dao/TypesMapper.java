package cn.liang.dao;

import cn.liang.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface TypesMapper {

//    查询
    @Select("select * from t_type")
    List<Type> queryAllTypes();

    @Select("select t.id tid,t.name tname,ifnull(count(b.id),0) as count from t_type t left join t_blog b on t.id=b.type_id and b.ispublish = 1 group by t.id")
    @Results(value = {
            @Result(property = "id",column = "tid"),
            @Result(property = "name",column = "tname"),
            @Result(property = "blogs",column = "tid",javaType = ArrayList.class,
            many = @Many(select = "cn.liang.dao.BlogsMapper.queryBlogsByTypeId"))
    })
    List<Type> queryTypesTop();

    @Select("select * from t_type where name like concat('%',#{name},'%')")
    List<Type> queryTypesByName(@Param("name") String name);

    @Select("select * from t_type where id = #{id}")
    Type queryTypeById(@Param("id") int id);

    @Select("select * from t_type where name = #{name}")
    Type queryByName(@Param("name") String name);

    @Insert("insert into t_type (name) values (#{name})")
    int addType(Type type);

    @Update("update t_type set name=#{name} where id = #{id}")
    int updateType(Type type);

    @Delete("delete from t_type where id = #{id}")
    int deleteType(@Param("id") int id);

}
