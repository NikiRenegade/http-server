package ru.otus.java.http.server.app;

import java.math.BigDecimal;

public class Item {
    private Long id;
    private String tittle;
    private BigDecimal price;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Item(Long id, String tittle, BigDecimal price) {
        this.id = id;
        this.tittle = tittle;
        this.price = price;
    }

}
