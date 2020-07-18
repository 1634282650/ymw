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
    /**
     *  获取id
     * @return id
     */
    public Integer getId() {
        return id;
    }
    /**
     *  设置主键id
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     *  获取标题
     * @return 标题
     */
    public String getTitle() {
        return title;
    }
    /**
     *  设置标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     *  获得新闻内容
     * @return 新闻内容
     */
    public String getContent() {
        return content;
    }
    /**
     *  设置新闻内容
     * @param content 新闻内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     *  获得新闻创建时间
     * @return 新闻创建时间
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     *  设置新闻创建时间
     * @param createTime 需要设置的新闻创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
