package cn.liang.dao;

import cn.liang.pojo.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogTagMapper {

    @Select("select t.id id,t.name name from t_tag t,t_blog_tag tb where tb.blog_id = #{bid} and tb.tag_id = t.id")
    List<Tag> getTagsByBlogId(@Param("bid") int bid);

    @Delete("delete from t_blog_tag where blog_id = #{bid}")
    int deleteTags(@Param("bid") int bid);
}
