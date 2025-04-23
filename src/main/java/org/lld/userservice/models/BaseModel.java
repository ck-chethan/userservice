package org.lld.userservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    private Long id;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}
