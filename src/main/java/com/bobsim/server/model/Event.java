package com.bobsim.server.model;

import java.util.Set;

import com.bobsim.server.model.type.Page;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Event {

    private Integer userId;

    private Set<Integer> userItems;

    private Request request;

    private Page page;

    public Event(Integer userId, Set<Integer> userItems, Request request, Page page) {
        this.userId = userId;
        this.userItems = userItems;
        this.request = request;
        this.page = page;
    }
}