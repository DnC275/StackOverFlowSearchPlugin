package com.intellij.stackoverflowsearch.exceptions;

public class ConnectionException extends PluginException {
    public ConnectionException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Connection problems, check your internet connection";
    }
}
