package com.example.inventory.repository;

import com.example.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Menemukan item dengan stok di bawah nilai tertentu
    List<Item> findByStockLessThan(int threshold);

    // Menemukan item berdasarkan kategori
    @Query("SELECT i FROM Item i WHERE i.category.id = :categoryId")
    List<Item> findItemsByCategory(@Param("categoryId") Long categoryId);
}
