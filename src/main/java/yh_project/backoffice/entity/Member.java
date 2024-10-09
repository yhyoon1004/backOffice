package yh_project.backoffice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String imageUrl;
    private String provider;
    private String providerId;
    private String role;

    protected Member() {
    }

    @Builder
    public Member(String email, String name, String imageUrl, String provider, String providerId, String role) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }
}
