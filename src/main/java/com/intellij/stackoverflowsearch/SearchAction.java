package com.intellij.stackoverflowsearch;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.stackoverflowsearch.exceptions.NullEditorException;
import com.intellij.stackoverflowsearch.exceptions.PluginException;
import com.intellij.stackoverflowsearch.linkActions.LinkAction;
import com.intellij.stackoverflowsearch.request.Request;
import com.intellij.stackoverflowsearch.request.builders.SearchRequestBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            JBPopupFactory popupFactory = JBPopupFactory.getInstance();
            Editor editor = e.getData(PlatformDataKeys.EDITOR);
            if (editor == null) {
                throw new NullEditorException();
            }
            SelectionModel selectionModel = editor.getSelectionModel();
            String text = selectionModel.getSelectedText();
            System.out.println(text);
            List<LinkAction> objects = makeSearch(text);
            ActionGroup actionGroup = new DefaultActionGroup(objects);

            ListPopup popupSearchResult = popupFactory.createActionGroupPopup("StackOverFlow Search Results", actionGroup, e.getDataContext(), JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, false);
            popupSearchResult.showInBestPositionFor(editor);
        }
        catch (PluginException exception) {
            exception.printStackTrace();
            Messages.showMessageDialog(exception.getMessage(), "StackOverFlowSearch Error", Messages.getErrorIcon());
        }
    }

    private List<LinkAction> makeSearch(String textForSearch) throws PluginException {
        SearchRequestBuilder requestBuilder = new SearchRequestBuilder(textForSearch);
        Request searchRequest = requestBuilder.create();
        return searchRequest.perform();
    }
}
