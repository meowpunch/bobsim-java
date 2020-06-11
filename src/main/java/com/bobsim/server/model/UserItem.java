package com.bobsim.server.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(schema = "bobsim", name = "user_items")
public class UserItem {

    @Id @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;
}
