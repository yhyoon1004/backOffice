package yh_project.backoffice.config.oauth2;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import yh_project.backoffice.entity.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class OAuth2UserDetail extends DefaultOAuth2User implements UserDetails {

    private Member member;

    public OAuth2UserDetail(Member member, Map<String, Object> attributes, String nameAttributeKey) {
        super(Collections.singletonList(new SimpleGrantedAuthority(member.getRole())), attributes, nameAttributeKey);
        this.member = member;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
