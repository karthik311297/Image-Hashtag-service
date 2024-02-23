package com.imagehashtag.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hashtag")
public class Hashtag
{
    @Id
    @Column(length = 50)
    private String value;
    
    public Hashtag()
    {
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Hashtag)) return false;
        Hashtag hashtag = (Hashtag)o;
        return getValue().equals(hashtag.getValue());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getValue());
    }
}
