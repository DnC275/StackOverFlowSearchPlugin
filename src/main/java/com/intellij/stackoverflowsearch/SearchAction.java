package com.intellij.stackoverflowsearch;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.stackoverflowsearch.exceptions.PluginException;
import com.intellij.stackoverflowsearch.request.Request;
import com.intellij.stackoverflowsearch.request.builders.SearchRequestBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

//        ArrayList<MySimpleClass> mySimpleClasses = new ArrayList<>();
//        mySimpleClasses.add(new MySimpleClass());
//        mySimpleClasses.add(new MySimpleClass());
//        BaseListPopupStep<MySimpleClass> step =
//                new BaseListPopupStep<MySimpleClass>("Xui", mySimpleClasses) {
//                    @Override
//                    public PopupStep onChosen(MySimpleClass selectedValue, boolean finalChoice){
//                        if (selectedValue != null && finalChoice) {
//                            selectedValue.showMessage("Aboba");
//                        }
//                        return FINAL_CHOICE;
//                    }
//
//                    @NotNull
//                    @Override
//                    public String getTextFor(MySimpleClass value) {
//                        return "Jopa";
//                    }
//
//                    @Override
//                    public Icon getIconFor(MySimpleClass aValue) {
//                        return Messages.getInformationIcon();
//                    }
//                };
//        ListPopupImpl popup = new ListPopupImpl(e.getProject(), step);
//        popup.showInBestPositionFor(e.getData(PlatformDataKeys.EDITOR));

        try {
            JBPopupFactory popupFactory = JBPopupFactory.getInstance();
            Editor editor = e.getData(PlatformDataKeys.EDITOR);
            SelectionModel selectionModel = editor.getSelectionModel();
            String text = selectionModel.getSelectedText();
            System.out.println(text);
            List<LinkAction> objects = makeSearch(text);
            ActionGroup actionGroup = new DefaultActionGroup(objects);

            ListPopup pisos = popupFactory.createActionGroupPopup("StackOverFlow Search Results", actionGroup, e.getDataContext(), JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, false);
//            Editor editor = e.getData(PlatformDataKeys.EDITOR);
            pisos.showInBestPositionFor(editor);
        }
        catch (PluginException exception) {
            exception.printStackTrace();
            Messages.showMessageDialog(exception.getMessage(), "Sorry", Messages.getErrorIcon());
        }

//        Editor editor = e.getData(PlatformDataKeys.EDITOR);
//        editor.getComponent()
//        String text = editor.getSelectionModel().getSelectedText();
//        Messages.showMessageDialog(text, "Test", Messages.getInformationIcon());
    }

    private List<LinkAction> makeSearch(String textForSearch) throws PluginException {
        SearchRequestBuilder requestBuilder = new SearchRequestBuilder(textForSearch);
        Request searchRequest = requestBuilder.create();
        return searchRequest.perform();
    }
}
