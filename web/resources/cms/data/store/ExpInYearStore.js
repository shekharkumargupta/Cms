Ext.define('Cms.data.store.ExpInYearStore', {
    extend: 'Ext.data.Store',
    id: 'expInYearStore',
    constructor: function(cfg) {
        var me = this;
        
        var data = [
            {"id":"0", "value":"0"},
            {"id":"1", "value":"1"},
            {"id":"2", "value":"2"},
            {"id":"3", "value":"3"},
            {"id":"4", "value":"4"},
            {"id":"5", "value":"5"},
            {"id":"6", "value":"6"},
            {"id":"7", "value":"7"},
            {"id":"8", "value":"8"},
            {"id":"9", "value":"9"},
            {"id":"10", "value":"10"},
            {"id":"11", "value":"11"},
            {"id":"12", "value":"12"},
            {"id":"13", "value":"13"},
            {"id":"14", "value":"14"},
            {"id":"15", "value":"15"},
            {"id":"16", "value":"16"},
            {"id":"17", "value":"17"},
            {"id":"18", "value":"18"},
            {"id":"19", "value":"19"},
            {"id":"20", "value":"20"},
            {"id":"21", "value":"21"},
            {"id":"22", "value":"22"},
            {"id":"23", "value":"23"},
            {"id":"24", "value":"24"},
            {"id":"25", "value":"25"},
            {"id":"26", "value":"26"},
            {"id":"27", "value":"27"},
            {"id":"28", "value":"28"},
            {"id":"29", "value":"29"},
            {"id":"30", "value":"30"},
            {"id":"31", "value":"31"},
            {"id":"32", "value":"32"},
            {"id":"33", "value":"33"},
            {"id":"34", "value":"34"},
            {"id":"35", "value":"35"},
            {"id":"36", "value":"36"},
            {"id":"37", "value":"37"},
            {"id":"38", "value":"38"},
            {"id":"39", "value":"39"},
            {"id":"40", "value":"40"}
        ]
        
        
        
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,    
            data: data,
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
