package com.tiaotiaopoker.net;

import com.tiaotiaopoker.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum HttpPosts {
    JSON( new AbstractHttpClient( Constants.Http.HTTP_POST ) {
        private final static String APPLICATION_JSON = "application/json";

        @Override
        protected void postHttpRequest(HttpRequestBase httpRequest,
                                       AbstractHttpEntity httpEntity,
                                       String charset) {
            httpEntity.setContentEncoding( charset );
            HttpPost hp = (HttpPost) httpRequest;
            httpEntity.setContentType( APPLICATION_JSON );
            hp.setEntity( httpEntity );
        }

    } ) {
        @Override
        protected AbstractHttpEntity initialHttpEntity(Map<String, Object> parameters) throws UnsupportedEncodingException {
            String jsonData = parameters.get( "data" ).toString();
            return new StringEntity( jsonData.toString() );
        }

    }, FORM( new AbstractHttpClient( Constants.Http.HTTP_POST ) {
        private final static String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

        @Override
        protected void postHttpRequest(HttpRequestBase httpRequest,
                                       AbstractHttpEntity httpEntity,
                                       String charset) {
            httpEntity.setContentEncoding( charset );
            httpEntity.setContentType( APPLICATION_FORM_URLENCODED );
            HttpPost hp = (HttpPost) httpRequest;
            hp.setEntity( httpEntity );
        }

    } ) {
        @Override
        protected AbstractHttpEntity initialHttpEntity(Map<String, Object> parameters) throws UnsupportedEncodingException {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            BasicNameValuePair nv;
            for( String key : parameters.keySet() ) {
                nv = new BasicNameValuePair( key, parameters.get( key ).toString() );
                formparams.add( nv );
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity( formparams );
            return entity;
        }

    }, QUERY( new AbstractHttpClient( Constants.Http.HTTP_POST ) {

        @Override
        protected void postHttpRequest(HttpRequestBase httpRequest,
                                       AbstractHttpEntity httpEntity,
                                       String charset) {
        }

    } ) {
        @Override
        protected AbstractHttpEntity initialHttpEntity(Map<String, Object> parameters) throws UnsupportedEncodingException {
            return null;
        }

    }, POST( new AbstractHttpClient( Constants.Http.HTTP_POST ) {
        @Override
        protected void postHttpRequest(HttpRequestBase httpRequest,
                                       AbstractHttpEntity httpEntity,
                                       String charset) {
            httpEntity.setContentEncoding( charset );
            HttpPost hp = (HttpPost) httpRequest;
            hp.setEntity( httpEntity );
        }

    } ) {
        @Override
        protected AbstractHttpEntity initialHttpEntity(Map<String, Object> parameters) throws UnsupportedEncodingException {
            String jsonData = parameters.get( "data" ).toString();
            return new StringEntity( jsonData.toString() );
        }

    };

    private final AbstractHttpClient httpClient;

    private HttpPosts(AbstractHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected abstract AbstractHttpEntity initialHttpEntity(Map<String, Object> parameters) throws UnsupportedEncodingException;

    public String request(String url,
                          Map<String, Object> parameters) throws ClientProtocolException, IOException, URISyntaxException {
        httpClient.setHttpEntity( initialHttpEntity( parameters ) );
        return httpClient.doRequest( url );
    }

}
