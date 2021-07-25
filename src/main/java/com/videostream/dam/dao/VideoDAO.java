package com.videostream.dam.dao;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.videostream.dam.orm.Video;

import java.util.List;
import java.util.Optional;


public class VideoDAO extends AbstractDAO<Video> {
    public VideoDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Video> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Optional<Video> updateById(Long id, Video video) {
    	video.setId(id);
    	return Optional.ofNullable(persist(video)); 
    }

    public Optional<Video> deleteById(Long id) {
    	return deleteById(id);
    }

    public Video create(Video video) {
        return persist(video);
    }

    public List<Video> findAll() {
        return list(namedTypedQuery("com.videostream.dam.orm.Video.findAll"));
    }

}
