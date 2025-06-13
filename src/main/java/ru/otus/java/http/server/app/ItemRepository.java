package ru.otus.java.http.server.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemRepository {
    private List<Item> items;
    public ItemRepository() {
        this.items = new ArrayList<Item>(Arrays.asList(
                new Item(1L, "Milk", BigDecimal.valueOf(80)),
                new Item(2L, "Bread", BigDecimal.valueOf(38))
        ));
    }
    public List<Item> getAllItems() {
        return items;
    }
    public Item getItemById(Long id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
    public Item createItem(Item item) {
        Long newId = 1L;
        for (Item i : items) {
            if (newId <= i.getId()) {
                newId = i.getId() + 1L;
            }
        }
        item.setId(newId);
        items.add(item);
        return item;
    }
}
