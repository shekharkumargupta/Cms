Ext.define('Cms.data.store.ContactTypeStore', {
    extend: 'Ext.data.Store',
    id: 'contactTypeStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'contactTypeStore',
            proxy: {
                type: 'ajax',
                url: 'util/contactTypeList',
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
