package com.example.inventory.controller;

import com.example.inventory.model.Item;
import com.example.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    // Endpoint untuk barang dengan stok di bawah ambang batas tertentu
    @GetMapping("/low-stock")
    public List<Item> getLowStockItems(@RequestParam int threshold) {
        return itemService.getLowStockItems(threshold);
    }

    // Endpoint untuk total nilai stok
    @GetMapping("/total-value")
    public double getTotalStockValue() {
        return itemService.getTotalStockValue();
    }

    // Endpoint untuk rata-rata harga barang
    @GetMapping("/average-price")
    public double getAveragePrice() {
        return itemService.getAveragePrice();
    }

    // Endpoint untuk barang berdasarkan kategori tertentu
    @GetMapping("/category/{categoryId}")
    public List<Item> getItemsByCategory(@PathVariable Long categoryId) {
        return itemService.getItemsByCategory(categoryId);
    }
}
