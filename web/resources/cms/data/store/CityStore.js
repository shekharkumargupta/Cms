Ext.define('Cms.data.store.CityStore', {
    extend: 'Ext.data.Store',
    id: 'cityStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'cityStore',
            proxy: {
                type: 'ajax',
                url: 'util/cityList',
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
                },
                {
                    name: 'countryId'
                }
            ]
        }, cfg)]);
    }
});
