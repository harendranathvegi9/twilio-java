package com.twilio.sdk.updaters.ipmessaging.v1.service;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ipmessaging.v1.service.User;
import com.twilio.sdk.updaters.Updater;

public class UserUpdater extends Updater<User> {
    private final String serviceSid;
    private final String sid;
    private final String roleSid;

    /**
     * Construct a new UserUpdater.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     * @param roleSid The role_sid
     */
    public UserUpdater(final String serviceSid, 
                       final String sid, 
                       final String roleSid) {
        this.serviceSid = serviceSid;
        this.sid = sid;
        this.roleSid = roleSid;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated User
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public User execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING,
            "/v1/Services/" + this.serviceSid + "/Users/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("User update failed: Unable to connect to server");
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
        
        return User.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (roleSid != null) {
            request.addPostParam("RoleSid", roleSid);
        }
    }
}