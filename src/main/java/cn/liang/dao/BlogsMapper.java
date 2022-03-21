package cn.liang.dao;

import cn.liang.pojo.Blog;
import cn.liang.pojo.Type;
import cn.liang.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface BlogsMapper {

    @Select("select b.id bid,b.title btitle,b.type_id btypeId,b.isrecommend bisrecommend,b.ispublish bispublish," +
            "b.update_time bupdateTime from t_blog b,t_type t where b.type_id = t.id")
    @Results(id = "queryResult",value = {
            @Result(property = "id",column = "bid"),
            @Result(property = "title",column = "btitle"),
            @Result(property = "isrecommend",column = "bisrecommend"),
            @Result(property = "ispublish",column = "bispublish"),
            @Result(property = "updateTime",column = "bupdateTime"),
            @Result(property = "type",column = "btypeId",javaType = Type.class,
                    one = @One(select = "cn.liang.dao.TypesMapper.queryTypeById")),
    })
    List<Blog> queryAllBlogs();

    @Select("select * from t_blog where ispublish = 1")
    @Results(id = "PublishedBlogs",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "description",column = "description"),
            @Result(property = "headerImage",column = "header_image"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "views",column = "views"),
            @Result(property = "isshare",column = "isshare"),
            @Result(property = "iscomment",column = "iscomment"),
            @Result(property = "isrecommend",column = "isrecommend"),
            @Result(property = "ispublish",column = "ispublish"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "typeId",column = "type_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "user",column = "user_id",javaType = User.class,
            one = @One(select = "cn.liang.dao.UserMapper.getUser")),
            @Result(property = "type",column = "type_id",javaType = Type.class,
            one = @One(select = "cn.liang.dao.TypesMapper.queryTypeById"))
    })
    List<Blog> queryAllPublishedBlogs();

    @Select("<script>" +
            "select b.id bid,b.title btitle,b.type_id btypeId,b.isrecommend bisrecommend,b.ispublish bispublish,b.update_time bupdateTime " +
            "from t_blog b,t_type t " +
            "where b.type_id = t.id" +
            "   <if test = \"title !=null and title != ''\">" +
            "       and b.title like concat('%',#{title},'%')" +
            "   </if>" +
            "   <if test = \"typeId != 0\">" +
            "       and t.id = #{typeId}" +
            "   </if>" +
            "   <if test = \"isrecommend == true\">" +
            "       and b.isrecommend = 1" +
            "   </if>" +
            "   <if test = \"ispublish == true\">" +
            "       and b.ispublish != 1" +
            "   </if>" +
            "</script>")
    @ResultMap("queryResult")
    List<Blog> searchBlogs(Blog blog);

    @Insert("insert into t_blog (title,content,header_image,flag,views,isshare,iscomment,isrecommend,ispublish," +
            "create_time,update_time,type_id,user_id,description) " +
            "values (#{title},#{content},#{headerImage},#{flag},#{views},#{isshare},#{iscomment},#{isrecommend},#{ispublish}," +
            "#{createTime},#{updateTime},#{typeId},#{userId},#{description})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int addBlog(Blog blog);

    @Insert("<script>" +
            "insert into t_blog_tag " +
            "(blog_id,tag_id) " +
            "values " +
            "   <foreach item=\"tag\" collection=\"tags\" separator=\",\" >" +
            "       (#{id},#{tag.id})" +
            "   </foreach>" +
            "</script>")
    int addBlogTag(Blog blog);

    @Select("select * from t_blog where id = #{id}")
    @Results(id = "AllBlogsResult",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "description",column = "description"),
            @Result(property = "headerImage",column = "header_image"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "views",column = "views"),
            @Result(property = "isshare",column = "isshare"),
            @Result(property = "iscomment",column = "iscomment"),
            @Result(property = "isrecommend",column = "isrecommend"),
            @Result(property = "ispublish",column = "ispublish"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "typeId",column = "type_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "user",column = "user_id",javaType = User.class,
                    one = @One(select = "cn.liang.dao.UserMapper.getUser")),
            @Result(property = "type",column = "type_id",javaType = Type.class,
                    one = @One(select = "cn.liang.dao.TypesMapper.queryTypeById")),
            @Result(property = "tags",column = "id",javaType = ArrayList.class,
            many = @Many(select = "cn.liang.dao.BlogTagMapper.getTagsByBlogId"))
    })
    Blog getBlogById(@Param("id") int id);

    @Update("update t_blog " +
            "set title=#{title},content=#{content},header_image=#{headerImage}," +
            "flag=#{flag},isshare=#{isshare},iscomment=#{iscomment}," +
            "isrecommend=#{isrecommend},ispublish=#{ispublish},update_time=#{updateTime},type_id=#{typeId},description=#{description} " +
            "where id=#{id}")
    int updateBlog(Blog blog);

    @Delete("delete from t_blog where id = #{id}")
    int deleteBlog(@Param("id") int id);

    @ResultMap("PublishedBlogs")
    @Select("select * from t_blog where ispublish = 1 and title like concat('%',#{query},'%') or description like concat('%',#{query},'%')")
    List<Blog> searchIndexBlogs(@Param("query") String query);

    @ResultMap("AllBlogsResult")
    @Select("select * from t_blog where type_id = #{id} and ispublish = 1")
    List<Blog> queryBlogsByTypeId(@Param("id") int id);

    @ResultMap("AllBlogsResult")
    @Select("select * from t_blog b,t_blog_tag tb where b.id = tb.blog_id and tb.tag_id = #{id} and b.ispublish = 1")
    List<Blog> queryBlogsByTagId(@Param("id") int id);

    @Select("select date_format(b.create_time,'%Y') as year from t_blog b group by year order by year desc")
    List<String> queryGroupYear();

    @Select("select * from t_blog b where date_format(b.create_time,'%Y') = #{year} and b.ispublish = 1")
    List<Blog> queryBlogsByYear(@Param("year") String year);

    @Select("select count(b.id) as count from t_blog b where b.ispublish = 1")
    int countBlogs();

}
