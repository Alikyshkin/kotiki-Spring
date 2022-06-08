package kotiki.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class FriendsDto {
    private int id;
    private Integer kotik1;
    private Integer kotik2;

    public FriendsDto(int id, Integer kotik1, Integer kotik2) {
        this.id = id;
        this.kotik1 = kotik1;
        this.kotik2 = kotik2;
    }
}
