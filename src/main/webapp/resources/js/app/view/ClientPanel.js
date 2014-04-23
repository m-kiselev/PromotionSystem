Ext.define('app.view.ClientPanel' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.clientpanel',
    closable: true,
    title: 'Клиенты',
    anchor: '100% 100%',
    layout: 'anchor',
    tbar:[{
        xtype: 'radiogroup',
        items: [
            { boxLabel : 'Физ. лицо', width: 90, name: 'rb_client', inputValue : 1, checked: true},
            { boxLabel : 'Юр. лицо',  width: 80, name: 'rb_client', inputValue : 2}
        ],
        listeners: {
            change: function(me, newValue) {
                var clientpanel =  me.up('clientpanel');
                var grid = clientpanel.down('grid');
                grid.destroy();
                var newGrid;
                if (newValue.rb_client == 1) {
                    newGrid = Ext.create('app.view.IndividClientBrowser', {anchor: '100% 100%'});
                } else {
                    newGrid = Ext.create('app.view.LegalClientBrowser', {anchor: '100% 100%'});
                }
                clientpanel.add(newGrid);
                reconfigureStoreInClientPanel(clientpanel);
            }
        }
    }, {
        xtype: 'button',
        text: 'Добавить',
        handler: function() {
            if (this.up('clientpanel').down('radiogroup').getValue().rb_client == 1) {
                var view = Ext.widget('indclientform');
                var form = view.down('form');
                view.show();
            } else {
                var view = Ext.widget('legalclientform');
                var form = view.down('form');
                view.show();
            }
        }
    }, {
        xtype: 'button',
        text: 'Удалить',
        handler: function(button) {
            var grid = button.up('clientpanel').down('grid');
            var recs = grid.getSelectionModel().getSelection();
            if (recs.length > 0) {
                var url;
                if (this.up('clientpanel').down('radiogroup').getValue().rb_client == 1) {
                    url = '/iclient/delete';
                } else {
                    url = '/lclient/delete';
                }
                Ext.Msg.show({
                    title:'Удалить клиента?',
                    msg: 'Вы уверены что хотите удалить?',
                    buttons: Ext.Msg.YESNO,
                    buttonText:{yes:'Да', no:'Нет'},
                    icon: Ext.Msg.QUESTION,
                    fn: function(btn) {
                        if (btn === 'yes') {
                            var delRec = recs[0];
                            ajaxSave({id: delRec.data.id},
                            url,
                            function(resp) {
                                grid.getStore().reload();
                            });
                        }
                    }
                });
            } else {
                Ext.Msg.alert('Инфо', 'Необходимо выбрать клиента.');
            }
        }
    }],
    items: { xtype: 'indclientbrowser', anchor: '100% 100%'}
});

function reconfigureStoreInClientPanel(_this){
    var store;
    if (_this.down('radiogroup').getValue().rb_client == 1) {
        store = Ext.create('app.store.IndividClients');
    } else {
        store = Ext.create('app.store.LegalClients');
    }
    store.load();
    _this.down('grid').reconfigure(store);
    _this.down('pagingtoolbar').bindStore(store);
}