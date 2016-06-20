package za.co.caxtondigital.vo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @Column(name = "id",unique = true, length = 150)
    private String id;

    @Getter @Setter
    @Column(name = "name", length = 150)
    private String name;

    @Getter @Setter
    @Column(name = "country", length = 4)
    private String country;

    @Getter @Setter
    @Column(name = "aliases", length = 200)
    private String aliases;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
