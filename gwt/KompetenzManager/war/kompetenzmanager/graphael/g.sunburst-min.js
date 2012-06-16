/*!
 * g.sunburst 0.1 - Sunburst diagrams
 * Needs g.Raphael 0.4.1 - Charting library, based on Raphaël
 *
 * Copyright (c)2011 zynamics GmbH (http://zynamics.com)
 * Licensed under the MIT (http://www.opensource.org/licenses/mit-license.php)
 * license.
 * 
 * 
 * See http://vis.stanford.edu/protovis/ex/sunburst.html for more
information. Rendering is done much the same way.

Use like this (include g.raphael.js and g.sunburst.js):

var r = Raphael("area");
var clusters = {
    "Cluster A": 50,
    "Cluster B": {
        "Sub-Cluster B-A": 25,
        "Sub-Cluster B-B": 75,
    }
    "Cluster C": {
        "Sub-Cluster C-A": 86,
        "Sub-Cluster C-B": 23,
        "Sub-Cluster C-C": {
            "Depth 3 - A": 50,
            "Depth 3 - B": 125
            WithoutQuotes: 14
        }
    }
};

sunburst = r.g.sunburst(350, 350, clusters, {
    rootLabel: "clusters",
    offsetAngle: 90,
    // With of each "ring"
    levelWidths: [25, 75, 50, 40, 30],
    // Colors array, by default used per slice
    colors: ["#E8420C", "#FF9600", "#FF0057", "#A90CE8", "#1D0DFF"],
    // You can also specify gradients instead of colors
    //gradients: ["45-#E8420C-#E832FF", "45-#FF9600-#FF96FF"),
    //    "45-#FF0057-#FF00FF", "45-#A90CE8-#A90CFF",
    //    "45-#1D0DFF-#1D0DFF"],
    // Draw each level in a different color
    //colorizeByLevel: true,
    // Gradient function, this needs the color array;
    // will likey be replaced by an eachSector() function
    onGradient: function(rootSect, depth) {
        return shadeColor(this.colors[rootSect % this.colors.length],
            0.1 * (depth + 2));
    },
    stroke: "#fff",
    strokewidth: 1
});

The diagram also supports the hover and the click event.

 *
 * Author: Christian Blichmann <christian.blichmann@zynamics.com>
 * acomodated by sgurin for raphael 2.1 and raphael4gwt.
 */
Raphael.fn.sunburst=function(h,g,o,a){a=a||{};var d=this,n=this.set(),l=this.set(),b=[];function i(s){if(b[s]){return b[s]}var p=a.onLevelWidth||function(t){return this.levelWidths?this.levelWidths[t]:(this.levelWidth||50)},r=0;for(var q=0;q<=s;q++){r+=p.call(a,q)}b[s]=r;return r}function f(v,u,C,y,I,G,K){var x=G-I,w=Math.abs(x)>=360;if(w){G=I+359.99}var q=Math.abs(x)>180,L=Math.PI/180,t=v+y*Math.cos(-I*L),H=u+y*Math.sin(-I*L),s=v+y*Math.cos(-G*L),F=u+y*Math.sin(-G*L),r=v+C*Math.cos(-I*L),E=u+C*Math.sin(-I*L),p=v+C*Math.cos(-G*L),D=u+C*Math.sin(-G*L),B=Math.abs(G+I)/2,z=(y+C)/2,A=v+z*Math.cos(-B*L),J=u+z*Math.sin(-B*L),M=d.path(["M",r,E,"A",C,C,0,+q,0,p,D,w?"M":"L",s,F,"A",y,y,0,+q,1,t,H,"Z"]);M.middle={x:A,y:J};M.mangle=B;M.ri=C;M.ro=y;return M.attr(K)}function k(p,q){var s={label:p,value:0,children:[]},u=0,t;for(var r in q){if(~~q[r]){s.value+=q[r];t={label:r,value:q[r],depth:0,children:[]}}else{t=k(r,q[r]);s.value+=t.value}u=Math.max(u,t.depth);s.children[s.children.length]=t}s.depth=u+1;return s}function m(p,q){var p=p%(a.colors.length||a.gradients.length);if(a.onGradient){return{gradient:a.onGradient.call(a,p,q)}}return a.gradients?{gradient:a.gradients[p]}:{fill:a.colors?a.colors[p]:Raphael.getColor()}}function e(v,z,q,A,p,B){q=q||0;A=A||v.value;p=p||(a.offsetAngle||0);B=B||0;var y,t=p,s=v.children,r=0;for(var u=0;u<s.length;u++){y=t;t+=s[u].value/A*360;var x=q==0?r:B,w=f(h,g,i(q),i(q+1),y,t,m(!a.colorizeByLevel?x:q,q)).attr({stroke:a.stroke||"#fff","stroke-width":a.strokewidth||1});w.level=q;w.value=s[u].value;w.label=s[u].label;z.push(w);e(s[u],z,q+1,A,y,x);r++}}Raphael.getColor.reset();var j=k(a.rootLabel,o);e(j,l);function c(p){return{sector:p,cx:h,cy:g,mx:p.middle.x,my:p.middle.y,mangle:p.mangle}}n.hover=function(r,q){q=q||function(){};for(var p=0;p<l.length;p++){(function(s){var t=c(s);s.mouseover(function(){r.call(t)});s.mouseout(function(){q.call(t)})})(l[p])}return this};n.click=function(q){for(var p=0;p<l.length;p++){(function(r){var s=c(r);r.click(function(){q.call(s)})})(l[p])}return this};n.push(l);n.series=l;return n};