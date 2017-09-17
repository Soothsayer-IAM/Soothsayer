package coms.soothsayer.core.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
public class BaseEntity implements Serializable {
    @Id
    private String id;
}
