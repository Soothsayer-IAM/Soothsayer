package coms.soothsayer.core.entities.user;

import coms.soothsayer.core.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity {
    private String userName;
    private String password;
    private String key;
    private String mobile;
    private String displayName;
    private String email;
    private boolean active;
    private DateTime createDate;
    private List<String> groupIds;
}
