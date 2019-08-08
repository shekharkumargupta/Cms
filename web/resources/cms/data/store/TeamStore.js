Ext.define('Cms.data.store.TeamStore', {
    extend: 'Ext.data.Store',
    id: 'teamStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'teamStore',
            proxy: {
                type: 'ajax',
                url: 'team/teamListByOpeningId',
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
                    name: 'branchId'
                },
                {
                    name: 'openingId'
                }
            ]
        }, cfg)]);
    }
});
