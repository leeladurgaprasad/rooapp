package com.prasad.roostack.dto;

import javax.persistence.*;

/**
 * Created by lgunti on 003, Jan 03.
 */
/*@Entity
@Table(name="tags")*/
public class TagDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tagId")
    private int tagId;

    @Column(name = "tagName")
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
