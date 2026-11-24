package com.example.itemmanagement.Service;

import com.example.itemmanagement.DTO.ItemResponseDTO;
import com.example.itemmanagement.Entity.Item;
import com.example.itemmanagement.Repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service // it the springboot annotation to create the beans(objects) and scans the pkg containing the main app class and its sub pkg's to discover components when the springboot application startup
public class ItemService {


    private final ItemRepository itemRepository;

    public static final Logger log = LoggerFactory.getLogger(ItemService.class);

    //This is constructor based DI, Here item service depends on itemRepository , spring supplies it through the constructor's parameter
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item){
        log.info("Creating the records...");
        return itemRepository.save(item);
    }

    public List<Item> getItems(){
        log.info("Fetching all the items");
        return itemRepository.findAll();
    }

    public ItemResponseDTO getItemById(int id){
         Item item = itemRepository.findById(id) //here it returns optional <Item> , if the id presents. Optional is a container-used internally in the repository layer
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
         return new ItemResponseDTO(item.getId(), item.getName());
    }

    public List<Item> getItemsByPrice(double maxPrice){
        return itemRepository.findByPriceLessThanEqual(maxPrice);
    }

    public Item updateItem(Integer id, Item updatedItem){
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(Integer id){
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(existingItem);
    }
}
