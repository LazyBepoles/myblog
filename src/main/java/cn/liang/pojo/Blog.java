package cn.liang.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

  private int id;
  private String title;
  private String content;
  private String headerImage;
  private String flag;
  private int views;
  private boolean isshare;
  private boolean iscomment;
  private boolean isrecommend;
  private boolean ispublish;
  private Date createTime;
  private Date updateTime;
  private String description;
  private int typeId;
  private int userId;

  private User user;

  private Type type;

  private String tagsIds;

  private List<Tag> tags = new ArrayList<>();

  private List<Comment> comments = new ArrayList<>();

  public Blog() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getHeaderImage() {
    return headerImage;
  }

  public void setHeaderImage(String headerImage) {
    this.headerImage = headerImage;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public int getViews() {
    return views;
  }

  public void setViews(int views) {
    this.views = views;
  }

  public boolean isIsshare() {
    return isshare;
  }

  public void setIsshare(boolean isshare) {
    this.isshare = isshare;
  }

  public boolean isIscomment() {
    return iscomment;
  }

  public void setIscomment(boolean iscomment) {
    this.iscomment = iscomment;
  }

  public boolean isIsrecommend() {
    return isrecommend;
  }

  public void setIsrecommend(boolean isrecommend) {
    this.isrecommend = isrecommend;
  }

  public boolean isIspublish() {
    return ispublish;
  }

  public void setIspublish(boolean ispublish) {
    this.ispublish = ispublish;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getTagsIds() {
    return tagsIds;
  }

  public void setTagsIds(String tagsIds) {
    this.tagsIds = tagsIds;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Blog{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", headerImage='" + headerImage + '\'' +
            ", flag='" + flag + '\'' +
            ", views=" + views +
            ", isshare=" + isshare +
            ", iscomment=" + iscomment +
            ", isrecommend=" + isrecommend +
            ", ispublish=" + ispublish +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", description='" + description + '\'' +
            ", typeId=" + typeId +
            ", userId=" + userId +
            ", user=" + user +
            ", type=" + type +
            ", tagsIds='" + tagsIds + '\'' +
            ", tags=" + tags +
            ", comments=" + comments +
            '}';
  }

  public void init(){
    this.tagsIds = tagsToIds(this.getTags());
  }
  public String tagsToIds(List<Tag> tags){
    if(!tags.isEmpty()){
      StringBuffer ids = new StringBuffer();
      boolean flag = false;
      for(Tag tag : tags){
        if(flag){
          ids.append(",");
        }else {
          flag=true;
        }
        ids.append(tag.getId());
      }
      return ids.toString();
    }else {
      return tagsIds;
    }
  }
}
