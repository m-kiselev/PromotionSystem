Ext.define('app.controller.MainController', {
    extend: 'Ext.app.Controller',
    views:  ['ManagerBrowser', 'ManagerForm', 'HeadDepBrowser', 'HeadDepForm', 'ClientPanel','IndividClientBrowser', 'IndividClientForm',
             'LegalClientBrowser', 'LegalClientForm', 'ContractBrowser', 'ContractForm', 'SelectClientWindow', 'MonthPlanBrowser',
             'MonthPlanForm', 'ServiceContractBrowser', 'ServiceContractForm'],
    stores: ['Managers', 'HeadDeps', 'IndividClients', 'LegalClients', 'Contracts', 'EnumContractStatus',
             'MonthPlans', 'ServiceContracts', 'EnumServiceType'],
    models: ['Manager', 'HeadDep', 'IndividClient', 'LegalClient', 'Contract', 'MonthPlan', 'ServiceContract'],
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
            			var clientpanel = Ext.getCmp('mainclientpanel');
            			if (clientpanel != null) {
            			    reconfigureStoreInClientPanel(clientpanel);
            			}
            			var windowclientpanel = Ext.getCmp('windowclientpanel');
            			if (windowclientpanel != null) {
            			    reconfigureStoreInClientPanel(windowclientpanel);
            			}
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
            			var clientpanel = Ext.getCmp('mainclientpanel');
                        if (clientpanel != null) {
                            reconfigureStoreInClientPanel(clientpanel);
                        }
                        var windowclientpanel = Ext.getCmp('windowclientpanel');
                        if (windowclientpanel != null) {
                            reconfigureStoreInClientPanel(windowclientpanel);
                        }
            		});
            	}
            },
            'contractbrowser': {
                itemdblclick: function(grid, record) {
                    var view = Ext.widget('contractform');
                    view.setTitle('Договор');
                    view.down('form').loadRecord(record);
                    view.show();
                }
            },
            'contractform button[action=save]': {
                click: function(button) {
                    entityCommit(button,'contract/edit', function(){
//                        Ext.StoreMgr.lookup('Contracts').load();
                        var contractbrowser = Ext.getCmp('maincontractbrowser');
                        performContractBrowserFilters(contractbrowser);
                    });
                }
            },
            'monthplanbrowser': {
                itemdblclick: function(grid, record) {
                    var view = Ext.widget('monthplanform');
                    view.setTitle('План');
                    view.down('form').loadRecord(record);
                    view.show();
                }
            },
            'monthplanform button[action=save]': {
                click: function(button) {
                    entityCommit(button,'monthplan/edit', function(){
                        Ext.StoreMgr.lookup('MonthPlans').load();
                    });
                }
            },
            'servicebrowser': {
                itemdblclick: function(grid, record) {
                    var view = Ext.widget('servicecontractform');
                    view.setTitle('Договор на услуги');
                    view.down('form').loadRecord(record);
                    view.show();
                }
            },
            'servicecontractform button[action=save]': {
                click: function(button) {
                    entityCommit(button,'servicecontract/edit', function(){
                        Ext.StoreMgr.lookup('ServiceContracts').load();
                    });
                }
            }
        });
    }
});
