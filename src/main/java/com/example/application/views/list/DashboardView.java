package com.example.application.views.list;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Premier League")
public class DashboardView extends VerticalLayout {
    private final CrmService service;

    public DashboardView(CrmService service){
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getStatisticiMembri(), getStatisticiEchipe());
    }

    private Component getStatisticiMembri(){
        Span statistici = new Span(service.numaraMembri() + " membri");
        statistici.addClassNames("text-xl", "mt-m");
        return statistici;
    }

    private Chart getStatisticiEchipe(){
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        service.gasesteToateEchipele().forEach(echipa -> dataSeries.add(new DataSeriesItem(echipa.getDenumireEchipa(), echipa.getEmployeeCount())));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}
