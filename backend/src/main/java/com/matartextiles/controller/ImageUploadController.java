package com.matartextiles.controller;

import com.matartextiles.entity.Category;
import com.matartextiles.entity.Product;
import com.matartextiles.repository.CategoryRepository;
import com.matartextiles.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ImageUploadController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Value("${app.upload.dir:./uploads}")
    private String uploadDir;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg", "image/png", "image/webp"
    );

    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    @PostMapping("/categories/{id}/image")
    public ResponseEntity<?> uploadCategoryImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));

        validateFile(file);

        String extension = getExtension(file.getOriginalFilename());
        String filename = category.getSlug() + "." + extension;

        Path dir = Paths.get(uploadDir, "categories");
        Files.createDirectories(dir);
        Files.copy(file.getInputStream(), dir.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        String imageUrl = "/uploads/categories/" + filename;
        category.setImageUrl(imageUrl);
        categoryRepository.save(category);

        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }

    @PostMapping("/products/{id}/image")
    public ResponseEntity<?> uploadProductImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));

        validateFile(file);

        String extension = getExtension(file.getOriginalFilename());
        String filename = product.getSlug() + "." + extension;

        Path dir = Paths.get(uploadDir, "products");
        Files.createDirectories(dir);
        Files.copy(file.getInputStream(), dir.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        String imageUrl = "/uploads/products/" + filename;
        product.setImageUrl(imageUrl);
        productRepository.save(product);

        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new RuntimeException("File too large (max 5MB)");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new RuntimeException("Invalid file type. Allowed: jpg, png, webp");
        }
    }

    private String getExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
            if (Set.of("jpg", "jpeg", "png", "webp").contains(ext)) {
                return ext.equals("jpeg") ? "jpg" : ext;
            }
        }
        return "jpg";
    }
}
