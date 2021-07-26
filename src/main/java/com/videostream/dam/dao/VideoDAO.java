package com.videostream.dam.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.videostream.dam.dto.VideoDTO;
import com.videostream.dam.util.MapperUtil;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.videostream.dam.orm.Video;

import javax.validation.Valid;
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

    public VideoDTO create(@Valid VideoDTO videoDTO) {
        return persist(videoDTO.convertToEntity()).convertToDto();
    }

    public List<Video> findAll() {
        return list(namedTypedQuery("com.videostream.dam.orm.Video.findAll"));
    }

}
