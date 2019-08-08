Ext.define('Cms.data.store.CountryStore', {
    extend: 'Ext.data.Store',
    id: 'countryStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'countryStore',
            proxy: {
                type: 'ajax',
                url: 'util/countryList',
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
