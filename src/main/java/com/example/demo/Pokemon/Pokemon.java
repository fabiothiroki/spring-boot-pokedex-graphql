package com.example.demo.Pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "pokemon")
@JsonIgnoreProperties(value = { "type", "base" })
public class Pokemon {

    @Id
    public Long id;

    @Column
    public String name;

    @JsonProperty("name")
    private void unpackNestedName(Map<String,Object> name) {
        this.name = (String)name.get("english");
    }
}
