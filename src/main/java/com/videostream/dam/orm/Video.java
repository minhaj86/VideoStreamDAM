package com.videostream.dam.orm;

import com.videostream.dam.dto.VideoDTO;
import com.videostream.dam.util.MapperUtil;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Video")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.videostream.dam.orm.Video.findAll",
                        query = "SELECT v FROM Video v"
                )
        })
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "yearReleased")
    @Min(value = 1900)
    @Max(value = 2050)
    private int yearReleased;

	@OneToMany(mappedBy="video",cascade=CascadeType.ALL)
    private List<VideoGenre> genres = new ArrayList<VideoGenre>();


	public Video() {
		
	}
	
	public Video(Video v) {
		this.id=v.id;
		this.title=v.title;
		this.description=v.description;
		v.getGenres().forEach(x-> {
			VideoGenre t = new VideoGenre(x);
			this.getGenres().add(t);
		});
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public List<VideoGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<VideoGenre> genres) {
		this.genres = genres;
	}

	

    public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public VideoDTO convertToDto() {
		return MapperUtil.getInstance().map(this, VideoDTO.class);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Video)) {
            return false;
        }

        Video video = (Video) o;

        return id == video.id &&
                yearReleased == video.yearReleased &&
                Objects.equals(title, video.title) &&
                Objects.equals(description, video.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, yearReleased);
    }
}

