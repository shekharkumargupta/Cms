Ext.define('Cms.data.store.OpeningByEmployeeStore', {
    extend: 'Ext.data.Store',
    id: 'openingByEmployeeStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'openingByEmployeeStore',
            proxy: {
                type: 'ajax',
                url: 'opening/employeeOpeningList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'openingId'
                },
                {
                    name: 'categoryId'
                },
                {
                    name: 'categoryName'
                },
                {
                    name: 'clientId'
                },
                {
                    name: 'clientName'
                },
                {
                    name: 'branchId'
                },
                {
                    name: 'branchName'
                },
                {
                    name: 'positionId'
                },
                {
                    name: 'positionName'
                },
                {
                    name: 'qualification'
                },
                {
                    name: 'location'
                },
                {
                    name: 'minimumSalary'
                },
                {
                    name: 'maximumSalary'
                },
                {
                    name: 'minimumExp'
                },
                {
                    name: 'maximumExp'
                },
                {
                    name: 'keySkill'
                },
                {
                    name: 'openingStatusId'
                },
                {
                    namme: 'openingStatusName'
                },
                {
                    name: 'openingDetails'
                }
            ]
        }, cfg)]);
    }
});
