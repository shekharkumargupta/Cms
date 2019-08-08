Ext.define('Cms.data.store.TeamMemberStore', {
    extend: 'Ext.data.TreeStore',
    id: 'teamMemberStore',
    autoLoad: true,
    proxy:{
        type: 'ajax',
        url: 'team/teamMember',
        //url: 'http://localhost:8080/Cms/resources/cms/data/json/treemenu.json',
        reader:{
            type: 'json',
            root: 'children',
            successProperty: 'success'
        }
        /*,
        listeners: {
            load: function(thisStore, rootnode, records, successful, eOpts){
                records.forEach(function(group){
                    group.cams().each(function(cam) {
                        group.appendChild({
                            text: cam.get('name'),
                            leaf: true
                        });
                    });
                });
            }
        }*/
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
