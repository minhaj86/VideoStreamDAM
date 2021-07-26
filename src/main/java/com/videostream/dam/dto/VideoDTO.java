package com.videostream.dam.dto;

import com.videostream.dam.orm.Video;
import com.videostream.dam.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;

public class VideoDTO {
    private int id;
    private String title;
    private String description;
    private int yearReleased;
    private List<String> videoGenreTitle = null;

    public VideoDTO() {
        this.videoGenreTitle = new ArrayList<String>();
        this.title = "";
        this.description = "";
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public List<String> getVideoGenreTitle() {
        return videoGenreTitle;
    }

    public void setVideoGenreTitle(List<String> videoGenreTitle) {
        this.videoGenreTitle = videoGenreTitle;
    }

    public Video convertToEntity() {
        return MapperUtil.getInstance().map(this, Video.class);
    }
}
