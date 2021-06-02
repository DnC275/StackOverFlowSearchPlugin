package com.intellij.stackoverflowsearch.exceptions;

public abstract class PluginException extends Exception {
    public PluginException(){
        super();
    }

    public PluginException(Throwable cause) {
        super(cause);
    }

    @Override
    public abstract String getMessage();
}
