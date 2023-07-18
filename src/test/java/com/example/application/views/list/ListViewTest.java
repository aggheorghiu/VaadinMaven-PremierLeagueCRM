package com.example.application.views.list;

import com.example.application.data.entity.Membri;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ListViewTest {

    @Autowired
    private ListView listView;

    @Test
    public void formShownWhenMemberSelected() {
        Grid<Membri> grid = listView.grid;
        Membri firstMembru = getFirstItem(grid);

        ContactForm form = listView.form;

        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstMembru);
        assertTrue(form.isVisible());
        assertEquals(firstMembru.getNume(), form.nume.getValue());
    }

    private Membri getFirstItem(Grid<Membri> grid){
        return ( (ListDataProvider<Membri>) grid.getDataProvider()).getItems().iterator().next();
    }
}