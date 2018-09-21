package com.tiaotiaopoker.net;

import com.tiaotiaopoker.Constants;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGet extends AbstractHttpClient {
    public HttpGet() {
        super( Constants.Http.HTTP_GET );
    }

    @Override
    public String request(String url,
                          AbstractHttpEntity entity) throws ClientProtocolException, IOException, URISyntaxException {
        return doRequest( url );
    }

    @Override
    protected void postHttpRequest(HttpRequestBase httpRequest,
                                   AbstractHttpEntity httpEntity,
                                   String charset) {
    }

}
