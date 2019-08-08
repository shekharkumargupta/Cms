Ext.define('Cms.data.store.ClientStore', {
    extend: 'Ext.data.Store',
    id: 'clientStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'clientStore',
            proxy: {
                type: 'ajax',
                url: 'client/clientList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                /*
                {
                    name: 'categoryId'
                },
                {
                    name: 'categoryName'
                },
                */
                {
                    name: 'clientId'
                },
                {
                    name: 'clientName'
                },
                {
                    name: 'website'
                },
                {
                    name: 'street1'
                },
                {
                    name: 'street2'
                },
                {
                    name: 'countryId'
                },
                {
                    name: 'cityId'
                },
                {
                    name: 'countryName'
                },
                {
                    name: 'cityName'
                },
                {
                    name: 'zipCode'
                }
            ]
        }, cfg)]);
    }
});
