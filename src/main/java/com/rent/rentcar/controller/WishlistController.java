package com.rent.rentcar.controller;

import com.rent.rentcar.entity.Wishlist;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.WishlistRepository;
import com.rent.rentcar.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private WishlistRepository wishlistRepository;

    @PostMapping("/save")
    public ResponseEntity<Wishlist> add(@RequestBody Wishlist wishlist) {
        return ResponseEntity.ok(wishlistService.add(wishlist));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Wishlist>> findAll() {
        return ResponseEntity.ok(wishlistService.getAll());
    }

    @GetMapping("/find-wishlist/{id}")
    public ResponseEntity<Wishlist> getById(@PathVariable Long id) throws GetByIdErrorMessage {
        if (!wishlistRepository.existsById(id)) {
            throw new GetByIdErrorMessage("İstek listenizde böyle bir ürün bulunmamakta");
        }
        return ResponseEntity.ok(wishlistService.getById(id));
    }

    @DeleteMapping("/remove-wishlist/{id}")
    public ResponseEntity<Wishlist> remove(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!wishlistRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz ürün istek listenizde bulunmuyor");
        }
        wishlistService.removeWishlist(id);
        return new ResponseEntity<Wishlist>(HttpStatus.ACCEPTED);
    }
}
