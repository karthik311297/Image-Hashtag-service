package com.imagehashtag.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imagehashtag.model.Hashtag;
import com.imagehashtag.model.Image;
import com.imagehashtag.model.ImageHashtag;
import com.imagehashtag.repository.HashtagRepository;
import com.imagehashtag.repository.ImageHashtagRepository;
import com.imagehashtag.repository.ImageRepository;

@Service
public class ImageHashtagService
{
    @Autowired
    private HashtagRepository hashtagRepository;
    
    @Autowired
    private ImageHashtagRepository imageHashtagRepository;
    
    @Autowired
    private ImageRepository imageRepository;
    
    public List<String> getImagesWithHashtag(String hashtag, Pageable pageable)
    {
        return imageHashtagRepository
                .findImagesWithHashtagValue(hashtag, pageable)
                .stream()
                .collect(Collectors.toList());
    }
    
    public long getCountOfImagesWithHashtag(String hashtag)
    {
        return imageHashtagRepository.getCountOfImagesByHashtagValue(hashtag);
    }
    
    @Transactional
    public void saveImageLinkWithHashtags(String imageLink, String hashtags)
    {
        List<String> uniqueTags = Arrays.stream(hashtags.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        Image persistedImage = persistImageDetails(imageLink);
        persistUniqueHashtagsIfNotExists(uniqueTags);
        persistImageHashtagMappings(uniqueTags, persistedImage);
    }
    
    private Image persistImageDetails(String imageLink)
    {
        Image image = new Image();
        image.setLink(imageLink);
        return imageRepository.save(image);
    }
    
    private void persistUniqueHashtagsIfNotExists(List<String> uniqueTags)
    {
        Set<Hashtag> tagsNotAlreadyInDB = uniqueTags
                .stream()
                .map(tag -> {
                    Hashtag hashtagEntity = new Hashtag();
                    hashtagEntity.setValue(tag);
                    return hashtagEntity;
                }).collect(Collectors.toSet());
        Set<Hashtag> tagsAlreadyInDB = new HashSet<>(hashtagRepository.findByValueIn(uniqueTags));
        tagsNotAlreadyInDB.removeAll(tagsAlreadyInDB);
        hashtagRepository.saveAll(tagsNotAlreadyInDB);
    }
    
    private void persistImageHashtagMappings(List<String> uniqueTags, Image persistedImage)
    {
        List<ImageHashtag> imageWithHashtags = new ArrayList<>();
        List<Hashtag> hashtagsInDB = hashtagRepository.findByValueIn(uniqueTags);
        for(Hashtag theHashtag : hashtagsInDB)
        {
            ImageHashtag imageHashtag = new ImageHashtag();
            imageHashtag.setHashtag(theHashtag);
            imageHashtag.setImage(persistedImage);
            imageWithHashtags.add(imageHashtag);
        }
        imageHashtagRepository.saveAll(imageWithHashtags);
    }
}
