package by.mishastoma.web.controller.impl;

import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.GenreDto;
import by.mishastoma.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreControllerImpl implements CrudController<GenreDto> {
    private final GenreService genreService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody GenreDto genre) {
        genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> findById(@PathVariable Long id) {
        GenreDto genre = genreService.findById(id);
        return ResponseEntity.ok(genre);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody GenreDto genre) {
        genreService.update(genre);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<GenreDto> findGenreByName(@PathVariable String name) {
        GenreDto genre = genreService.findGenreByName(name);
        return ResponseEntity.ok(genre);
    }
}
