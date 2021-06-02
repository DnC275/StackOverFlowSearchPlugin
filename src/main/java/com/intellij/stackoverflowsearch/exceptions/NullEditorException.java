package com.intellij.stackoverflowsearch.exceptions;

public class NullEditorException extends PluginException {
    public NullEditorException(){
        super();
    }

    public NullEditorException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Please open a file and select a simple section of text";
    }
}
