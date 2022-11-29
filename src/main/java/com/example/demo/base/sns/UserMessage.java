package com.example.demo.base.sns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_message")
public class UserMessage {
    @Id
    @Column(name="id")
    String id;
    @Column(unique = true)
    String messageNumber;
}
