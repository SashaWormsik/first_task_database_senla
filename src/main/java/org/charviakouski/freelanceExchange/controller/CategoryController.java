package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto getById(@PathVariable long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto insert(@RequestBody CategoryDto categoryDto) {
        return categoryService.insert(categoryDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto update(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        return categoryService.update(categoryDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }
}
