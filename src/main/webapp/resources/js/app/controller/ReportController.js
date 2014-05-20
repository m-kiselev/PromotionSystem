Ext.define('app.controller.ReportController', {
    extend: 'Ext.app.Controller',
    views:  ['ContractReportBrowser', 'SalaryReportBrowser'],
    stores: ['SalaryReportRecords', 'ContractReportRecords'],
    models: ['SalaryReportRecord', 'ContractReportRecord'],
    init: function(application) {
        this.control({
            'salaryreportbrowser button[action=xlsexport]': {
                click: function(button) {
                    var grid = button.up('grid');
                    var params = getParamsFromStoreAndFilters(grid, true);
                    window.location.href='/report/salary/export?' + Ext.Object.toQueryString(params);
                }
            }
        });
    }
});