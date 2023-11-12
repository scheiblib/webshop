package sze.thesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sze.thesis.persistence.entity.Item;
import sze.thesis.persistence.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item findItemById(long id){
        return itemRepository.findById(id);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public List<Item> addItems(List<Item> items) {
        itemRepository.saveAll(items);
        return findAll();
    }
}
