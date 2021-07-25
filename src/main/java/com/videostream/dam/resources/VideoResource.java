package com.videostream.dam.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.videostream.dam.dao.VideoDAO;
import com.videostream.dam.orm.Video;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/video")
@Produces(MediaType.APPLICATION_JSON)
public class VideoResource {
    private final VideoDAO videoDAO;

    public VideoResource(VideoDAO dao) {
        this.videoDAO = dao;
    }

    @POST
    @UnitOfWork
    public Video createVideo(@Valid Video video) {
        return videoDAO.create(video);
    }
    
	@GET
	@Path("/{id}")
    @UnitOfWork
	public Optional<Video> getVideo(@PathParam("id") long id) {
		return videoDAO.findById(id);
	}

	@PUT
	@Path("/{id}")
    @UnitOfWork
	public Optional<Video> updateVideo(@PathParam("id") long id, @Valid Video video) {
		return videoDAO.updateById(id, video);
	}

	@DELETE
	@Path("/{id}")
    @UnitOfWork
	public Optional<Video> deleteVideo(@PathParam("id") long id) {
		return videoDAO.deleteById(id);
	}

    @GET
    @UnitOfWork
    public List<Video> listVideos() {
        return videoDAO.findAll();
    }

}
