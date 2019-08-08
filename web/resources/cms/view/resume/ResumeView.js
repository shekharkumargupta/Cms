Ext.define('Cms.view.resume.ResumeView', {
    extend: 'Ext.panel.Panel',

    height: 470,
    layout: {
        type: 'border'
    },
    title: 'Opening Details',
    
    openingId: '0',
    resumeId: '0',
    companyId: '0',
    
    setOpeningId: function(openingId){
        this.openingId = openingId;
    },
    getOpeningId: function(){
        return this.openingId;
    },
    
    
    setResumeId: function(resumeId){
        this.resumeId = resumeId;
    },
    getResumeId: function(){
        return this.resumeId;
    },
    
    
    setCompanyId: function(companyId){
        this.companyId = companyId;
    },
    getCompanyId: function(){
        return this.companyId;
    },
    
    
    setBranchId: function(branchId){
        this.branchId = branchId;
    },
    getBranchId: function(){
        return this.branchId;
    },
    
    setDetails: function(resumeId, companyId, branchId, details, remarks){
        this.resumeId = resumeId;
        this.companyId = companyId;
        this.branchId = branchId;
        var data = {
            resumeId: resumeId,
            companyId: companyId,
            branchId: branchId,
            details: details,
            remarks: remarks
        }
        var panel = Ext.getCmp(this.resumeId);
        panel.removeAll(true);
        var tpl = Ext.create('Ext.Template', 
            '<div style="text-align: right;">'+
            '<a href="/Cms/resumes/{companyId}/{branchId}/{resumeId}.doc"><img src="../images/word.jpg" height=25 width=25"></a>'+
            '</div>'+
            '<br/>'+
            '<div style="background-color:mediumorchid; color:white; padding-left: 5px; padding-top: 5px; padding-right: 5px; padding-bottom: 5px;">'+
            '<b>Remarks:</b><br> {remarks}'+
            '</div>'+    
            '<div style="padding-left: 40px; padding-top: 40px; padding-right: 40px; padding-bottom: 40px;">'+
            '{details}'+
            '</div>'
        )
        tpl.overwrite(panel.body, data);
        panel.doComponentLayout();    
    },
    
    createWindow: function(extComponent, title, height, width){
        extComponent.region = 'center';
        var win;
        if(!win){
            win = Ext.create('widget.window',{
                title: title,
                layout: 'border',
                closeAction: 'destroy',
                items:[extComponent],
                autoHeight: true,
                width: width,
                modal: false,
                //minimizable: true,
                maximizable: true,
                closable:true,
                collapsible:true,
                animCollapse: true,
                //maximized: true,
                //y: 20,
                listeners: {
                    maximize: function(){
                        var viewportHeight = Ext.getCmp('mainViewport').height;
                        //Ext.Msg.alert('Message',viewportHeight);
                        win.setHeight(viewportHeight-32);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },
    
    initComponent: function() {
        var me = this;
        //Ext.Msg.alert('Resume Id', me.getResumeId()+" "+me.getOpeningId()+"  "+me.getBranchId()+" "+me.getCompanyId());
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    title: 'Opening Details',
                    region: 'center',
                    collapsible: true,
                    titleCollapse: true,
                    collapseDirection: 'top',
                    split: true,
                    height: 160,
                    items: [
                        {
                            xtype: 'panel',
                            id: me.getResumeId(),
                            //title: 'Opening dEtails',
                            height: 470,
                            split: true,
                            padding: 5,
                            border: false,
                            autoScroll: true
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            layout: {
                                pack: 'start',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Send',
                                    iconCls: 'pipelineProcessingIcon',
                                    handler: function(){
                                        var component = Ext.create('Cms.view.pipeline.PipelineResume',{

                                        });
                                        component.setResumeId(me.getResumeId());
                                        me.createWindow(component, "Resume Id: "+component.getResumeId(), 600, 600);
                                    }
                                },
                                '-'
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});