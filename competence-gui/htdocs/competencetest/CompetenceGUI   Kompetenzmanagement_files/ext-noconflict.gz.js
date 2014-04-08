/**
 * Ext Sandboxing (aka "no-conflict")
 * 
 * About this file:
 *
 * This file is is loaded after ext-all.js, and attempts to modify Ext's behavior so
 * that elements created by Ext code will be sandboxed in an appropriate root element.
 * 
 * After loading this file, replace Ext.getRootEl() to return a custom root element.
 * For example:<pre><code>
Ext.getRootEl = function () {
    return document.getElementById("my-ext-sandbox");
};</code></pre>
 *
 * Known Issues:
 *  - The width and height of the sandbox element affects Window centering.
 */

/**
 * Gets the root element for the sandboxing of "loose" Ext elements.
 * 
 * Override this method to define a new sandbox element.
 */
Ext.getRootEl = function () {
    return document.body;
};


// Intercept DomHelper methods to prevent loose elements from accumulating in the first place:

var Ext_DomHelper_append     = Ext.DomHelper.append;
var Ext_DomHelper_insertHtml = Ext.DomHelper.insertHtml;

Ext.DomHelper.append = function ( el, o, returnElement ) {
    return Ext_DomHelper_append.call( this, ( el === document.body ? Ext.getRootEl() : el ), o, returnElement );
};

Ext.DomHelper.insertHtml = function ( where, el, html ) {
    var testEl = el;

    switch ( where ) {
        case "beforebegin":
        case "afterend":
            testEl = el.parentNode;
    }

    return Ext_DomHelper_insertHtml.call( this, where, ( testEl === document.body ? Ext.getRootEl() : el ), html );
};


// Intercept Component render() to prevent rendering directly to the BODY:

var Ext_Component_prototype_render = Ext.Component.prototype.render;

Ext.Component.prototype.render = function ( container, position ) {
    return Ext_Component_prototype_render.call( this, ( Ext.getDom(container) === document.body ? Ext.getRootEl() : container ), position );
};


// Special case for Ext.Msg.show(), which moves it's dialog to document.body every time!

var Ext_MessageBox_getDialog = Ext.MessageBox.getDialog;

Ext.MessageBox.getDialog = function () {
    var dlg = Ext_MessageBox_getDialog.apply( this, arguments );

    dlg.on( "beforeshow", function () {
        if ( this.el.dom.parentNode === document.body ) {
            Ext.getRootEl().appendChild( this.el.dom );
        }
    } );

    Ext.MessageBox.getDialog = Ext_MessageBox_getDialog;
    Ext_MessageBox_getDialog = null;

    return dlg;
};
 
// Render menu to our own Ext.getRootEl() rather than the document body if ct is not specified
var Ext_Menu_onRender = Ext.menu.Menu.prototype.onRender;
Ext.menu.Menu.prototype.onRender = function( ct, position) {
    return Ext_Menu_onRender.call( this, ( ct ? ct : Ext.get( Ext.getRootEl() ) ), position );
}

// Fix menu alignment when sub-menus are rendered to our ext-root
var Ext_Menu_constrainScroll = Ext.menu.Menu.prototype.constrainScroll;
Ext.menu.Menu.prototype.constrainScroll = function( y ) {

    if ( this.constrain === false ) {
        return y;
    }

    return Ext_Menu_constrainScroll.call( this, y );
}
