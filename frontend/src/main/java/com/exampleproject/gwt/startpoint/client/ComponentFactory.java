package com.exampleproject.gwt.startpoint.client;

import com.exampleproject.model.shared.Organization;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class ComponentFactory {

    private static final ConsoleLogHandler logger = new ConsoleLogHandler();

    public static DataGrid<Organization> getOrganizationTable(WorkerClient client, final List<Organization> orgList) {
        final ListDataProvider<Organization> orgModel = new ListDataProvider<>(orgList);
        DataGrid<Organization> tableOfOrganizations = new DataGrid<>();
        TextColumn<Organization> nameColumn = new TextColumn<Organization>() {
            @Override
            public String getValue(Organization object) {
                return object.getName();
            }
        };
        tableOfOrganizations.addColumn(nameColumn, "Name");
        TextColumn<Organization> idColumn = new TextColumn<Organization>() {
            @Override
            public String getValue(Organization object) {
                return Long.toString(object.getId());
            }
        };
        tableOfOrganizations.addColumn(idColumn, "Id");
        client.getOrgs(new MethodCallback<List<Organization>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Organization> response) {
                orgList.clear();
                orgList.addAll(response);
                orgModel.addDataDisplay(tableOfOrganizations);
            }
        });
        tableOfOrganizations.setSize("100%", "30%");

        Column<Organization, String> deleteOrg = new Column<Organization, String>(new ButtonCell()) {
            @Override
            public String getValue(Organization object) {
                return "X";
            }
        };
        tableOfOrganizations.addColumn(deleteOrg, "");
        deleteOrg.setFieldUpdater(new FieldUpdater<Organization, String>() {
            @Override
            public void update(int index, Organization object, String value) {
                client.delete(object, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        orgModel.getList().remove(object);
                        StringBuilder sb = new StringBuilder("orgList: ");
                        orgList.forEach(org -> sb.append(org.getName()).append(", "));
                        publishInfoLog(sb.toString());
                        orgModel.refresh();
                        tableOfOrganizations.redraw();
                    }
                });
            }
        });

        Column<Organization, String> editOrg = new Column<Organization, String>(new ButtonCell()) {
            @Override
            public String getValue(Organization object) {
                return "Edit";
            }
        };
        tableOfOrganizations.addColumn(editOrg, "");
        editOrg.setFieldUpdater(new FieldUpdater<Organization, String>() {
            @Override
            public void update(int index, Organization object, String value) {
                showEditDialog(object, client, orgModel, tableOfOrganizations);
            }
        });

        return tableOfOrganizations;
    }

    private static DialogBox showEditDialog(Organization org, WorkerClient client,
                                            ListDataProvider<Organization> orgModel,
                                            DataGrid<Organization> tableOfOrganizations) {
        final DialogBox dialogBox = new DialogBox(false, true);
        dialogBox.setText("Редактирование организации");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        final TextBox tb = new TextBox();
        tb.setText(org.getName());
        tb.setName("Название");
        VerticalPanel panel = new VerticalPanel();
        panel.add(tb);
        panel.add(new Button("Сохранить", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (tb.getText().length() == 0) {
                    Window.alert("Имя не может быть пустым");
                    return;
                }
                org.setName(tb.getText());
                client.save(org, new MethodCallback<Organization>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, Organization response) {
                        dialogBox.hide();
                        orgModel.refresh();
                        tableOfOrganizations.redraw();
                    }
                });
            }
        }));
        panel.add(new Button("Отмена", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        }));
        panel.setSpacing(10);
        dialogBox.setPopupPosition(100, 150);
        dialogBox.setWidget(panel);
        dialogBox.show();
        return dialogBox;
    }

    private static void publishInfoLog(String message) {
        logger.publish(new LogRecord(Level.INFO, message));
    }
}
