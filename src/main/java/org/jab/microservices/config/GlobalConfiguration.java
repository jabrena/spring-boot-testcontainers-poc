package org.jab.microservices.config;

//@Component
//@ConfigurationProperties(prefix = "cache.redis")
public class GlobalConfiguration {

    private String host;
    private String port;
    private String minIdle;
    private String maxIdle;
    private String maxActive;
    private String maxWait;
    private String timeOut;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
}