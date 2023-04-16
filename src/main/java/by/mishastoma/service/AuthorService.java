package by.mishastoma.service;

import by.mishastoma.web.dto.AuthorDto;
import org.springframework.data.domain.Page;

public interface AuthorService extends CrudService<AuthorDto> {

    Page<AuthorDto> search(AuthorDto author, int pageNumber, int pageSize);
}
