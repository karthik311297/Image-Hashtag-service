package com.imagehashtag.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.imagehashtag.model.identifier.ImageHashtagIdentifier;

@Entity
@Table(name = "image_hashtag")
@IdClass(ImageHashtagIdentifier.class)
public class ImageHashtag
{
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value")
    private Hashtag hashtag;
    
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Image image;
    
    public ImageHashtag()
    {
    }
    
    public Hashtag getHashtag()
    {
        return hashtag;
    }
    
    public void setHashtag(Hashtag hashtag)
    {
        this.hashtag = hashtag;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public void setImage(Image image)
    {
        this.image = image;
    }
}
