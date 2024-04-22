package org.example.upload.repository;

import org.example.upload.domain.Item;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private final Map<Long, Item> store = new HashMap<Long, Item>();
    private long sequence = 0L;
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public Item findById(Long id){
        return store.get(id);
    }
}