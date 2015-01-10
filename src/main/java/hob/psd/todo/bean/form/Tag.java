package hob.psd.todo.bean.form;


/**
 * Created by lgunti on 003, Jan 03.
 */
public class Tag {
    public Tag(String tagName) {
        this.tagName = tagName;
    }
    private int tagId;

    private String tagName;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
