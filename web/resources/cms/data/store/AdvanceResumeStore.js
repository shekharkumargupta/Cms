Ext.define('Cms.data.store.AdvanceResumeStore', {
    extend: 'Ext.data.Store',
    id: 'advanceResumeStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'advanceResumeStore',
            proxy: {
                type: 'ajax',
                url: 'resume/advanceSearch',
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
                    name: 'pipelineId'
                },
                {
                    name: 'branchId'
                },
                {
                    name: 'branchName'
                },
                {
                    name: 'companyId'
                },
                {
                    name: 'companyName'
                },
                {
                    name: 'name'
                },
                {
                    name: 'contactNo'
                },
                {
                    name: 'email'
                },
                {
                    name: 'keySkills'
                },
                {
                    name: 'experience'
                },
                {
                    name: 'createdById'
                },
                {
                    name: 'createdByName'
                },
                {
                    name: 'createdAt'
                },
                {
                    name: 'pipelineStatusId'
                },
                {
                    name: 'pipelineStatusName'
                },
                {
                    name: 'dateOfBirth'
                },
                {
                    name: 'currentCompany'
                },
                {
                    name: 'currentLocation'
                },
                {
                    name: 'currentSalary'
                },
                {
                    name: 'currentDesignation'
                },
                {
                    name: 'remarks'
                },
                {
                    name: 'contentImage'
                }
            ]
        }, cfg)]);
    }
});
