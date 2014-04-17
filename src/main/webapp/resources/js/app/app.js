Ext.Loader.loadScript('app/extra/Utils.js');
Ext.Loader.loadScript('app/extra/PlainWriter.js');

Ext.application({
    name: 'app',
    appFolder: 'app',
    controllers: ['MainController'],

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
                        '<p>Материальные</p><ul>' +
                        '<li onclick="openBrowser(\'maincontractbrowser\', \'contractbrowser\')">Договора</li>' +
                        '<li onclick="openBrowser(\'mainmonthplanbrowser\', \'monthplanbrowser\')">Планы на месяц</li></ul>' +
                        '<p>Нематериальные</p><ul>' +
                        '<li onclick="openBrowser(\'maincomplexbrowser\', \'complexbrowser\')">Комплексы</li></ul>' +
                        '<p>Отчеты</p><ul>' +
                        '<li onclick="openBrowser(\'mainsalaryreportbrowser\', \'salaryreportbrowser\')">Отчет по ЗП</li>' +
                        '<li onclick="openBrowser(\'maincontractreportbrowser\', \'contractreportbrowser\')">Отчет об одобрении договоров</li>' +
                        '<li onclick="openBrowser(\'mainheaddepreportbrowser\', \'headdepreportbrowser\')">Отчет для руководителей отделов</li>' +
                        '<li onclick="openBrowser(\'maincomplexreportbrowser\', \'complexreportbrowser\')">Отчет по комплексам</li>' +
                        '<li onclick="openBrowser(\'mainservicereportbrowser\', \'servicereportbrowser\')">Отчет по услугам</li></ul>' +
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
                autoDestroy: true
            }]
       });
   }
});

function addToMainTab(id, method) {
    var tabs = Ext.getCmp('mainPanel');
    var view = tabs.down('grid[id='+id+']');
    
    if (view == null) {
        view = method();
        tabs.add(view);
    } else {
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

/**
 * Added red '*' for requared field;
 * Example: {xtype: 'textfield', afterLabelTextTpl: markFieldRequired},
 */
var markFieldRequired = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';