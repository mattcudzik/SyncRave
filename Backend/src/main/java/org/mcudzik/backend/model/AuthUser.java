package org.mcudzik.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "auth_users")
public class AuthUser implements UserDetails {

    @Column(nullable = false, unique = true, name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(name = "spotify_id")
    private String spotifyId;

    @Column(name = "spotify_display_name")
    private String spotifyName;

    @Setter
    @Column(name = "access_token", length = 512)
    private String accessToken;

    @Setter
    @Column(name = "token_expiration")
    private Date tokenExpiration;

    @Column(name = "refresh_token", length = 512)
    private String refreshToken;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(UserTypes.SPOTIFY.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
