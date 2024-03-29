/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

Commercial Usage
Licensees holding valid commercial licenses may use this file in accordance with the Commercial Software License Agreement provided with the Software or, alternatively, in accordance with the terms contained in a written agreement between you and Sencha.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/*!
* Ext JS Library 4.0
* Copyright(c) 2006-2011 Sencha Inc.
* licensing@sencha.com
* http://www.sencha.com/license
*/

// From code originally written by David Davis (http://www.sencha.com/blog/html5-video-canvas-and-ext-js/)

Ext.define(Cms.view.video.VideoWindow', {
    //extend: 'Ext.ux.desktop.Module',

    uses: [
        'Qms.view.video.Video'
    ],

    id:'video',
    windowId: 'video-window',

    init : function(){
        Ext.Msg.alert('Message','Hello');
        
        this.launcher = {
            text: 'About Ext JS',
            iconCls:'video',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var me = this; //, desktop = me.app.getDesktop(), win = desktop.getWindow(me.windowId);
        var win;
        if (!win) {
            win = win = Ext.create('widget.window',{
                id: me.windowId,
                title: 'About Ext JS',
                width: 740,
                height: 480,
                iconCls: 'video',
                animCollapse: false,
                border: false,

                layout: 'fit',
                items: [
                    {
                        xtype: 'video',
                        id: 'video-player',
                        src: [
                            // browser will pick the format it likes most:
                            { src: 'http://dev.sencha.com/desktopvideo.mp4', type: 'video/mp4' },
                            { src: 'http://dev.sencha.com/desktopvideo.ogv', type: 'video/ogg' },
                            { src: 'http://dev.sencha.com/desktopvideo.mov', type: 'video/quicktime' }
                        ],
                        autobuffer: true,
                        autoplay : true,
                        controls : true,
                        /* default */
                        listeners: {
                            afterrender: function(video) {
                                me.videoEl = video.video.dom;

                                if (video.supported) {
                                    me.tip = new Ext.tip.ToolTip({
                                        anchor   : 'bottom',
                                        dismissDelay : 0,
                                        height   : me.tipHeight,
                                        width    : me.tipWidth,
                                        renderTpl: [
                                            '<canvas width="', me.tipWidth,
                                                  '" height="', me.tipHeight, '">'
                                        ],
                                        renderSelectors: {
                                            body: 'canvas'
                                        },
                                        listeners: {
                                            afterrender: me.onTooltipRender,
                                            show: me.renderPreview,
                                            scope: me
                                        }
                                    }); // tip
                                }
                            }
                        }
                    }
                ],
                listeners: {
                    beforedestroy: function() {
                        me.tip = me.ctx = me.videoEl = null;
                    }
                }
            });
        }

        win.show();

        return win;
    }
    /*
    onTooltipRender: function (tip) {
        // get the canvas 2d context
        var el = tip.body.dom, me = this;
        me.ctx = el.getContext && el.getContext('2d');
    },

    renderPreview: function() {
        var me = this;

        if ((me.tip && !me.tip.isVisible()) || !me.videoEl) {
            return;
        }

        if (me.ctx) {
            try {
                me.ctx.drawImage(me.videoEl, 0, 0, me.tipWidth, me.tipHeight);
            } catch(e) {};
        }

        // 20ms to keep the tooltip video smooth
        Ext.Function.defer(me.renderPreview, 20, me);
    }*/
});

