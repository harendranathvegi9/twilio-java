package com.twilio.sdk.resources.ipmessaging.v1.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.ipmessaging.v1.service.UserCreator;
import com.twilio.sdk.deleters.ipmessaging.v1.service.UserDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.ipmessaging.v1.service.UserFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.ipmessaging.v1.service.UserReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.ipmessaging.v1.service.UserUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends SidResource {
    private static final long serialVersionUID = 168172741902935L;

    /**
     * Create a UserFetcher to execute fetch.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     * @return UserFetcher capable of executing the fetch
     */
    public static UserFetcher fetch(final String serviceSid, 
                                    final String sid) {
        return new UserFetcher(serviceSid, sid);
    }

    /**
     * Create a UserDeleter to execute delete.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     * @return UserDeleter capable of executing the delete
     */
    public static UserDeleter delete(final String serviceSid, 
                                     final String sid) {
        return new UserDeleter(serviceSid, sid);
    }

    /**
     * Create a UserCreator to execute create.
     * 
     * @param serviceSid The service_sid
     * @param identity The identity
     * @param roleSid The role_sid
     * @return UserCreator capable of executing the create
     */
    public static UserCreator create(final String serviceSid, 
                                     final String identity, 
                                     final String roleSid) {
        return new UserCreator(serviceSid, identity, roleSid);
    }

    /**
     * Create a UserReader to execute read.
     * 
     * @param serviceSid The service_sid
     * @return UserReader capable of executing the read
     */
    public static UserReader read(final String serviceSid) {
        return new UserReader(serviceSid);
    }

    /**
     * Create a UserUpdater to execute update.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     * @param roleSid The role_sid
     * @return UserUpdater capable of executing the update
     */
    public static UserUpdater update(final String serviceSid, 
                                     final String sid, 
                                     final String roleSid) {
        return new UserUpdater(serviceSid, sid, roleSid);
    }

    /**
     * Converts a JSON String into a User object using the provided ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return User object represented by the provided JSON
     */
    public static User fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, User.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a User object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return User object represented by the provided JSON
     */
    public static User fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, User.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String roleSid;
    private final String identity;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private User(@JsonProperty("sid")
                 final String sid, 
                 @JsonProperty("account_sid")
                 final String accountSid, 
                 @JsonProperty("service_sid")
                 final String serviceSid, 
                 @JsonProperty("role_sid")
                 final String roleSid, 
                 @JsonProperty("identity")
                 final String identity, 
                 @JsonProperty("date_created")
                 final String dateCreated, 
                 @JsonProperty("date_updated")
                 final String dateUpdated, 
                 @JsonProperty("url")
                 final URI url, 
                 @JsonProperty("links")
                 final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.roleSid = roleSid;
        this.identity = identity;
        this.dateCreated = MarshalConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The service_sid.
     * 
     * @return The service_sid
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The The role_sid.
     * 
     * @return The role_sid
     */
    public final String getRoleSid() {
        return this.roleSid;
    }

    /**
     * Returns The The identity.
     * 
     * @return The identity
     */
    public final String getIdentity() {
        return this.identity;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date_updated.
     * 
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The links.
     * 
     * @return The links
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        User other = (User) o;
        
        return Objects.equals(sid, other.sid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(serviceSid, other.serviceSid) && 
               Objects.equals(roleSid, other.roleSid) && 
               Objects.equals(identity, other.identity) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(url, other.url) && 
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            roleSid,
                            identity,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("roleSid", roleSid)
                          .add("identity", identity)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}