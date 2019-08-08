Ext.define('Cms.data.store.ContactStore', {
    extend: 'Ext.data.Store',
    id: 'contactStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'contactStore',
            proxy: {
                type: 'ajax',
                url: 'clientContact/contactList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'contactId'
                },
                {
                    name: 'contactType'
                },
                {
                    name: 'contactTypeName'
                },
                {
                    name: 'firstName'
                },
                {
                    name: 'middleInitial'
                },
                {
                    name: 'lastName'
                },
                {
                    name: 'designation'
                },
                {
                    name: 'email'
                },
                {
                    name: 'phone1'
                },
                {
                    name: 'phone2'
                },
                {
                    name: 'dateOfBirth'
                },
                {
                    name: 'dateOfAnniversary'
                }
            ]
        }, cfg)]);
    }
});
