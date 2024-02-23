package com.imagehashtag.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.imagehashtag.model.Image;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Long>
{

}
