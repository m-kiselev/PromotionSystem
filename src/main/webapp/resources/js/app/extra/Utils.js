/**
 * This file contains utils functions.
 * 
 * @author mkiselev
 */
//companies.setRawValue(fetchField(record.data.companies,"longTitle"));
function fetchField(data,name) {
    var out = "";
    if (data != null) {
        for (var r = 0; r < data.length; r++) {
            out += data[r][name] + ", ";
        }
        if (out.length > 0) {
            out = out.substr(0,out.length-2);
        }
    }
    return out;
}
function fetchIds(data) {
    var out = [];
    for (var r = 0; r < data.length; r++) {
        out.push(data[r].id);
    }
    return out;
}
function cleanEmptyFields(form) {
    for (var i in form) {
        if ((form[i] === null) || 
            (typeof form[i] === undefined) || 
            (form[i].length == 0)) {
            delete form[i];
        }
    }
    return form;
}

function tabTitle(title, id){
    return title + ' ['+id +']';
} 

function tabId(tabkind, tabid){
    return tabkind + '-' + tabid;
}

function applyIdFilter(id, store) {
    if (id != null) {
        store.filter('id', id);
        store.filters.clear();
    }
}

/*смена id одной из вкладок*/
function changeTabItemId(tabItem, newId){
    var tabs = Ext.getCmp('mainPanel');

    var oldId = tabItem.id;
    tabs.items.removeAtKey(oldId);
    Ext.ComponentManager.unregister(tabItem);

    tabItem.id = newId;

    Ext.ComponentManager.register(tabItem);
    tabs.items.add(newId,tabItem);
    tabs.setActiveTab(tabItem);
}

function createContentAndStatusArray(recs){
    var content = [];
    for (var i=0; i<recs.length; i++) {
        var rec = recs[i];
        content.push({                 contentId:rec.data.id, 
                      contentStatusForOperatorId:rec.data.contentstatusforoperatorid});
    }
    return content;
}


function getParamsObject(store){
    var options = {
    	action:     'read',
        page:       store.currentPage,
        limit:      store.pageSize,
        filters:    store.filters.items,
        sorters:    store.getSorters()
    };
    var operation = new Ext.data.Operation(options);
    
    var fakeRequest = store.getProxy().buildRequest(operation);
    var params = fakeRequest.params;

    return params;
}

/**
 * Get parameters from store and feature filter for current grid(feature id = 'filter_feature')
 * 
 * @param grid
 * @param allRecords - true(get all records)
 * @returns {params}
 */
function getParamsFromStoreAndFilters(grid, allRecords) {
    var params        = getParamsObject(grid.getStore());
    var filterFeature = grid.getView().getFeature('filter_feature');
    if (typeof params.filter == 'undefined' && typeof filterFeature != 'undefined') {
    	params.filter = filterFeature.buildQuery(filterFeature.getFilterData());
    }
    if (allRecords) {
    	params.limit = null;
    }
    return params;
}

/**
 * Get numeric valueField from given combo. 
 * Combo should have bound store, defined 'valueField' property, and set value  
 * 
 * @param combo
 * @returns id (valueField), null (if text field is empty), undefined (if text field contains text not from list)
 * @see http://docs.sencha.com/extjs/4.1.3/source/ComboBox.html#Ext-form-field-ComboBox-method-getValue 
 */
function getNumericIdFromCombo(combo){
	
	var id = combo.getValue();
	if (   id != null 
		&& !Ext.isNumber(id)
		&& id == combo.getRawValue() ){
		
		return; //undefined
	}
	else
		return id;
};

function getFirstDayCurrentMonth() {
    var today = new Date();
    return new Date(today.getFullYear(), today.getMonth(), 1);
}

function getLastDayCurrentMonth() {
    var today = new Date();
    return new Date(today.getFullYear(), today.getMonth()+1, 0);
    
}

/***************************************
 * Function related with Ajax requests *
 ***************************************/

/*TODO - this is bike, entity save is better to perform with 'sync' method*/
function entityCommit(button,url,successCallback) {
    var win = button.up('window');
    var form = win.down('form');
    var values = cleanEmptyFields(form.getValues());

    Ext.Ajax.request({
        url: url,
        params: values,
        success: function(response){
            var text = response.responseText;
            if (typeof successCallback !== 'undefined'){
                successCallback(text);
            }
        },
        failure: function(response){
            processAjaxFailure(response);
        }
    });
    win.close();
}
function postFromJson(values, url, next) {
    Ext.Ajax.request({
        url: url,
        method:'POST',
        params: values,
        success: function(response){
            var out = Ext.JSON.decode(response.responseText);
            if (out != null && next!= null) next(out.id);
        },
        failure: processAjaxFailure
    });
}
function entitySave(button,url,next) {
    var form = button.up('form');
    var values = form.getValues();
    form.setLoading('Идет сохранение ...');
    ajaxSave(values,url,function(responseText){
        form.setLoading(false);
        next(responseText);
    });
}
function ajaxSave(values,url,next) {
    Ext.Ajax.request({
        url: url,
        method:'POST',
        params: values,
        success: function(response){
            if ((typeof next !== 'undefined') && (next != null)) 
                next(response.responseText);
        },
        failure: processAjaxFailure
    });
}

function processAjaxFailure(response){
    var exception = Ext.JSON.decode(response.responseText,true);
    Ext.Msg.alert('Ошибка', 
                  exception == null ? 
                          response.responseText :
                          exception.exception.message);
}
function deleteFileOnServer(entityId, entityType, successCallback){
    Ext.Ajax.request({
        url:'file/delete_file',
        params:{id:entityId, type:entityType},
        method:'POST',
        success: successCallback
    });
}