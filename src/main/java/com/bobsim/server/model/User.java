package com.bobsim.server.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(schema = "bobsim", name = "users")
public class User {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String email;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

}
