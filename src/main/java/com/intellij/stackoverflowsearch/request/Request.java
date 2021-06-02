package com.intellij.stackoverflowsearch.request;

import com.intellij.stackoverflowsearch.linkActions.LinkAction;
import com.intellij.stackoverflowsearch.exceptions.PluginException;

import java.util.List;

public interface Request {
    public List<LinkAction> perform() throws PluginException;
}
