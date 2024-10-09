package yh_project.backoffice.mapper;

import java.util.Map;

public class OAuth2UserAttributeMapper {

    private final Map<String, Object> attributes;

    public OAuth2UserAttributeMapper(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
