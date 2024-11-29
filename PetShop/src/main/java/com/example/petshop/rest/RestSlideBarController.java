package com.example.petshop.rest;

import com.example.petshop.entity.SliderBar;
import com.example.petshop.service.SlideBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slide-bar")
@CrossOrigin("*")
public class RestSlideBarController {
    @Autowired
    private SlideBarService slideBarService;

    @GetMapping
    public List<SliderBar> getSlideBar() {
        return slideBarService.getAll();
    }

    @GetMapping("{id}")
    public SliderBar getSlideBarById(Integer id) {
        return slideBarService.getById(id);
    }

    @PostMapping
    public SliderBar save(@RequestBody SliderBar sliderBar) {
        return slideBarService.save(sliderBar);
    }

    @PutMapping("/{id}")
    public SliderBar update(@RequestBody SliderBar sliderBar, @PathVariable Integer id) {
        sliderBar.setId(id);
        return slideBarService.save(sliderBar);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        slideBarService.delete(id);
    }

    @DeleteMapping
    public void deleteAll() {
        slideBarService.deleteAll();
    }
}
