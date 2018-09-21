package com.tiaotiaopoker.net;

import com.tiaotiaopoker.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractHttpClient implements HttpClient {
    private AbstractHttpEntity httpEntity;
    private int                httpMethod;
    private HttpRequestBase    httpRequest;
    private String             charset;

    protected AbstractHttpClient() {
    }

    public AbstractHttpClient(int httpMethod) {
        this( httpMethod, null );
    }

    public AbstractHttpClient(int httpMethod,
                              AbstractHttpEntity httpEntity) {
        this( httpMethod, httpEntity, Constants.Charset.UTF8 );
    }

    public AbstractHttpClient(int httpMethod,
                              AbstractHttpEntity httpEntity,
                              String charset) {
        this.httpEntity = httpEntity;
        this.httpMethod = httpMethod;
        this.charset = charset;
    }

    protected String doRequest(String url) throws URISyntaxException, ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        initialHttpRequest( url );
        postHttpRequest( httpRequest, httpEntity, charset );
        CloseableHttpResponse execute = client.execute( httpRequest );
        HttpEntity resentity = execute.getEntity();
        return EntityUtils.toString( resentity, "utf-8" );
    }

    @Override
    public String request(String url,
                          AbstractHttpEntity entity) throws ClientProtocolException, IOException, URISyntaxException {
        return doRequest( url );
    }

    protected abstract void postHttpRequest(HttpRequestBase httpRequest,
                                            AbstractHttpEntity httpEntity,
                                            String charset);

    private void initialHttpRequest(String url) throws URISyntaxException {
        URI uri = new URI( url );
        switch (httpMethod) {
            case Constants.Http.HTTP_POST:
                this.httpRequest = new HttpPost( uri );
                break;
            case Constants.Http.HTTP_GET:
                this.httpRequest = new HttpGet( uri );
                break;

            default:
                break;
        }
    }

    public AbstractHttpEntity getHttpEntity() {
        return httpEntity;
    }

    public void setHttpEntity(AbstractHttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

}
