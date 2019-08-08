Ext.define('Cms.data.store.CategoryStore', {
    extend: 'Ext.data.Store',
    id: 'categoryStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'categoryStore',
            proxy: {
                type: 'ajax',
                url: 'util/categoryList',
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
