package ru.otus.java.http.server.app;

import java.math.BigDecimal;

public class Item {
    private Long id;
    private String title;
    private BigDecimal price;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Item(Long id, String tittle, BigDecimal price) {
        this.id = id;
        this.title = tittle;
        this.price = price;
    }

}
