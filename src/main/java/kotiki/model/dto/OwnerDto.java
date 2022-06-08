package kotiki.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public final class OwnerDto {
    private int id;
    private Date ownerBirthday;
    private String ownerName;

    public OwnerDto(int id, Date ownerBirthday, String ownerName) {
        this.id = id;
        this.ownerBirthday = ownerBirthday;
        this.ownerName = ownerName;
    }
}
