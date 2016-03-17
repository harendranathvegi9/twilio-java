package com.twilio.sdk.creators.ipmessaging.v1.service.channel;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ipmessaging.v1.service.channel.Message;

public class MessageCreator extends Creator<Message> {
    private final String serviceSid;
    private final String channelSid;
    private final String body;
    private String from;

    /**
     * Construct a new MessageCreator.
     * 
     * @param serviceSid The service_sid
     * @param channelSid The channel_sid
     * @param body The body
     */
    public MessageCreator(final String serviceSid, 
                          final String channelSid, 
                          final String body) {
        this.serviceSid = serviceSid;
        this.channelSid = channelSid;
        this.body = body;
    }

    /**
     * The from.
     * 
     * @param from The from
     * @return this
     */
    public MessageCreator setFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Message
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Message execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING,
            "/v1/Services/" + this.serviceSid + "/Channels/" + this.channelSid + "/Messages",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Message creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
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
        
        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (body != null) {
            request.addPostParam("Body", body);
        }
        
        if (from != null) {
            request.addPostParam("From", from);
        }
    }
}