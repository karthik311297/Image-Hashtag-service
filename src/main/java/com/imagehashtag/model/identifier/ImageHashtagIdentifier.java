package com.imagehashtag.model.identifier;

import java.io.Serializable;
import java.util.Objects;

public class ImageHashtagIdentifier implements Serializable
{
    private String hashtag;
    private Long image;
    
    public ImageHashtagIdentifier()
    {
    }
    
    public ImageHashtagIdentifier(String hashtag, Long image)
    {
        this.hashtag = hashtag;
        this.image = image;
    }
    
    public String getHashtag()
    {
        return hashtag;
    }
    
    public void setHashtag(String hashtag)
    {
        this.hashtag = hashtag;
    }
    
    public Long getImage()
    {
        return image;
    }
    
    public void setImage(Long image)
    {
        this.image = image;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof ImageHashtagIdentifier)) return false;
        ImageHashtagIdentifier that = (ImageHashtagIdentifier)o;
        return getHashtag().equals(that.getHashtag()) && getImage().equals(that.getImage());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getHashtag(), getImage());
    }
}
