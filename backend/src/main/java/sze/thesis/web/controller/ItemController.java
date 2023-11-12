package sze.thesis.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sze.thesis.persistence.entity.Item;
import sze.thesis.service.ItemService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/findItemById/{id}")
    public Item findById(@PathVariable("id") long id){
        return itemService.findItemById(id);
    }

    @GetMapping("/findAll")
    public List<Item> findAll (){
        return itemService.findAll();
    }

    @PostMapping("/addItems")
    public List<Item> addItems(@RequestBody List<Item> items) {
        return itemService.addItems(items);
    }
}
