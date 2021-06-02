package com.intellij.stackoverflowsearch.exceptions;

public abstract class PluginException extends Exception {
    public PluginException(Throwable cause) {
        super(cause);
    }
}
