Ext.define('Cms.data.store.OpeningByTeamStore', {
    extend: 'Ext.data.Store',
    id: 'openingByTeamStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'openingByTeamStore',
            proxy: {
                type: 'ajax',
                url: 'opening/teamOpeningList',
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
