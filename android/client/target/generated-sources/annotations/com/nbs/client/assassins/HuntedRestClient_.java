//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.nbs.client.assassins;

import java.util.Collections;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HuntedRestClient_
    implements HuntedRestClient
{

    private RestTemplate restTemplate;
    private String rootUrl;

    public HuntedRestClient_() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        rootUrl = "https://hunted.cloudfoundry.com/api";
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public LocationResponse updateLocation(String token, LocationMessage msg) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("token", token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<LocationMessage> requestEntity = new HttpEntity<LocationMessage>(msg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/users/{token}/location"), HttpMethod.POST, requestEntity, LocationResponse.class, urlVariables).getBody();
    }

    @Override
    public MatchResponse joinPrivateMatch(JoinMatchMessage msg) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<JoinMatchMessage> requestEntity = new HttpEntity<JoinMatchMessage>(msg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/matches/private/users"), HttpMethod.POST, requestEntity, MatchResponse.class).getBody();
    }

    @Override
    public AttackResponse attack(String token, LocationMessage msg) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("token", token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<LocationMessage> requestEntity = new HttpEntity<LocationMessage>(msg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("users/{token}/attack"), HttpMethod.POST, requestEntity, AttackResponse.class, urlVariables).getBody();
    }

    @Override
    public UserLoginResponse updateGCMRegId(String token, GCMRegistrationMessage msg) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("token", token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<GCMRegistrationMessage> requestEntity = new HttpEntity<GCMRegistrationMessage>(msg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/users/{token}/gcm/register"), HttpMethod.POST, requestEntity, UserLoginResponse.class, urlVariables).getBody();
    }

    @Override
    public UserLoginResponse registerUser(String token, UserLoginMessage loginMsg) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("token", token);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<UserLoginMessage> requestEntity = new HttpEntity<UserLoginMessage>(loginMsg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/users/{token}"), HttpMethod.POST, requestEntity, UserLoginResponse.class, urlVariables).getBody();
    }

    @Override
    public UserLoginResponse registerProvisionalUser(UserLoginMessage loginMsg) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<UserLoginMessage> requestEntity = new HttpEntity<UserLoginMessage>(loginMsg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/provisional-users"), HttpMethod.POST, requestEntity, UserLoginResponse.class).getBody();
    }

    @Override
    public void unregisterGCMRegId(String token, GCMRegistrationMessage msg) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("token", token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<GCMRegistrationMessage> requestEntity = new HttpEntity<GCMRegistrationMessage>(msg, httpHeaders);
        restTemplate.exchange(rootUrl.concat("/users/{token}/gcm/unregister"), HttpMethod.POST, requestEntity, null, urlVariables);
    }

    @Override
    public UserLoginResponse registerUser(UserLoginMessage loginMsg) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<UserLoginMessage> requestEntity = new HttpEntity<UserLoginMessage>(loginMsg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/users"), HttpMethod.POST, requestEntity, UserLoginResponse.class).getBody();
    }

    @Override
    public UserLoginResponse login(UserLoginMessage loginMsg) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<UserLoginMessage> requestEntity = new HttpEntity<UserLoginMessage>(loginMsg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/login"), HttpMethod.POST, requestEntity, UserLoginResponse.class).getBody();
    }

    @Override
    public MatchResponse joinPublicMatch(JoinMatchMessage msg) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<JoinMatchMessage> requestEntity = new HttpEntity<JoinMatchMessage>(msg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/matches/public/users"), HttpMethod.POST, requestEntity, MatchResponse.class).getBody();
    }

    @Override
    public MatchResponse createMatch(CreateMatchMessage msg) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<CreateMatchMessage> requestEntity = new HttpEntity<CreateMatchMessage>(msg, httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/matches"), HttpMethod.POST, requestEntity, MatchResponse.class).getBody();
    }

}
