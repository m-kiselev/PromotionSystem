Ext.define('app.controller.MainController', {
    extend: 'Ext.app.Controller',
    views:  ['ManagerBrowser', 'ManagerForm', 'HeadDepBrowser', 'HeadDepForm', 'ClientPanel','IndividClientBrowser', 'IndividClientForm',
             'LegalClientBrowser', 'LegalClientForm'],
    stores: ['Managers', 'HeadDeps', 'IndividClients', 'LegalClients'],
    models: ['Manager', 'HeadDep', 'IndividClient', 'LegalClient'],
    init: function(application) {
        this.control({
            'managerbrowser': {
                itemdblclick: function(grid, record) {
                    var view = Ext.widget('managerform');
                    view.setTitle('Менеджер');
                    view.down('form').loadRecord(record);
//                    var companies = view.down('combo[name=companies]');
//                    companies.setValue(fetchIds(record.data.companies));
//                    companies.getStore().load();
                    view.show();
                }
            },
            'managerform button[action=save]': {
                click: function(button) {
                    entityCommit(button,'manager/edit', function(){
                        Ext.StoreMgr.lookup('Managers').load();
                    });
                }
            },
            'headdepbrowser': {
            	itemdblclick: function(grid, record) {
            		var view = Ext.widget('headdepform');
            		view.setTitle('Руководитель отдела');
            		view.down('form').loadRecord(record);
            		view.show();
            	}
            },
            'headdepform button[action=save]': {
            	click: function(button) {
            		entityCommit(button,'headdep/edit', function(){
            			Ext.StoreMgr.lookup('HeadDeps').load();
            		});
            	}
            },
            'indclientbrowser': {
            	itemdblclick: function(grid, record) {
            		var view = Ext.widget('indclientform');
            		view.setTitle('Физ. клиент');
            		view.down('form').loadRecord(record);
            		view.show();
            	}
            },
            'indclientform button[action=save]': {
            	click: function(button) {
            		entityCommit(button,'iclient/edit', function(){
            			var tabs = Ext.getCmp('mainPanel');
            			var clientpanel = tabs.down('clientpanel');
            			reconfigureStoreInClientPanel(clientpanel);
            		});
            	}
            },
            'legalclientbrowser': {
            	itemdblclick: function(grid, record) {
            		var view = Ext.widget('legalclientform');
            		view.setTitle('Юр. клиент');
            		view.down('form').loadRecord(record);
            		view.show();
            	}
            },
            'legalclientform button[action=save]': {
            	click: function(button) {
            		entityCommit(button,'lclient/edit', function(){
            			var tabs = Ext.getCmp('mainPanel');
            			var clientpanel = tabs.down('clientpanel');
            			reconfigureStoreInClientPanel(clientpanel);
            		});
            	}
            },
        });
    }
});
