package kotiki.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "owner", schema = "public", catalog = "postgres")
public class Owner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "owner_birthday")
    private Date ownerBirthday;

    @Basic
    @Column(name = "owner_name")
    private String ownerName;

    public Owner(String name, Date birthday) {
        this.ownerName = name;
        this.ownerBirthday = birthday;
    }
}
