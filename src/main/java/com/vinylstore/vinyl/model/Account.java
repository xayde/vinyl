package com.vinylstore.vinyl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @NotEmpty(message = "Firstname cannot be null or empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotEmpty(message = "Lastname cannot be null or empty")
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty(message = "Email cannot be null or empty")
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @NotEmpty(message = "Password cannot be null or empty")
    @Column(name = "password")
    private String password;
    @Column(name = "confirmation_key")
    private String confirmationKey;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role")
    private Role role;
}
