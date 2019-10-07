package com.exampleproject.gwt.startpoint.client;


import com.exampleproject.model.shared.Organization;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import com.exampleproject.model.shared.TestDto;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class StartPoint implements EntryPoint {

    private final WorkerClient client = GWT.create(WorkerClient.class);

    private static final String HELLO_MESSAGE = "Hi, I'm your gwt application!";

    private final List<Organization> orgList = new ArrayList<>();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");

        Button send = new Button("Send");
        send.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                client.get(new MethodCallback<TestDto>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        // TODO Auto-generated method stub
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, TestDto response) {
                        // TODO Auto-generated method stub
                        Window.alert(response.getMessage());
                    }

                });
            }
        });
        RootPanel.get().add(send);

        Button testDb = new Button("Test DB");
        testDb.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                client.getDb(new MethodCallback<Boolean>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        // TODO Auto-generated method stub
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, Boolean response) {
                        // TODO Auto-generated method stub
                        Window.alert(response.toString());
                    }

                });
            }
        });
        RootPanel.get().add(testDb);

        final Label label = new Label(HELLO_MESSAGE);
        RootPanel.get().add(label);

        final Button button = new Button("Click me");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("You press on me! Don't it anymore");
            }
        });
        RootPanel.get().add(button);
        DataGrid<Organization> table = ComponentFactory.getOrganizationTable(client, orgList);
        RootPanel.get().add(table);
        Button hide = new Button("Hide");
        hide.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                table.setVisible(false);
            }
        });
RootPanel.get().add(hide);
        final FormPanel orgForm = new FormPanel();
        orgForm.setAction("/orgForm");
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(10);
        orgForm.setWidget(panel);
        final TextBox tb=new TextBox();
        tb.setWidth("220");
        tb.setName("name");
        panel.add(tb);
        panel.add(new Button("Создать", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if(tb.getText().length()==0){
                    Window.alert("Имя не может быть пустым");
                    return;
                }
                client.save(new Organization(tb.getText()), new MethodCallback<Organization>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, Organization response) {
                        orgList.add(response);
                    }
                });
            }
        }));
//        orgForm.addSubmitHandler(new FormPanel.SubmitHandler() {
//            @Override
//            public void onSubmit(FormPanel.SubmitEvent event) {
//                if(tb.getText().length()==0){
//                    Window.alert("Имя не может быть пустым");
//                    event.cancel();
//                }
//            }
//        });
//        orgForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//            @Override
//            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//                client.save(new Organization(tb.getText()), new MethodCallback<Organization>() {
//                    @Override
//                    public void onFailure(Method method, Throwable exception) {
//                        Window.alert(exception.toString() + "\n" + exception.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(Method method, Organization response) {
//                        orgList.add(response);
//                    }
//                });
//            }
//        });
        DecoratorPanel decoratorPanel = new DecoratorPanel();
        decoratorPanel.add(orgForm);
        RootPanel.get().add(decoratorPanel);
    }
}
