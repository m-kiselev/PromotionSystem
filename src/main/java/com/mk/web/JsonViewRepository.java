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
        
    private static final JsonView minimalListView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
        put(Contract.class, RemoveHibProps.class);
        put(Manager.class, RemoveHibProps.class);
        put(Service.class, RemoveHibProps.class);
        put(Contact.class, RemoveHibProps.class);
    }});

    public JsonView getMinimalListView() {
        return minimalListView;
    }
    
    private static final JsonView listContactView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
        put(Contact.class, RemoveHibProps.class);
    }});
    
    private static final JsonView listContractView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
    	put(Contract.class, RemoveHibProps.class);
    }});
    
    private static final JsonView listManagerView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
    	put(Manager.class, RemoveHibProps.class);
    }});
    
    private static final JsonView listServiceView = new JsonView(new HashMap<java.lang.Class<?>,java.lang.Class<?>>() {{
    	put(Service.class, RemoveHibProps.class);
    }});
    
    /**
     * The main view for the list entities.
     */
    private static final Map<Class<?>, JsonView> viewsForLists = new HashMap<Class<?>, JsonView>(){{
        put(Contract.class,   listContractView);
        put(Manager.class,    listManagerView);
        put(Service.class,    listServiceView);
        put(Contact.class,    listContactView);
    }};

    public static JsonView getViewForList(Class<?> clazz) {
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
//        put(Company.class, new JsonView(Company.class, CompanyMinimal.class));
//        put(ContentStatusForOperator.class, singleCSFOView);
//        put(CrReward.class,                 singleRORView );
//        put(LsReward.class,                 singleRORView);
//        put(OperatorPriceCategory.class, new JsonView(OperatorPriceCategory.class, OperatorPriceCategoryMinimal.class));
//        put(ContentRule.class,     listContentRuleView);
//        put(TelecomOperator.class, listTelecomOperatorForComboView);
//        put(ContentType.class,     listContentTypeForComboView);
//        put(RightOwner.class,      listRightOwnerForComboView);
    }};

    public static JsonView getViewForEntity(Class<?> clazz) {

        JsonView vv = minimalViews.get(clazz);
        if (vv == null) {
            vv = new JsonView(clazz, RemoveHibProps.class);
        }

        return vv;
    }
}