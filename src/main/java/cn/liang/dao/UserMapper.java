package cn.liang.dao;

import cn.liang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username} and password = #{password}")
    User checkUser(@Param("username") String username,@Param("password") String password);

    @Select("select * from t_user where id = #{id}")
    User getUser(@Param("id") int id);
}
