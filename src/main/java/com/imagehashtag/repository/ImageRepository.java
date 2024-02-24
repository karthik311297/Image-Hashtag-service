package com.imagehashtag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imagehashtag.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>
{

}
