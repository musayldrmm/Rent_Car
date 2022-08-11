package com.rent.rentcar.service;

import com.rent.rentcar.entity.Wishlist;
import com.rent.rentcar.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist add(Wishlist wishlist) {

        return wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getAll() {
        return wishlistRepository.findAll();
    }

    public Wishlist getById(Long wishlistId) {
        return wishlistRepository.findById(wishlistId).get();
    }

    public void removeWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }


}
