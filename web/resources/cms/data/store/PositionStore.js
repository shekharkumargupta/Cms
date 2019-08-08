Ext.define('Cms.data.store.PositionStore', {
    extend: 'Ext.data.Store',
    id: 'positionStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'positionStore',
            proxy: {
                type: 'ajax',
                url: 'util/positionList',
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
