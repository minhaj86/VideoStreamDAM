package com.videostream.dam.dao;

import com.videostream.dam.dto.VideoDTO;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.videostream.dam.orm.Video;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class VideoDAO extends AbstractDAO<Video> {
    public VideoDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<VideoDTO> findById(Long id) {
        Video video = get(id);
        return Optional.ofNullable(video.convertToDto());
    }

    public Optional<VideoDTO> updateById(Long id, VideoDTO videoDTO) {
        Video video = videoDTO.convertToEntity();
    	video.setId(id);

        Video videoOld = get(id);
        videoOld.getGenres().forEach( genre -> {
            currentSession().delete(genre);
        });
        videoOld.getGenres().clear();
        videoOld.mapFromDTO(videoDTO);
        videoOld.setId(id);
    	return Optional.ofNullable(persist(videoOld).convertToDto());
    }

    public Optional<VideoDTO> deleteById(Long id) {
        Optional<VideoDTO> video = findById(id);
        currentSession().delete(video.get().convertToEntity());
    	return video;
    }

    public VideoDTO create(@Valid VideoDTO videoDTO) {
        Video video = persist(videoDTO.convertToEntity());
        return video.convertToDto();
    }

    public List<VideoDTO> findAll() {
        List<Video> videoList = list(namedTypedQuery("com.videostream.dam.orm.Video.findAll"));

        return videoList.stream().map(video -> {
            return video.convertToDto();
        }).collect(Collectors.toList());
    }

}
