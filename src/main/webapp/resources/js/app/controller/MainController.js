Ext.define('app.controller.MainController', {
    extend: 'Ext.app.Controller',
    views:  ['ManagerBrowser', 'ManagerForm'],
    stores: ['Managers'],
    models: ['Manager'],
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
        });
    }
});
