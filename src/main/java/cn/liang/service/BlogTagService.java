package cn.liang.service;

import cn.liang.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagService {

    List<Tag> getTagsByBlogId(int bid);

    int deleteTags(@Param("bid") int bid);
}
