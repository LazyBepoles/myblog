package cn.liang.service;

import cn.liang.pojo.Tag;

import java.util.List;

public interface TagsService {

    List<Tag> queryAllTags();

    Tag queryTagById(int id);

    Tag queryTagByName(String name);

    List<Tag> queryTagsByName(String name);

    List<Tag> queryTagsTop();

    List<Tag> queryTags(String tagsIds);

    int addTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(int id);

}
