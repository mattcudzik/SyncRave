package org.mcudzik.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@Getter @AllArgsConstructor
@Builder
@NoArgsConstructor
public class Session {

    @Column(nullable = false, unique = true, name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "name")
    private String name;

    @Column(name = "invitation_code", unique = true, nullable = false)
    private String invitationCode;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sessionProperties_id")
    private SessionProperties sessionProperties;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnonUser> anonUsers;

    @Column(name = "spotify_playlist_id")
    private String spotifyPlaylistId;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tracks_added", joinColumns = @JoinColumn(name = "session_id"))
    @Column(name = "spotify_track_added")
    private List<String> tracksAdded;
}
