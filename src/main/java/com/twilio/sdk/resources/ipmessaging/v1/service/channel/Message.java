package com.twilio.sdk.resources.ipmessaging.v1.service.channel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.ipmessaging.v1.service.channel.MessageCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.ipmessaging.v1.service.channel.MessageFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.ipmessaging.v1.service.channel.MessageReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message extends SidResource {
    private static final long serialVersionUID = 19477703234495L;

    /**
     * Create a MessageFetcher to execute fetch.
     * 
     * @param serviceSid The service_sid
     * @param channelSid The channel_sid
     * @param sid The sid
     * @return MessageFetcher capable of executing the fetch
     */
    public static MessageFetcher fetch(final String serviceSid, 
                                       final String channelSid, 
                                       final String sid) {
        return new MessageFetcher(serviceSid, channelSid, sid);
    }

    /**
     * Create a MessageCreator to execute create.
     * 
     * @param serviceSid The service_sid
     * @param channelSid The channel_sid
     * @param body The body
     * @return MessageCreator capable of executing the create
     */
    public static MessageCreator create(final String serviceSid, 
                                        final String channelSid, 
                                        final String body) {
        return new MessageCreator(serviceSid, channelSid, body);
    }

    /**
     * Create a MessageReader to execute read.
     * 
     * @param serviceSid The service_sid
     * @param channelSid The channel_sid
     * @return MessageReader capable of executing the read
     */
    public static MessageReader read(final String serviceSid, 
                                     final String channelSid) {
        return new MessageReader(serviceSid, channelSid);
    }

    /**
     * Converts a JSON String into a Message object using the provided ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Message object represented by the provided JSON
     */
    public static Message fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Message.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Message object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Message object represented by the provided JSON
     */
    public static Message fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Message.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String to;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Boolean wasEdited;
    private final String from;
    private final String body;
    private final URI url;

    @JsonCreator
    private Message(@JsonProperty("sid")
                    final String sid, 
                    @JsonProperty("account_sid")
                    final String accountSid, 
                    @JsonProperty("service_sid")
                    final String serviceSid, 
                    @JsonProperty("to")
                    final String to, 
                    @JsonProperty("date_created")
                    final String dateCreated, 
                    @JsonProperty("date_updated")
                    final String dateUpdated, 
                    @JsonProperty("was_edited")
                    final Boolean wasEdited, 
                    @JsonProperty("from")
                    final String from, 
                    @JsonProperty("body")
                    final String body, 
                    @JsonProperty("url")
                    final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.to = to;
        this.dateCreated = MarshalConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.iso8601DateTimeFromString(dateUpdated);
        this.wasEdited = wasEdited;
        this.from = from;
        this.body = body;
        this.url = url;
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
     * Returns The The to.
     * 
     * @return The to
     */
    public final String getTo() {
        return this.to;
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
     * Returns The The was_edited.
     * 
     * @return The was_edited
     */
    public final Boolean getWasEdited() {
        return this.wasEdited;
    }

    /**
     * Returns The The from.
     * 
     * @return The from
     */
    public final String getFrom() {
        return this.from;
    }

    /**
     * Returns The The body.
     * 
     * @return The body
     */
    public final String getBody() {
        return this.body;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Message other = (Message) o;
        
        return Objects.equals(sid, other.sid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(serviceSid, other.serviceSid) && 
               Objects.equals(to, other.to) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(wasEdited, other.wasEdited) && 
               Objects.equals(from, other.from) && 
               Objects.equals(body, other.body) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            to,
                            dateCreated,
                            dateUpdated,
                            wasEdited,
                            from,
                            body,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("to", to)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("wasEdited", wasEdited)
                          .add("from", from)
                          .add("body", body)
                          .add("url", url)
                          .toString();
    }
}