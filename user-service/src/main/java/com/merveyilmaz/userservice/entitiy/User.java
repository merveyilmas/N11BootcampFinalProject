package com.merveyilmaz.userservice.entitiy;

import com.merveyilmaz.userservice.enums.EnumGender;
import com.merveyilmaz.userservice.enums.EnumStatus;
import com.merveyilmaz.userservice.general.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    @Id
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_CREATE_DATE", nullable = false)
    private LocalDateTime userCreateDate;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "LONGITUDE", nullable = false)
    private double longitude;

    @Column(name = "LATITUDE", nullable = false)
    private double latitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 30)
    private EnumGender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 30, nullable = false)
    private EnumStatus status;
}
