package com.videostream.dam.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VideoGenre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;

	@ManyToOne
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;

	public VideoGenre() {
	}

	public VideoGenre(VideoGenre v) {
		this.id = v.id;
		this.title = v.title;
	}

	public VideoGenre(VideoGenre vg, Video v) {
		this.id = vg.id;
		this.title = vg.title;
		this.video = v;
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

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}
