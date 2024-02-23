package com.imagehashtag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imagehashtag.model.identifier.ImageHashtagIdentifier;
import com.imagehashtag.model.ImageHashtag;

@Repository
public interface ImageHashtagRepository extends JpaRepository<ImageHashtag,ImageHashtagIdentifier>
{
    @Query("select count(a) from ImageHashtag a where a.hashtag.value = :hashtagString")
    long getCountOfImagesByHashtagValue(@Param("hashtagString") String hashtag);
}
