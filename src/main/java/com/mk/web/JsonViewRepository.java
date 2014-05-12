package com.mk.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mk.model.*;

@SuppressWarnings("serial")
public class JsonViewRepository {

    private static final Logger        log    = LoggerFactory.getLogger(JsonViewRepository.class);

    public JsonViewRepository() {}

    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler","fieldHandler" })
    public class RemoveHibProps{
    }

    /************************************************************************************/
    /************************** minimals that are without any associations***************/
    /************************************************************************************/

    @JsonIgnoreProperties(value = { "content", "uploadInfo", "tags", "hibernateLazyInitializer", "handler","fieldHandler" })
    public class ContentPackageMinimal {
    }

    
    /************************************************************************************/
    /************************************************************************************/
    /************************************************************************************/
        
    private static final JsonView listContractView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
        put(Contract.class, RemoveHibProps.class);
    }});

    public JsonView getListContractView() {
        return listContractView;
    }

    private static final JsonView minimalListView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
        put(Contract.class, RemoveHibProps.class);
        put(Manager.class,  RemoveHibProps.class);
        put(Service.class,  RemoveHibProps.class);
        put(MonthPlan.class,RemoveHibProps.class);
        put(HeadDep.class,  RemoveHibProps.class);
        put(IndividualClient.class, RemoveHibProps.class);
        put(LegalClient.class,      RemoveHibProps.class);
    }});

    public JsonView getMinimalListView() {
        return minimalListView;
    }
    
    /**
     * The main view for the list entities.
     */
    private static final Map<Class<?>, JsonView> viewsForLists = new HashMap<Class<?>, JsonView>(){{
        put(Contract.class,   minimalListView);
        put(Manager.class,    minimalListView);
        put(Service.class,    minimalListView);
        put(MonthPlan.class,  minimalListView);
        put(HeadDep.class,    minimalListView);
        put(IndividualClient.class, minimalListView);
        put(LegalClient.class,      minimalListView);
    }};

    public static JsonView getViewForList(Class<?> clazz) {
    	System.out.println(log);
    	log.info("clazz = {}", clazz.toString());
        JsonView vv = viewsForLists.get(clazz);
        if (vv == null) {
            log.warn("View for list is not defined for {}, using RemoveHibProps", clazz.getName());
            vv = new JsonView(clazz, RemoveHibProps.class);
        }
        return vv;
    }

    /**
     * Minimum of information about entities.
     */
    private static final Map<Class<?>, JsonView> minimalViews = new HashMap<Class<?>, JsonView>(){{
        put(Contract.class,   minimalListView);
        put(Manager.class,    minimalListView);
        put(Service.class,    minimalListView);
        put(MonthPlan.class,  minimalListView);
        put(HeadDep.class,    minimalListView);
        put(IndividualClient.class, minimalListView);
        put(LegalClient.class,      minimalListView);
    }};

    public static JsonView getViewForEntity(Class<?> clazz) {

        JsonView vv = minimalViews.get(clazz);
        if (vv == null) {
            vv = new JsonView(clazz, RemoveHibProps.class);
        }

        return vv;
    }
}