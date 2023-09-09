package com.alibou.security.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;
    @Column(name="first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name="email")
    private String email;
    @Column(name = "password")
    private String password;
}
