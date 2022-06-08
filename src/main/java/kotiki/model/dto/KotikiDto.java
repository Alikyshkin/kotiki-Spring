package kotiki.model.dto;

import kotiki.model.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public final class KotikiDto {

    private int id;
    private Date kotikBirthday;
    private Breed kotikBreed;
    private Color kotikColor;
    private String kotikName;
    private int ownerId;

    public KotikiDto(int id, Date kotikBirthday, Breed kotikBreed, Color kotikColor, String kotikName, int ownerId) {
        this.id = id;
        this.kotikBirthday = kotikBirthday;
        this.kotikBreed = kotikBreed;
        this.kotikColor = kotikColor;
        this.kotikName = kotikName;
        this.ownerId = ownerId;
    }
}
