package com.example.itemmanagement.Controller;

import com.example.itemmanagement.DTO.ItemRequestDTO;
import com.example.itemmanagement.DTO.ItemResponseDTO;
import com.example.itemmanagement.Entity.Item;
import com.example.itemmanagement.Service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") //to enable cors(Cross Origin Resource sharing)
@RestController
public class ItemController {

    // Here item controller class needs the item service object, and the Class (Item service) should annotate with @Service annotation
    private final ItemService itemService;

    //this is the constructor based DI,since the ItemController class needs the ItemService object, spring provides through DI which was registered the spring beans into spring container.
    public ItemController(ItemService itemService){
        this.itemService= itemService;//assigns the value through the constructor parameter
    }

    @PostMapping("/items")
    public Item saveItem(@RequestBody ItemRequestDTO itemRequestDTO){ // RequestBody means Payload we send to create the data, ItemRequestDTO-java type of parameter, itemRequestDTO- parameter name
        Item item = new Item();
        item.setName(itemRequestDTO.getName());
        item.setPrice(itemRequestDTO.getPrice());
        return itemService.saveItem(item);
    }

    @GetMapping("/showItems")
    public List<Item> showItems(){
        return itemService.getItems();
    }

    @GetMapping("/item/{id}")
    public ItemResponseDTO getItemById(@PathVariable int id){
        return itemService.getItemById(id);
    }

    @GetMapping("/items")
    public List<Item> getItemByMaxPrice(@RequestParam double maxPrice){
        return itemService.getItemsByPrice(maxPrice);
    }

    @PutMapping("/items/{id}")
    public Item updateItem(
            @PathVariable Integer id,
            @RequestBody ItemRequestDTO itemRequestDTO){
        Item item = new Item();
        item.setName(itemRequestDTO.getName());
        item.setPrice(itemRequestDTO.getPrice());
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Integer id){
        itemService.deleteItem(id);
    }

}
