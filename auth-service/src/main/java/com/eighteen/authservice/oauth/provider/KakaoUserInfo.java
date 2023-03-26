package com.eighteen.authservice.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
        return attributesProfile.get("nickname").toString();
    }

    @Override
    public String getEmail() {
        return attributesAccount.get("email").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

}

