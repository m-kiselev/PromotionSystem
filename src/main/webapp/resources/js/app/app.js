

Ext.application({
    name: 'AM',
    appFolder: 'app',

    launch: function() {
    Ext.create('Ext.container.Viewport', {
        layout: {
            type: 'border',
            padding: 5
        },

        items: [{
            region: 'north',
            xtype: 'toolbar',
            height: 35,
            items: [{
                    xtype: 'tbtext',
                    style: 'font-weight:bold;',
                    text: 'Система учета поощрений'
            }, {
                    xtype: 'tbfill'
            }]
        },{
            region: 'west',
            cls:'promsystem-bg',
            collapsible: true,
            title: 'Меню',
            split: true,
            width: '30%',
            minWidth: 100,
            minHeight: 140,
            width: 250,
            layout: {
                type:'vbox',
                padding:'5',
                align:'stretch'
            },
            items: [{
                flex:1,
                border:0,
                cls:'promsystem-bg',
                autoHeight:true,
                html:'<div class="leftmenu">' +
                    '<p>Информация о сотрудниках</p><ul>' +
                    '<li onclick="openBrowser(\'mainmanagerbrowser\', \'managerbrowser\')">Менеджеры</li>' +
                    '<li onclick="openBrowser(\'mainheaddepbrowser\', \'headdepbrowser\')">Руководители отделов</li></ul>' +
                    '<p>Отчеты</p><ul>' +
                    '<li onclick="openBrowser(\'mainsalarybrowser\', \'salarybrowser\')">Отчет по ЗП</li>' +
                    '<li onclick="openBrowser(\'maindreportsbrowser\', \'contractbrowser\')">Отчет об одобрении договоров</li>' +
                    '<li onclick="openBrowser(\'mainconsolreportsbrowser\', \'consolreportsbrowser\')">Отчет для руководителей отделов</li>' +
                    '<li onclick="openBrowser(\'mainfinancereportsbrowser\', \'financereportsbrowser\')">Отчет по комплексам</li>' +
                    '<li onclick="openBrowser(\'mainpacketreportsbrowser\', \'packetreportsbrowser\')">Отчет по услугам</li></ul>' +
                    '</div>'
                }]
        },{
            region: 'center',
            layout:'anchor',
            cls:'promsystem-bg',
            border: 0,
            xtype:'tabpanel',
            id:'mainPanel',
            activeTab: 0,
            autoDestroy: false
        }]
       });
   }
});

function addToMainTab(id, method) {
    var tabs = Ext.getCmp('mainPanel');
    var view = Ext.getCmp(id);

    if (view == null) {
        view = method();
        tabs.add(view);
    } else if (view.isHidden()) {
        // recreate hidden view for itemdbclick in entities browser
        view.destroy();
        view = method();
        tabs.add(view);
    }
    tabs.setActiveTab(view);
}

function openBrowser(browserName, widgetName) {
    addToMainTab(browserName, function() {
        var view = Ext.widget(widgetName, {
            id: browserName
        });
        view.getStore().load();
        return view;
    });
}

function openManagerPanel(btn) {
    Ext.Msg.alert('Byaj', 'adasd');
}

function openHeadDepsPanel(btn) {
    
}

function openContractsPanel(btn) {
    
}