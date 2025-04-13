package com.acupoint.entity.po;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ReasoningContentMessage {

    private String role;
    private String content;
    private String reasoningContent;
    private Boolean playing;
    private Boolean writer;
    private Date timestamp;

    public ReasoningContentMessage() {
    }

    public ReasoningContentMessage(String role, String content, String reasoningContent, Boolean playing, Boolean writer, Date timestamp) {
        this.role = role;
        this.content = content;
        this.reasoningContent = reasoningContent;
        this.playing = playing;
        this.writer = writer;
        this.timestamp = timestamp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReasoningContent() {
        return reasoningContent;
    }

    public void setReasoningContent(String reasoningContent) {
        this.reasoningContent = reasoningContent;
    }

    public Boolean getPlaying() {
        return playing;
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }

    public Boolean getWriter() {
        return writer;
    }

    public void setWriter(Boolean writer) {
        this.writer = writer;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
