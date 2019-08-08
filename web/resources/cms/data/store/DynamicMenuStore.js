Ext.define('Cms.data.store.DynamicMenuStore', {
    extend: 'Ext.data.TreeStore',
    id: 'dynamicMenuStore',
    autoLoad: true,
    proxy:{
        type: 'ajax',
        url: 'util/menuTree',
        //url: 'http://localhost:8080/Cms/resources/cms/data/json/treemenu.json',
        reader:{
            type: 'json',
            root: 'children',
            successProperty: 'success'
        }
    },    
    fields: [
        {
            name: 'id'
        },
        {
            name: 'text'
        },
        {
            name: 'iconCls'  
        },
        {
            name: 'leaf'
        }
     ]
    }
    
);
