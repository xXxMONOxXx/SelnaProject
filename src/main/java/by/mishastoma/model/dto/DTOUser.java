package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DTOUser {

    private Integer id;
    private String username;
    private String password;
    private DTORole role;
    private DTOProfile profile;
    private List<DTOItem> items;
}
