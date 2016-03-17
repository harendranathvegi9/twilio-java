package com.twilio.sdk.creators.ipmessaging.v1;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ipmessaging.v1.Credential;

public class CredentialCreator extends Creator<Credential> {
    private final String friendlyName;
    private final Credential.PushService type;
    private String certificate;
    private String privateKey;
    private Boolean sandbox;
    private String apiKey;

    /**
     * Construct a new CredentialCreator.
     * 
     * @param friendlyName The friendly_name
     * @param type The type
     */
    public CredentialCreator(final String friendlyName, 
                             final Credential.PushService type) {
        this.friendlyName = friendlyName;
        this.type = type;
    }

    /**
     * The certificate.
     * 
     * @param certificate The certificate
     * @return this
     */
    public CredentialCreator setCertificate(final String certificate) {
        this.certificate = certificate;
        return this;
    }

    /**
     * The private_key.
     * 
     * @param privateKey The private_key
     * @return this
     */
    public CredentialCreator setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    /**
     * The sandbox.
     * 
     * @param sandbox The sandbox
     * @return this
     */
    public CredentialCreator setSandbox(final Boolean sandbox) {
        this.sandbox = sandbox;
        return this;
    }

    /**
     * The api_key.
     * 
     * @param apiKey The api_key
     * @return this
     */
    public CredentialCreator setApiKey(final String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Credential
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Credential execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING,
            "/v1/Credentials",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Credential creation failed: Unable to connect to server");
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
        
        return Credential.fromJson(response.getStream(), client.getObjectMapper());
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
        
        if (type != null) {
            request.addPostParam("Type", type.toString());
        }
        
        if (certificate != null) {
            request.addPostParam("Certificate", certificate);
        }
        
        if (privateKey != null) {
            request.addPostParam("PrivateKey", privateKey);
        }
        
        if (sandbox != null) {
            request.addPostParam("Sandbox", sandbox.toString());
        }
        
        if (apiKey != null) {
            request.addPostParam("ApiKey", apiKey);
        }
    }
}