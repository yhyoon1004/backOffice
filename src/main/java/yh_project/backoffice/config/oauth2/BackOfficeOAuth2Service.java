package yh_project.backoffice.config.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import yh_project.backoffice.entity.Member;
import yh_project.backoffice.mapper.OAuth2UserAttributeMapper;
import yh_project.backoffice.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BackOfficeOAuth2Service extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserAttributeMapper attributeMapper = new OAuth2UserAttributeMapper(oAuth2User.getAttributes());
        String providerPlatform = userRequest.getClientRegistration().getRegistrationId();


        Optional<Member> userData = memberRepository.findByEmail(attributeMapper.getEmail());
        if (userData.isPresent()) {
            return new OAuth2UserDetail(userData.get(), oAuth2User.getAttributes(), "sub");
        } else {
            Member member = memberRepository.save(
                    Member.builder()
                            .email(attributeMapper.getEmail())
                            .name(attributeMapper.getName())
                            .imageUrl(attributeMapper.getImageUrl())
                            .provider(providerPlatform)
                            .providerId(attributeMapper.getProviderId())
                            .role("MEMBER")
                            .build()
            );
            return new OAuth2UserDetail(member, oAuth2User.getAttributes(), "sub");
        }
    }
}
