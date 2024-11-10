package com.example.inventory.service;

import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    // Menampilkan barang dengan stok di bawah ambang batas tertentu
    public List<Item> getLowStockItems(int threshold) {
        return itemRepository.findByStockLessThan(threshold);
    }

    // Menampilkan total nilai stok
    public double getTotalStockValue() {
        return itemRepository.findAll().stream()
                .mapToDouble(item -> item.getPrice() * item.getStock())
                .sum();
    }

    // Menampilkan rata-rata harga barang
    public double getAveragePrice() {
        return itemRepository.findAll().stream()
                .mapToDouble(Item::getPrice)
                .average()
                .orElse(0.0);
    }

    // Menampilkan ringkasan berdasarkan kategori tertentu
    public List<Item> getItemsByCategory(Long categoryId) {
        return itemRepository.findItemsByCategory(categoryId);
    }
}
