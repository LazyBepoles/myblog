package cn.liang.dao;

import cn.liang.pojo.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into t_comment (nickname,email,avatar,content,create_time,parent_id,blog_id,isadmin) " +
            "values (#{nickname},#{email},#{avatar},#{content},#{createTime},#{parentId},#{blogId},#{isadmin})")
    int addComment(Comment comment);

    @Select("select * from t_comment where parent_id = -1 and blog_id =#{id}")
    @Results(id = "BaseResultMap",value = {
            @Result(column="id",property="id"),
            @Result(column="nickname",property="nickname"),
            @Result(column="email",property="email"),
            @Result(column="avatar",property="avatar"),
            @Result(column="content",property="content"),
            @Result(column="create_time",property="createTime"),
            @Result(column="parent_id",property="parentId"),
            @Result(column="blog_id",property="blogId"),
            @Result(column="isadmin",property="isadmin"),
            @Result(column = "parent_id",property = "parentComment",javaType = Comment.class,
            one = @One(select = "cn.liang.dao.CommentMapper.queryParent")),
            @Result(property="replyComments",column="{id=id,bid=blog_id}",javaType = ArrayList.class,
            many = @Many(select = "cn.liang.dao.CommentMapper.queryReply"))
    })
    List<Comment> queryByBlogId(@Param("id") int id);

    @ResultMap("BaseResultMap")
    @Select("select * from t_comment where parent_id = #{id} and blog_id=#{bid}")
    List<Comment> queryReply(@Param("id") int id,@Param("bid") int bid);

    @Select("select * from t_comment where id = #{id}")
    Comment queryParent(@Param("id")int id);
}
