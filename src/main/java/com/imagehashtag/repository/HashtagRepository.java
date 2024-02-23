package com.imagehashtag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imagehashtag.model.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag,String>
{
    List<Hashtag> findByValueIn(List<String> values);
}
