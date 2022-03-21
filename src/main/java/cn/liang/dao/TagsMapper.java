package cn.liang.dao;

import cn.liang.pojo.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface TagsMapper {

  //    查询
  @Select("select * from t_tag")
  List<Tag> queryAllTags();

  @Select("select * from t_tag where id = #{id}")
  Tag queryTagById(@Param("id") int id);

  @Select("select * from t_tag where name = #{name}")
  Tag queryTagByName(@Param("name") String name);

  @Select("select * from t_tag where name like concat('%',#{name},'%')")
  List<Tag> queryTagsByName(@Param("name") String name);

  @Select("select t.id tid,t.name tname,ifnull(count(b.id),0) as count  from t_tag t left join t_blog_tag tb on t.id = tb.tag_id left join t_blog b on b.id = tb.blog_id group by t.id")
  @Results(value = {
          @Result(property = "id",column = "tid"),
          @Result(property = "name",column = "tname"),
          @Result(property = "blogs",column = "tid",javaType = ArrayList.class,
          many = @Many(select = "cn.liang.dao.BlogsMapper.queryBlogsByTagId"))
  })
  List<Tag> queryTagsTop();

  @Select(
            "<script>"
          + "select * "
          + "from t_tag "
          + "<where>"
          + "   <if test = \"tagsIds != null and tagsIds != ''\">"
          + "       <foreach item=\"idstr\" collection=\"tagsIds.split(',')\" open=\"(\" separator=\"OR\" close=\")\">"
          + "           id = #{idstr}"
          + "       </foreach>"
          + "   </if>"
          + "</where>"
          + "</script>")
  List<Tag> queryTags(String tagsIds);
  //    添加
  @Insert("insert into t_tag (name) values (#{name})")
  int addTag(Tag tag);
  //    更新
  @Update("update t_tag set name=#{name} where id = #{id}")
  int updateTag(Tag tag);
  //    删除
  @Delete("delete from t_tag where id = #{id}")
  int deleteTag(@Param("id") int id);

}
