package com.imagehashtag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imagehashtag.service.ImageHashtagService;
import com.imagehashtag.service.imagestorage.ImageStorageService;

@RestController
@RequestMapping("/api/imagehashtags")
public class ImageHashtagController
{
    @Autowired
    private ImageStorageService imageStorageService;
    
    @Autowired
    private ImageHashtagService imageHashtagService;
    
    @GetMapping("/{hashtag}")
    public ResponseEntity<List<String>> getImagesHavingTag(@PathVariable String hashtag, Pageable pageable)
    {
        List<String> images = imageHashtagService.getImagesWithHashtag(hashtag, pageable);
        return ResponseEntity.ok(images);
    }
    
    @GetMapping("/count/{hashtag}")
    public ResponseEntity<String> getCountOfImagesHavingTag(@PathVariable String hashtag)
    {
        long count = imageHashtagService.getCountOfImagesWithHashtag(hashtag);
        return ResponseEntity.ok("Total image: " + count);
    }
    
    @PostMapping
    public ResponseEntity<String> saveImageWithHashtags(@RequestParam("image") MultipartFile image,
                                                        @RequestParam("hashtags") String hashtags)
    {
        try
        {
            String imageLink = imageStorageService.saveImageToStorage(image);
            imageHashtagService.saveImageLinkWithHashtags(imageLink, hashtags);
            return ResponseEntity.ok("Saved");
        }
        catch(Exception e)
        {
            return ResponseEntity.internalServerError().body("");
        }
    }
    
}
