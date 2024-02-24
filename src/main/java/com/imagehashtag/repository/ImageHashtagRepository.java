package com.imagehashtag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imagehashtag.model.ImageHashtag;
import com.imagehashtag.model.identifier.ImageHashtagIdentifier;

@Repository
public interface ImageHashtagRepository extends JpaRepository<ImageHashtag,ImageHashtagIdentifier>
{
    @Query("select count(a) from ImageHashtag a where a.hashtag.value = :hashtagString")
    long getCountOfImagesByHashtagValue(@Param("hashtagString") String hashtag);
    
    @Query("select a.image.link from ImageHashtag a where a.hashtag.value = :hashtagString")
    Page<String> findImagesWithHashtagValue(@Param("hashtagString") String hashtag, Pageable pageable);
}
