Ext.define('Cms.data.store.OpeningStore', {
    extend: 'Ext.data.Store',
    id: 'openingStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'openingStore',
            proxy: {
                type: 'ajax',
                url: 'opening/clientOpeningList',
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
                    name: 'openingStatusId'
                },
                {
                    name: 'openingStatusName'
                },
                {
                    name: 'keySkills'
                },
                {
                    name: 'openingDetails'
                },
                {
                    name: 'createdDate'
                },
                {
                    name: 'createdById'
                },
                {
                    name: 'createdByName'
                },
                {
                    name: 'minLacs'
                },
                {
                    name: 'minThousands'
                },
                {
                    name: 'maxLacs'
                },
                {
                    name: 'maxThousands'
                },
                {
                    name: 'minExp'
                },
                {
                    name: 'maxExp'
                },
                {
                    name: 'minYrs'
                },
                {
                    name: 'minMonths'
                },
                {
                    name: 'maxYrs'
                },
                {
                    name: 'maxMonths'
                }
            ]
        }, cfg)]);
    }
});
