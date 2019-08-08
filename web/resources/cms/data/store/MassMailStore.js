Ext.define('Cms.data.store.MassMailStore', {
    extend: 'Ext.data.Store',
    id: 'massMailStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'massMailStore',
            proxy: {
                type: 'ajax',
                url: 'massMail/listByOpeningAndSenderId',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'name'  
                },
                {
                    name: 'email'
                },
                {
                    name: 'status'
                },
                {
                    name: 'openingId'
                },
                {
                    name: 'positionName'
                },
                {
                    name: 'sentById'
                },
                {
                    name: 'senderName'
                },
                {
                    name: 'sentAt'
                },
                {
                    name: 'resumeId'
                },
                {
                    name: 'branchId'
                },
                {
                    name: 'companyId'
                }
            ]
        }, cfg)]);
    }
});
