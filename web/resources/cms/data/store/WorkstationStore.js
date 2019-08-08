Ext.define('Cms.data.store.WorkstationStore', {
    extend: 'Ext.data.Store',
    id: 'workstationStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'workstationStore',
            proxy: {
                type: 'ajax',
                url: 'workstation/workstationList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'companyId'
                },
                {
                    name: 'id'
                },
                {
                    name: 'name'
                },
                {
                    name: 'ipAddress'
                },
                {
                    name: 'loginId'
                }
            ]
        }, cfg)]);
    }
});
