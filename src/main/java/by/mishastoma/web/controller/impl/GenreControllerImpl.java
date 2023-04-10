package by.mishastoma.web.controller.impl;

import by.mishastoma.service.GenreService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreControllerImpl implements CrudController<GenreDto> {
    private final GenreService genreService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid GenreDto genre) {
        genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/{id}")
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid GenreDto genre, @PathVariable Long id) {
        genre.setId(id);
        genreService.update(genre);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<GenreDto> findGenreByName(@RequestParam @Size(max = 32,
            message = "Genre maximum size is 32") String name) {
        GenreDto genre = genreService.findGenreByName(name);
        return ResponseEntity.ok(genre);
    }
}
