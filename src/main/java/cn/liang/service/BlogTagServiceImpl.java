package cn.liang.service;

import cn.liang.dao.BlogTagMapper;
import cn.liang.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService{

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public List<Tag> getTagsByBlogId(int bid) {
        List<Tag> tags = blogTagMapper.getTagsByBlogId(bid);
        return tags;
    }

    @Transactional
    @Override
    public int deleteTags(int bid) {
        int result = blogTagMapper.deleteTags(bid);
        return result;
    }
}
