package sze.thesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sze.thesis.model.ItemDto;
import sze.thesis.model.OrderDto;
import sze.thesis.persistence.entity.Item;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item findItemById(long id){
        return itemRepository.findById(id);
    }

    public List<ItemDto> findAll(){
        return convertEntityListToDtoList(itemRepository.findAll());
    }

    public List<ItemDto> addItems(List<Item> items) {
        itemRepository.saveAll(items);
        return findAll();
    }

    private List<ItemDto> convertEntityListToDtoList(List<Item> items){
        List<ItemDto> result = new ArrayList<>();
        items.forEach(item -> {
            result.add(new ItemDto(item));
        });
        return result;
    }
}
