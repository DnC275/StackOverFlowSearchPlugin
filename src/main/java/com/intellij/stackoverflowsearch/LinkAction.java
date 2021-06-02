package com.intellij.stackoverflowsearch;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public abstract class LinkAction extends AnAction {
    protected URL link;

    public LinkAction(String title){
        super(title);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        BrowserUtil.browse(link);
    }
}
