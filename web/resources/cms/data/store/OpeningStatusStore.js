Ext.define('Cms.data.store.OpeningStatusStore', {
    extend: 'Ext.data.Store',
    id: 'openingStatusStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'openingStatusStore',
            proxy: {
                type: 'ajax',
                url: 'util/openingStatusList',
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
                    name: 'value'
                }
            ]
        }, cfg)]);
    }
});
