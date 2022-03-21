package cn.liang.service;

import cn.liang.dao.TagsMapper;
import cn.liang.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService{

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public List<Tag> queryAllTags() {
        List<Tag> listTag= tagsMapper.queryAllTags();
        return listTag;
    }

    @Transactional
    @Override
    public int addTag(Tag tag) {
        int result = tagsMapper.addTag(tag);
        return result;
    }

    @Override
    public Tag queryTagById(int id) {
        Tag queryResult = tagsMapper.queryTagById(id);
        return queryResult;
    }

    @Override
    public List<Tag> queryTags(String tagsIds) {
        List<Tag> tagList = tagsMapper.queryTags(tagsIds);
        return tagList;
    }

    @Override
    public List<Tag> queryTagsTop() {
        List<Tag> tagList = tagsMapper.queryTagsTop();
        return tagList;
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        int result = tagsMapper.updateTag(tag);
        return result;
    }

    @Transactional
    @Override
    public int deleteTag(int id) {
        int result = tagsMapper.deleteTag(id);
        return result;
    }

    @Override
    public Tag queryTagByName(String name) {
        Tag queryResult = tagsMapper.queryTagByName(name);
        return queryResult;
    }

    @Override
    public List<Tag> queryTagsByName(String name) {
        List<Tag> tagList = tagsMapper.queryTagsByName(name);
        return tagList;
    }
}
