package org.mcudzik.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "session_properties")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class SessionProperties {
    @Column(nullable = false, unique = true, name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "sessionProperties")
    private Session session;

    @Column(name = "max_songs_per_guest")
    private Integer maxSongsPerGuest;

    @Column(name = "max_guests")
    private Integer maxGuests;

    @Column (name = "ban_explicit_content")
    private Boolean banExplicit;


    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "banned_genres", joinColumns = @JoinColumn(name = "session_properties_id"))
    @Column(name = "banned_genre")
    private List<String> bannedGenres;

    @Column(name = "generate_playlist")
    private Boolean generatePlaylist;
}
