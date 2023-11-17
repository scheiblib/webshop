package sze.thesis.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sze.thesis.model.ItemDto;
import sze.thesis.persistence.entity.Item;
import sze.thesis.persistence.entity.Role;
import sze.thesis.service.ItemService;
import sze.thesis.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/item/{id}")
    public Item findById(@PathVariable("id") long id){
        return itemService.findItemById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<ItemDto> findAll (){
        return itemService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add_multiple")
    public ResponseEntity<List<ItemDto>> addItems(@RequestBody List<Item> items) {

//        if(userService.getLoggedUser().getRole().equals(Role.USER)){
//            return ResponseEntity.status(403).build();
//        }

        return ResponseEntity.ok(itemService.addItems(items));
    }
}
