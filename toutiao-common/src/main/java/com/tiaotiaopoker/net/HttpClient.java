package com.tiaotiaopoker.net;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.AbstractHttpEntity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface HttpClient {
    public abstract String request(String url,
                                   AbstractHttpEntity entity) throws ClientProtocolException,
                                                              IOException,
                                                              URISyntaxException;
}
