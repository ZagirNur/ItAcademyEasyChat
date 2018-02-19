package ru.zagir.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"chats","friends"})
@EqualsAndHashCode(exclude = "chats")
@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String login;

    String password;

    String firstName;
    String lastName;
    String eMail;

    @Enumerated(EnumType.STRING)
    Role role;
    @Enumerated(EnumType.STRING)
    State state;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    List<Chat> chats;

    @ManyToMany
    @JoinTable(name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    List<User> friends;

    @OneToOne(mappedBy = "user")
    private FileInfo image;
}
