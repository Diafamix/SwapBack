package com.tfg.swapCatBack.data.entities;


import com.tfg.swapCatBack.data.entities.enums.UserRole;
import com.tfg.swapCatBack.data.entities.enums.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
@ToString(exclude = {"wallets"})
@Table(name = "USERS")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "El campo email no puede estar vacio")
    @Email
    @Column(unique = true)
    private String mail;

    @NotEmpty(message = "El campo nombre de ususario no puede ir vacio")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "EL campo contraseña no puede ir vacio")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(mappedBy = "user")
    private List<WalletModel> wallets = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<FavouritesModel> favourites = new ArrayList<>();

    private int numRequests;

    @Builder
    public UserModel(String mail, String username, String password, UserRole role, UserType type, int numRequests) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.type = type;
        this.numRequests = numRequests;
    }

}
