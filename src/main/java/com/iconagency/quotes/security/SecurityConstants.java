package com.iconagency.quotes.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/auth/**";

    public static final String SECRET = "eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTYzNTQ2Nzk1MiwiaWF0IjoxNjM1NDY3OTUyfQ.9ekOeyp1w65RtqvQS2mdTBdDYR7R-wWJdQc5oBMCOwD7zBfJOGDPBEVr_aW7bjUfuB6NlaHcxk5cs3axarY0RA";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long  EXPIRATION_TIME = 600_000; //10 min

}
