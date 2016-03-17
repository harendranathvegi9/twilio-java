package com.twilio.sdk.updaters.ipmessaging.v1.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ipmessaging.v1.service.Channel;
import com.twilio.sdk.updaters.Updater;

public class ChannelUpdater extends Updater<Channel> {
    private final String serviceSid;
    private final String sid;
    private String friendlyName;
    private String uniqueName;
    private JsonNode attributes;
    private Channel.ChannelType type;

    /**
     * Construct a new ChannelUpdater.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     */
    public ChannelUpdater(final String serviceSid, 
                          final String sid) {
        this.serviceSid = serviceSid;
        this.sid = sid;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public ChannelUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The unique_name.
     * 
     * @param uniqueName The unique_name
     * @return this
     */
    public ChannelUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The attributes.
     * 
     * @param attributes The attributes
     * @return this
     */
    public ChannelUpdater setAttributes(final JsonNode attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The type.
     * 
     * @param type The type
     * @return this
     */
    public ChannelUpdater setType(final Channel.ChannelType type) {
        this.type = type;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Channel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Channel execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING,
            "/v1/Services/" + this.serviceSid + "/Channels/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Channel update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Channel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }
        
        if (attributes != null) {
            request.addPostParam("Attributes", attributes.toString());
        }
        
        if (type != null) {
            request.addPostParam("Type", type.toString());
        }
    }
}