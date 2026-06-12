package com.example.entity;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdDate;
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
    @LastModifiedBy
    String modifiedBy;

}