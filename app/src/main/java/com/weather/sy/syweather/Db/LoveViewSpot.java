package com.weather.sy.syweather.Db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "LOVE_VIEW_SPOT".
 */
public class LoveViewSpot {

    private Long id;
    private String name;
    private Integer order;

    public LoveViewSpot() {
    }

    public LoveViewSpot(Long id) {
        this.id = id;
    }

    public LoveViewSpot(Long id, String name, Integer order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
