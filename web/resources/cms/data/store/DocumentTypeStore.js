Ext.define('Cms.data.store.DocumentTypeStore', {
    extend: 'Ext.data.Store',
    id: 'documentTypeStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'documentTypeStore',
            proxy: {
                type: 'ajax',
                url: 'util/documentTypeList',
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
