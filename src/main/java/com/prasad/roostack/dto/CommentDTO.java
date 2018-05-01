package com.prasad.roostack.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lgunti on 003, Dec 03.
 */
/*@Entity
@Table(name="comments")*/
public class CommentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentId")
    private int commentId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="commentOwner_userId")
    private UserDTO commentedBy;

    @Column(name="commentedTime")
    private Date commentedTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentedTime() {
        return commentedTime;
    }

    public void setCommentedTime(Date commentedTime) {
        this.commentedTime = commentedTime;
    }

    public UserDTO getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(UserDTO commentedBy) {
        this.commentedBy = commentedBy;
    }
}
