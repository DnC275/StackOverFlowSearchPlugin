package com.intellij.stackoverflowsearch.exceptions;

public class URLException extends PluginException {
    public URLException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Add http or https to the url";
    }
}
