package entity;

/**
 * @author 徐英杰
 */
public class EasybuyNews {
    /**
     *  主键
     */
    private Integer id;
    /**
     *  新闻标题
     */
    private String title;
    /**
     *  新闻内容
     */
    private String content;
    /**
     *  新闻创建时间
     */
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
