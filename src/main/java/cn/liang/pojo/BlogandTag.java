package cn.liang.pojo;

public class BlogandTag {

    private int blogId;
    private int tagId;

    public BlogandTag() {
    }


    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "BlogandTag{" +
                "blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
