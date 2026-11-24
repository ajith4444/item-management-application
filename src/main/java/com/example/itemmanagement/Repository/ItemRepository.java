package com.example.itemmanagement.Repository;

import com.example.itemmanagement.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByPriceLessThanEqual(double maxPrice);
}
