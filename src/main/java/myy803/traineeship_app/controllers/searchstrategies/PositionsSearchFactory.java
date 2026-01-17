package myy803.traineeship_app.controllers.searchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionsSearchFactory {
    @Autowired
    private SearchBasedOnLocation searchBasedOnLocation;

    @Autowired
    private SearchBasedOnInterests searchBasedOnInterests;

    @Autowired
    private SearchBasedOnTitle searchBasedOnTitle;

    public PositionsSearchStrategy create(String strategy) {
        if ("location".equals(strategy)) return searchBasedOnLocation;
        if ("interests".equals(strategy)) return searchBasedOnInterests;
        if("title".equals(strategy)) return searchBasedOnTitle;
        return searchBasedOnLocation; //default
    }
}
