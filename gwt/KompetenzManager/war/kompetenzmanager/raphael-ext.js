/*
 * raphael extensions made by sgurin.
 * 
 * 
 * new shapes: 
 * 
 * printLetters() - print() that return a set of letters and support for text onpath.
 * 
 * 
 * set operations: 
 * 
 * union, intersect, etc
 * 
 * 
 * transformation / filters : 
 * 
 * the only one that support both svg and vml containers are blur() and emboss(). the
 * rest only support SVG, like convolution, color matrix, etc.
 *  
 * @author: sgurin
 */

(function() {
	/**
	 * do the job of putting all letters in a set returned bu printLetters in a path
	 * @param p - can be a rpahael path obejct or string
	 */
	var _printOnPath = function(text, paper, p) {
		if(typeof(p)=="string")
			p = paper.path(p).attr({stroke: "none"});
		for ( var i = 0; i < text.length; i++) {		
			var letter = text[i];
			var newP = p.getPointAtLength(letter.getBBox().x);
			var newTransformation = letter.transform()+
			 	"T"+(newP.x-letter.getBBox().x)+","+
		        (newP.y-letter.getBBox().y-letter.getBBox().height);		
			//also rotate the letter to correspond the path angle of derivative
		    newTransformation+="R"+
		        (newP.alpha<360 ? 180+newP.alpha : newP.alpha);
		    letter.transform(newTransformation);
		}
		text._rm_topathPath=p;
	};
	
	/** print letter by letter, and return the set of letters (paths), just like the old raphael print() method did. */
	Raphael.fn.printLetters = function(x, y, str, font, size, 
			letter_spacing, line_height, onpath) {
		letter_spacing=letter_spacing||size/1.5;
		line_height=line_height||size;
		this.setStart();
		var x_=x, y_=y;
		for ( var i = 0; i < str.length; i++) {
			if(str.charAt(i)!='\n') {
				var letter = this.print(x_,y_,str.charAt(i),font,size);
				x_+=letter_spacing;				
			}
			else {
				x_=x;
				y_+=line_height;
			}
		}
		var set = this.setFinish();
		if(onpath) {
			_printOnPath(set, this, onpath);
		}
		return set;
	};	
	
})();


/* set operations - very inefficient because raphael sets are arrays and not objects (maps-sets) */
(function() {
	/**
	 * so users can change the meaning of belonging to a set
	 * @param s1
	 * @param s2
	 * @returns
	 */
	Raphael.st._elEquals=function(s1, s2) {
		return s1==s2;
	};
	/**
	 * 
	 * @param el
	 * @returns
	 */
	Raphael.st.contains = function(el) {
		for(var i = 0; i < this.length; i++) 
			if(Raphael.st._elEquals(this[i], el))
				return true;		
		return false;
	};
	Raphael.st.containsAll = function(other) {
		//TODO
	};
	/**
	 * @returns a new set that is the intersection of this and the other set param
	 */
	Raphael.st.intersect = function(other) {
		if(!other)return[];
		var ret = this.paper.set();
		for(var i = 0; i < this.length; i++) {
			if(other.contains(this[i]))
				ret.push(this[i]);
		}
		return ret;
	};	
	/**
	 * @returns this plus other set els added
	 */
	Raphael.st.add = function(other) {
		if(!other)return this;
		for(var i = 0; i < other.length; i++) 
			if(!this.contains(other[i]))
				this.push(other[i]);
		return this;
	};
	/**
	 * @returns this with other set els removed
	 */
	Raphael.st.substract = function(other) {
		if(!other)return this;
		for(var i = 0; i < other.length; i++) 
			if(this.contains(other[i]))
				this.exclude(other[i]);
		return this;
	};	
	/**
	 * @return this set with all elements for which f return true removed.
	 */
	Raphael.st.filter = function(f) {
		var set = this;
		this.forEach(function(el, idx) {
			var result = f(el, idx);
			result = typeof(result)=="object" ? (result+"")=="true" : result;
			if(result) {
				set.exclude(el);
			}
			return true;
		});
		return set;
	};
	
	/**
	 * return the first non-set shape children of this set. will return 
	 * null if no non-set children shape is found.
	 */

	Raphael.el.firstShape=function() {
		return this;
	};
	Raphael.st.firstShape = function() {
		var nonSetShape = null;
		this.forEach(function(el, idx) {
			if(el.type!="set") {
				nonSetShape=el;
				return false; //breaks foreach
			}
			else {			
				nonSetShape = el.firstShape();
				if(nonSetShape==null)
					return true;
				else
					return false;
			}
		});
		return nonSetShape;
	};
	Raphael.st.item = function(index) {
		var i = 0;
		var shape = null;
		this.forEach(function(s){
			if(i==index) {
				shape=s;
				return false;
			}
			i++;
			return true;
		});
		return shape;
	}
	Raphael.el.print = function() {
		return this.type?this.type:"undef";
	}
	Raphael.st.print = function() {
		var s = "Set(";
		this.forEach(function(shape){
			s+=shape.print()+", ";			
		});
		return s+")";
	}

	
})();




(function(){
	Raphael.el.isRemoved = function() {
		return this.node.parentNode==null;
	};	
	Raphael.st.isRemoved = function() {
		return true;
	};	
})();

/**WRITE functions - export the paper and shapes to object and json string 
 * as described in Paper.add() 
 * explanation: raphaeljs has paper.add() for importing 
 * shapes in json format, but do not have any toJSON for exporting in the same way. 
 * @author: sgurin
 */
(function(){
	
	var shapeToObject = function(shape,arr) {
		if(shape.type=="set") {
			var aSet = shape;
			shape.forEach(function(shape) {
				shapeToObject(shape, arr)
			});
			return;
		}
		else {
			var obj=shape.attr();
			obj["type"]=shape.type;
			arr.push(obj);
		}
	};
	/**
	 * @return an array on objects as described in Paper.add()
	 */
	Raphael.el.writeToObject = function() {
		var a = [];
		shapeToObject(this, a);
		return a;
	};
	/**
	 * @return an json string with structure described in Paper.add()
	 */
	Raphael.el.writeToString = function() {
		var a = this.writeToObject();
		var sb = [];
		for(var i = 0; i<a.length; i++) {
			var shapeDesc = a[i], sb2=[];
			sb2.push('"type":'+"\""+shapeDesc.type+"\"");
			for(var attrName in shapeDesc) {
				val = (shapeDesc[attrName]+"").replace(/\"/g, "\\\"");
				sb2.push('"'+attrName+'":"'+val+"\"");
			}
			var s = "{"+sb2.join(",")+"}";
			if(i<a.length-1) 
				s+=","
			sb.push(s);
		}
		return sb.join(",")
	};
	Raphael.st.writeToObject = function() {
		var a = [];
		this.forEach(function(shape) {
			a = a.concat(shape.writeToObject());			
		});
		return a;
	};
	/**
	 * @return a json object just like expected by paper.add
	 */	 
	Raphael.st.writeToString = function() {
		var a = [];
		this.forEach(function(shape) {
			a = a.concat(shape.writeToString());			
		});
		return "["+a.join(",")+"]";
	};
	/**
	 * @return a json object just like expected by paper.add
	 */	 
	Raphael.fn.writeToObject = function() {
		var a = [];
		this.forEach(function(shape) {
			a = a.concat(shape.writeToObject());			
		});
		return a;
	};
	/**
	 * @return a json object just like expected by paper.add
	 */	 
	Raphael.fn.writeToString = function() {
		var a = [];
		this.forEach(function(shape) {
			a = a.concat(shape.writeToString());			
		});
		return "["+a.join(",")+"]";
	};
})();	 






///* path editor */
//(function(){
//
///**
//* install a new free path editor- only applicable for paths 
//@return an object {allShapes: set w all handlers.}
//*/	 
//Raphael.el.pathEditor = function() {
//	if(this.type!="path"||!this.attr("path")||this.attr("path")=="")
//		return null;
//	
//	this.pe={};
//	this.pe.createHandler(cmd, cmdIdx) {
//		
//	}
//	var pe = 
//		this.pe.allShapes = //ref from shape to the FPE object
//			this.paper.set();
//	
//	var str = this.attr("path"), cmds = Raphael.parsePathString(str);
//	for ( var i = 0; i < cmd.length; i++) {
//		
//	}
//	return pe;
//};
//
//})();	 



///* attribute change notifications. use like this:
// * circle1.addAttrChangeListener("transform", function(attrName, oldValue, newValue){
// * ...
// * })
// *  */
//(function() {
//	Raphael.st._attrChangeListeners = Raphael.el._attrChangeListeners = {};
//	Raphael.st.addAttrChangeListener = Raphael.el.addAttrChangeListener = 
//		function(attr, tl) {
//		if(!this._attrChangeListeners[attr])
//			this._attrChangeListeners[attr]=[];
//		this._attrChangeListeners[attr].push(tl);
//	};
//	
//	/* now the trik part - override the attr() function - 
//	 * no need to do it for Set because they call individual shape attr() */ 
////	Raphael.st.___attr = Raphael.st.attr;
//	Raphael.el.___attr = Raphael.el.attr;
//	Raphael.el.attr = function(name, value) {
//		if ( name != null && Raphael.is(name, "object") ) {
//			var params = name;
//			for(var attr in params) {
//				var listeners = this._attrChangeListeners[attr];
//				if(listeners) {
//					for ( var i = 0; i < listeners.length; i++) {
//						listeners[i](attr, this.___attr(attr), params[attr]);
//					}
//				}
//			}
//		}
//		else if (name!=null && value!=null) {
//			var listeners = this._attrChangeListeners[attr];
//			if(listeners) {
//				for ( var i = 0; i < listeners.length; i++) {
//					listeners[i](name, this.___attr(name), value);
//				}
//			}
//		}
//		return this.___attr(name, value);
//	};	
//	Raphael.st.attr = function (name, value) {
//		//extension - aSet.attr("transform") will return this set's first el attr transform
//		if(name && !value && Raphael.is(name, "string")) {
//			
//		}
//		else if (name && R.is(name, array) && R.is(name[0], "object")) {
////			return this.___attr(name, value);
//            for (var j = 0, jj = name.length; j < jj; j++) {
//                this.items[j].attr(name[j]);
//            }
//        } 
//        else {
//            for (var i = 0, ii = this.items.length; i < ii; i++) {
//                this.items[i].attr(name, value);
//            }
//        }
//        return this;
//    };
//})();




//blur plugin: use like shape1.blur(2);
(function () {
  if (Raphael.vml) {
      var reg = / progid:\S+Blur\([^\)]+\)/g;
      Raphael.el.blur = function (size) {
          var s = this.node.style,
              f = s.filter;
          f = f.replace(reg, "");
          if (size != "none" && size!=0 && size!="0") {
              s.filter = f + " progid:DXImageTransform.Microsoft.Blur(pixelradius=" + (+size || 1.5) + ")";
              s.margin = Raphael.format("-{0}px 0 0 -{0}px", Math.round(+size || 1.5));
          } else {
              s.filter = f;
              s.margin = 0;
          }
      };
  } else {
      var $ = function (el, attr) {
          if (attr) {
              for (var key in attr) if (attr.hasOwnProperty(key)) {
                  el.setAttribute(key, attr[key]);
              }
          } else {
              return document.createElementNS("http://www.w3.org/2000/svg", el);
          }
      };
      Raphael.el.blur = function (size) {
          // Experimental. No WebKit support.
    	  if (size != "none" && size!=0 && size!="0") {
              var fltr = $("filter"),
                  blur = $("feGaussianBlur");
              fltr.id = "r" + (Raphael.idGenerator++).toString(36);
              $(blur, {stdDeviation: +size || 1.5});
              fltr.appendChild(blur);
              this.paper.defs.appendChild(fltr);
              this._blur = fltr;
              $(this.node, {filter: "url(#" + fltr.id + ")"});
          } else {
              if (this._blur) {
                  this._blur.parentNode.removeChild(this._blur);
                  delete this._blur;
              }
              this.node.removeAttribute("filter");
          }
      };
      Raphael.st.blur =  function(size) {
		for ( var i = 0; i < this.items.length; i++) {
			this.items[i].blur(size);
		}
	  };
  }
})();
//emboss plugin, use like shape1.emboss(1.0)
(function () {
  if (Raphael.vml) {
      var reg = / progid:\S+Emboss\([^\)]+\)/g;
      Raphael.el.emboss = function (bias) {
          var s = this.node.style,
              f = s.filter;
          f = f.replace(reg, "");
          if (bias != "none") {
              s.filter = f + " progid:DXImageTransform.Microsoft.Emboss(bias=" + (bias || 0.0) + ")";
              //s.margin = Raphael.format("-{0}px 0 0 -{0}px", Math.round(+size || 1.5));
          } else {
              s.filter = f;
              //s.margin = 0;
          }
      };
  } else {        
      Raphael.el.emboss = function (bias) {
    	  
      	if(!bias ||bias=="0") {
      		return this.convolveClear(Raphael.el.emboss.EMBOSS_TRANS_NAME);
      	}
      	else {
      		var factor = bias;        				
      		var embossKernel =
      			
      			[	factor*-1,	0, 		0, 
      				0,			1, 		0,
      				0,			0, 		factor]; 
      			
      		return this.convolve(Raphael.el.emboss.EMBOSS_TRANS_NAME, 
      			3, embossKernel, 1.0, bias, null);
      	}
      };
      Raphael.st.emboss =  function(bias) {
      	for ( var i = 0; i < this.items.length; i++) {
			this.items[i].emboss(bias);
		}
      };
      Raphael.el.emboss.EMBOSS_TRANS_NAME="embossTransformation";
  }
})();



/*
 * pixel convolution tranformation (only svg). only squeare kernels allowed.
 * you can add many convolutions. Their name must be a valid html id. For example:
 * image.convolve("emboss1", 3, 3, [0.4,0,0,0,1,0,0,0,0.5])
 * image.convolve("conv2", 2,2,[1,2,2,3])
 * image.convolveClear("emboss1")
 * @author: SebastiÃ¡n Gurin <sgurin @ montevideo  DOT com  DOT uy>
 */
(function () {
    if (Raphael.vml) {    	
    	//TODO
    	Raphael.el.convolve = function (convolutionName, kernelXSize, kernel, 
        		divisor, bias,  preserveAlpha) {
    		return this;
    	};
    	 Raphael.el.convolveClear = function (convolutionName) {
    		 return this;
    	 };
    } 
    else {
        var $ = function (el, attr) {
            if (attr) {
                for (var key in attr) if (attr.hasOwnProperty(key)) {
                    el.setAttribute(key, attr[key]);
                }
            } else {
                return document.createElementNS("http://www.w3.org/2000/svg", el);
            }
        };
        Raphael.el.convolve = function (convolutionName, kernelXSize, kernel, 
        		divisor, bias,  preserveAlpha) {
        	
//        	debugger;
        	//convolution configuration
            var convolveConfig = {
            	order: kernelXSize+"",
            	kernelMatrix: kernel.join(" ")
            };
            if(divisor) convolveConfig["divisor"]=divisor;
            if(bias) convolveConfig["bias"]=bias;
            if( preserveAlpha) convolveConfig["preserveAlpha"]= preserveAlpha;            
            else convolveConfig["preserveAlpha"]="true";
            
        	//if not exists create a main filter element
        	if(this.mainFilter==null) {
        		this.mainFilter = $("filter");
        		this.mainFilter.id = "convolutionMainFilter"
                this.paper.defs.appendChild(this.mainFilter);
        		$(this.node, {filter: "url(#convolutionMainFilter)"});
        	}
        	
            //create or gets the filter primitive element feConvolveMatrix:
            var convolveFilter = this._convolutions==null?null:this._convolutions[convolutionName];
            if(convolveFilter==null){
            	convolveFilter = $("feConvolveMatrix");
            }
            this.mainFilter.appendChild(convolveFilter);
            
            //apply configuration and register
            $(convolveFilter, convolveConfig);  
            if(! this._convolutions)
            	 this._convolutions={}
            this._convolutions[convolutionName] = convolveFilter;
            
	        return this;
        };
        Raphael.st.convolve =  function(convolutionName, kernelXSize, kernel, 
        		divisor, bias,  preserveAlpha) {
        	for ( var i = 0; i < this.items.length; i++) {
				this.items[i].convolve(convolutionName, kernelXSize, kernel, 
		        		divisor, bias,  preserveAlpha);
			}
        };
        Raphael.el.convolveClear = function (convolutionName) {
        	if (this._convolutions!=null && this._convolutions[convolutionName]!=null &&
        			this.mainFilter!=null) {   
        		try {
        			this.mainFilter.removeChild(this._convolutions[convolutionName]);
        			this._convolutions[convolutionName]=null;
        		}catch(ex){alert("error removing filter for conv named : "+convolutionName);}
        		
            }  
            return this;
        };
        Raphael.st.convolveClear =  function(convolutionName) {
        	for ( var i = 0; i < this.items.length; i++) {
				this.items[i].convolveClear(convolutionName);
			}
        };
        Raphael.el.convolveClearAll=function() {
        	if(this.mainFilter!=null) {
	        	this.paper.defs.removeChild(this.mainFilter);
	        	this.mainFilter=null;
	        	this._convolutions=null;
	        	this.node.removeAttribute("filter");
        	}
        };
        Raphael.st.convolveClearAll =  function() {
        	for ( var i = 0; i < this.items.length; i++) {
				this.items[i].convolveClearAll();
			}
        };
    }
    
})();

//and now some convolution based transformations


/* emboss2 support constant orientated embossing using prewittCompassGradient
 * 
 * orientation can be any of 
 * "north", "north-east",  "east", "sourth east","south", "south west", "west", 
 * "worth west"
 * 
 * factor can be an integer >1.0
 * 
 * divisor and bias are optional
 * */
(function () {
Raphael.el.emboss2=function(factor, orientation, divisor, bias) {
	divisor=divisor||1.0;
	bias=bias||100;
	
	var k = null;
	if(orientation=="north") {
		k=[
			-factor, 0, 0, 
			0, 1, 0, 
			0, 0, factor];
	}
	else if(orientation=="north-east") {
		k=[
			0, 0, factor, 
			0, 1, 0, 
			-factor, 0, 0];
	}
	else if(orientation=="east") {
		k=[
			0, 0, 0, 
			-factor, 1, factor,
			0, 0, 0];
	}
	else if(orientation=="south-east") {
		k=[
			-factor, 0, 0, 
			0, 1, 0, 
			0, 0, factor];
	}
	else if(orientation=="south") {
		k=[
			0, -factor, 0, 
			0, 1, 0, 
			0, factor, 0];
	}
	else if(orientation=="south-west") {
		k=[
			0, 0, -factor,
			0, 1, 0, 
			factor, 0, 0];
	}
	else if(orientation=="west") {
		k=[
			0, 0, 0, 
			factor, 1, -factor,
			0, 0, 0];
	}
	else if(orientation=="north-west") {
		k=[
			factor, 0, 0, 
			0, 1, 0, 
			0, 0, -factor];
	}	
	if(k){
		this.convolve("prewittCompassGradient1", 3,  k, divisor, bias);
	}	
	return this;
}
})();




(function () {
var multiplyFor=function(data, n, x) {
	var D = [];
	for(var i = 0; i< n; i++)
		for(var j = 0; j<n; j++)
			D[i*n+j] = data[i*n+j]*x;
	return D;
}; 
Raphael.el.sobel=function(size, multiplier, divisor, bias) {
	divisor=divisor||1.0;
	bias=bias||1.0;

	if(size==1) {
		this.convolve("sobel1", 3, multiplyFor([
			-1, 0, +1,
			-2, 0, +2,
			-1, 0, +1], 
			3, multiplier), divisor, bias);			
		this.convolve("sobel2", 3, multiplyFor([
			+1, +2, +1,
			0, 0, 0,
			-1,-2,-1], 
			3, multiplier), divisor, bias);	
	}
	else if(size==2) {
		this.convolve("sobel1", 4, multiplyFor([
			-1, 0, 0, 1,
			-2, 0, 0, 2,
			-2, 0, 0, 2,
			-1, 0, 0, 1], 
			4, multiplier), divisor, bias);
		this.convolve("sobel2", 4, multiplyFor([
			+1, +2, +2, +1,
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 0, 0, 0,
			-1, -2, -2, -1], 
			4, multiplier), divisor, bias);	
	}		
	else if(size==3) {
		this.convolve("sobel1", 5, multiplyFor([
			-1, 0, 0, 0, 1,
			-2, 0, 0, 0, 2,
			-3, 0, 0, 0, 3,
			-2, 0, 0, 0, 2,
			-1, 0, 0, 0, 1], 
			5, multiplier), divisor, bias);			
		this.convolve("sobel2", 5, multiplyFor([
			+1, +2, +3, +2, +1,
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,
			-1, -2, -3, -2, -1], 
			5, multiplier), divisor, bias);		
	}
	else if(size==4) {
		this.convolve("sobel1",6, multiplyFor([
			-1, 0, 0, 0, 0, 1,
			-2, 0, 0, 0, 0, 2,
			-3, 0, 0, 0, 0, 3,
			-3, 0, 0, 0, 0, 3,
			-2, 0, 0, 0, 0, 2,
			-1, 0, 0, 0, 0, 1], 
			6, multiplier), divisor, bias);
		this.convolve("sobel2", 6, multiplyFor([
			+1, +2, +3, +3, +2, +1,
			0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0,
			-1, -2, -3, -3, -2, -1], 
			6, multiplier), divisor, bias);
	}
	else if(size==5) {
		this.convolve("sobel1",7, multiplyFor([
			-1, 0, 0, 0, 0, 0, 1,
			-2, 0, 0, 0, 0, 0, 2,
			-3, 0, 0, 0, 0, 0, 3,
			-4, 0, 0, 0, 0, 0, 4,
			-3, 0, 0, 0, 0, 0, 3,
			-2, 0, 0, 0, 0, 0, 2,
			-1, 0, 0, 0, 0, 0, 1], 
			7, multiplier), divisor, bias);			
		this.convolve("sobel2", 7, multiplyFor([
			+1, +2, +3, +4, +3, +2, +1,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 
			-1, -2, -3, -4, -3, -2, -1], 
			7, multiplier), divisor, bias);	
	}	
	return this;
};
})();





    
    
/*
 * colorMatrix support  for raphael. Only available on svg
 * @author: SebastiÃ¡n Gurin <sgurin @ montevideo  DOT com  DOT uy>
 */
(function () {
    if (Raphael.vml) {    	
    	//TODO
    } 
    else {
        var $ = function (el, attr) {
            if (attr) {
                for (var key in attr) if (attr.hasOwnProperty(key)) {
                    el.setAttribute(key, attr[key]);
                }
            } else {
                return document.createElementNS("http://www.w3.org/2000/svg", el);
            }
        };
        Raphael.el.colorMatrix = function (tname, matrix) {        	
            var filterConfig = {
            	type: "matrix", 
            	values : matrix.join(" ")
            };
        	//if not exists create a main filter element
        	if(this.colorMainFilter==null) {
        		this.colorMainFilter = $("filter");
        		this.colorMainFilter.id = "colorMainFilter"
                this.paper.defs.appendChild(this.colorMainFilter);
        		$(this.node, {filter: "url(#colorMainFilter)"});
        	}
        	
            //create or gets the filter primitive element feColorMatrix:
            var colorFilter = this._colorFilters==null?null:this._colorFilters[tname];
            if(colorFilter==null){
            	colorFilter = $("feColorMatrix");
            }
            this.colorMainFilter.appendChild(colorFilter);
            
            //apply configuration and register
            $(colorFilter, filterConfig);  
            if(! this._colorFilters)
            	 this._colorFilters={}
            this._colorFilters[tname] = colorFilter;
            
	        return this;
        };

        Raphael.st.colorMatrix =  function(tname, matrix) {
        	for ( var i = 0; i < this.items.length; i++) {
				this.items[i].colorMatrix(tname, matrix);
			}
        };
        Raphael.el.colorMatrixClear = function (tName) {
        	if (this._colorFilters!=null && this._colorFilters[tName]!=null &&
        			this.colorMainFilter!=null) {   
        		try {
        			this.colorMainFilter.removeChild(this._colorFilters[tName]);
        			this._colorFilters[tName]=null;
        		}catch(ex){alert("error removing filter for color matrix named : "+tName);}
        		
            }  
            return this;
        };
        Raphael.el.colorMatrixClearAll=function() {
        	if(this.colorMainFilter!=null) {
	        	this.paper.defs.removeChild(this.colorMainFilter);
	        	this.colorMainFilter=null;
	        	this._colorFilters=null;
	        	this.node.removeAttribute("filter");
        	}
        };
    }
})();     
    
/* raphael support for http://www.w3.org/TR/SVG/filters.html#feComponentTransfer (SVG ONLY!)
 * in this first version, only type="linear" supported
 * @author: SebastiÃ¡n Gurin <sgurin @ montevideo  DOT com  DOT uy>
 */
(function () {
    if (Raphael.vml) { 
		//TODO
    } 
    else {
        var $ = function (el, attr) {
            if (attr) {
                for (var key in attr) if (attr.hasOwnProperty(key)) {
                    el.setAttribute(key, attr[key]);
                }
            } else {
                return document.createElementNS("http://www.w3.org/2000/svg", el);
            }
        };
			/**use like this:
				el.componentTransferLinear("myTransf1", {funcR: {slope: 4, intercept: -1}, funcG: {slope: 4, intercept: -1}, funcB: {slope: 4, intercept: -1}})
			*/
        Raphael.el.componentTransferLinear = function (tName, funcs) {       	
//        	alert("componentTransferLinear");
	     	//if not exists create a main filter element
	     	if(this.componentTransfersMainFilter==null) {
	     		alert("***componentTransfersMainFilter created");
	     		this.componentTransfersMainFilter = $("filter");
	     		this.componentTransfersMainFilter.id = "componentTransfersMainFilter"
	             this.paper.defs.appendChild(this.componentTransfersMainFilter);
	     		$(this.node, {filter: "url(#componentTransfersMainFilter)"});
	     	}
        	
            //create or gets the filter primitive element feComponentTransfer with its feFuncX childs:
            var componentTransferFilter = this._componentTransfers==null?null:this._componentTransfers[tName], 
					funcR=null, funcG=null, funcB=null ;
            if(componentTransferFilter==null){
//            	debugger;
//            	alert("*componentTransfersMainFilter created");
            	componentTransferFilter = $("feComponentTransfer");
				funcR = $("feFuncR");
				funcG = $("feFuncG");
				funcB = $("feFuncB");
				componentTransferFilter.appendChild(funcR);
				componentTransferFilter.appendChild(funcG);
				componentTransferFilter.appendChild(funcB);
            }
            else {
            	funcR = componentTransferFilter.childNodes[0];
            	funcG = componentTransferFilter.childNodes[1];
            	funcB = componentTransferFilter.childNodes[2];
            }
            //debugger;
            $(funcR, funcs["funcR"]); funcR.setAttribute("type", "linear");
            $(funcG, funcs["funcG"]); funcG.setAttribute("type", "linear");
            $(funcB, funcs["funcB"]); funcB.setAttribute("type", "linear");            
            this.componentTransfersMainFilter.appendChild(componentTransferFilter);
            
            //register          
            if(! this._componentTransfers)
            	 this._componentTransfers={}
            this._componentTransfers[tName] = componentTransferFilter;
            
	        return this;
        };
        Raphael.st.componentTransferLinear =  function(tname, funcs) {
        	for ( var i = 0; i < this.items.length; i++) {
				this.items[i].componentTransferLinear(tname, funcs);
			}
        };
        
        Raphael.el.componentTransferClear = function (tName) {
        	if (this._componentTransfers!=null && this._componentTransfers[tName]!=null &&
        			this.componentTransfersMainFilter!=null) {   
        		try {
        			this.componentTransfersMainFilter.removeChild(this._componentTransfers[tName]);
        			this._componentTransfers[tName]=null;
        		}catch(ex){alert("error removing filter for conv named : "+tName);}
        		
            }  
            return this;
        };
        Raphael.el.componentTransferClearAll=function() {
        	if(this.componentTransfersMainFilter!=null) {
	        	this.paper.defs.removeChild(this.componentTransfersMainFilter);
	        	this.componentTransfersMainFilter=null;
	        	this._componentTransfers=null;
	        	this.node.removeAttribute("filter");
        	}
        };
    }
    
})();
/*
 *  'feMorphology' support  for raphael. Only available on svg
 *  use shape1.morphology(morphname, operator, radius)
 *  where operator cah be "erode" or "dilate" and radius an int. morphname is 
 *  the name of your transformation and can be used later for unregistering the 
 *  transf using shape1.morphologyClear(morphname).
 * @author: SebastiÃ¡n Gurin <sgurin @ montevideo  DOT com  DOT uy>
 */
(function () {
    if (Raphael.vml) {    	
    	//TODO
    } 
    else {
        var $ = function (el, attr) {
            if (attr) {
                for (var key in attr) if (attr.hasOwnProperty(key)) {
                    el.setAttribute(key, attr[key]);
                }
            } else {
                return document.createElementNS("http://www.w3.org/2000/svg", el);
            }
        };
        Raphael.el.morphology = function (tname, operator, radius) {        	
            var filterConfig = {
            	"operator": operator, 
            	"radius" : radius
            };
        	//if not exists create a main filter element
        	if(this.morphologyMainFilter==null) {
        		this.morphologyMainFilter = $("filter");
        		this.morphologyMainFilter.id = "morphologyMainFilter"
            this.paper.defs.appendChild(this.morphologyMainFilter);
        		$(this.node, {filter: "url(#morphologyMainFilter)"});
        	}
        	
            //create or gets the filter primitive element feColorMatrix:
            var morphologyFilter = this._morphologyFilters==null?null:this._morphologyFilters[tname];
            if(morphologyFilter==null){
            	morphologyFilter = $("feMorphology");
            }
            this.morphologyMainFilter.appendChild(morphologyFilter);
            
            //apply configuration and register
            $(morphologyFilter, filterConfig);  
            if(! this._morphologyFilters)
            	 this._morphologyFilters={}
            this._morphologyFilters[tname] = morphologyFilter;
            
	        return this;
        };
        
        Raphael.el.morphologyClear = function (tName) {
        	if (this._morphologyFilters!=null && this._morphologyFilters[tName]!=null &&
        			this.morphologyMainFilter!=null) {   
        		try {
        			this.morphologyMainFilter.removeChild(this._morphologyFilters[tName]);
        			this._morphologyFilters[tName]=null;
        		}catch(ex){alert("error removing filter for morphology named : "+tName);}
        		
            }  
            return this;
        };
        Raphael.el.morphologyClearAll=function() {
        	if(this.morphologyMainFilter!=null) {
	        	this.paper.defs.removeChild(this.morphologyMainFilter);
	        	this.morphologyMainFilter=null;
	        	this._morphologyFilters=null;
	        	this.node.removeAttribute("filter");
        	}
        };
        
        Raphael.st.morphology =  function(tname, operator, radius) {
        	for ( var i = 0; i < this.items.length; i++) {
				this.items[i].morphology(tname, operator, radius);
			}
        }
    }
})();     













// SVG export extension from https://github.com/ElbertF/Raphael.Export

/**
 * Raphael.Export https://github.com/ElbertF/Raphael.Export
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/mit-license.php
 *
 */

(function(R) {
	/**
	* Escapes string for XML interpolation
	* @param value string or number value to escape
	* @returns string escaped
	*/
	function escapeXML(s) {
		if ( typeof s === 'number' ) return s.toString();

		var replace = { '&': 'amp', '<': 'lt', '>': 'gt', '"': 'quot', '\'': 'apos' };

		for ( var entity in replace ) {
			s = s.replace(new RegExp(entity, 'g'), '&' + replace[entity] + ';');
		}

		return s;
	}

	/**
	* Generic map function
	* @param iterable the array or object to be mapped
	* @param callback the callback function(element, key)
	* @returns array
	*/
	function map(iterable, callback) {
		var mapped = new Array;

		for ( var i in iterable ) {
			if ( iterable.hasOwnProperty(i) ) {
				var value = callback.call(this, iterable[i], i);

				if ( value !== null ) mapped.push(value);
			}
		}

		return mapped;
	}

	/**
	* Generic reduce function
	* @param iterable array or object to be reduced
	* @param callback the callback function(initial, element, i)
	* @param initial the initial value
	* @return the reduced value
	*/
	function reduce(iterable, callback, initial) {
		for ( var i in iterable ) {
			if ( iterable.hasOwnProperty(i) ) {
				initial = callback.call(this, initial, iterable[i], i);
			}
		}

		return initial;
	}

	/**
	* Utility method for creating a tag
	* @param name the tag name, e.g., 'text'
	* @param attrs the attribute string, e.g., name1="val1" name2="val2"
	* or attribute map, e.g., { name1 : 'val1', name2 : 'val2' }
	* @param content the content string inside the tag
	* @returns string of the tag
	*/
	function tag(name, attrs, matrix, content) {
		if ( typeof content === 'undefined' || content === null ) {
			content = '';
		}

		if ( typeof attrs === 'object' ) {
			attrs = map(attrs, function(element, name) {
				if ( name === 'transform') return;

				return name + '="' + escapeXML(element) + '"';
			}).join(' ');
		}

		return '<' + name + ( matrix ? ' transform="matrix(' + matrix.toString().replace(/^matrix\(|\)$/g, '') + ')" ' : ' ' ) + attrs + '>' +  content + '</' + name + '>';
	}

	/**
	* @return object the style object
	*/
	function extractStyle(node) {
		return {
			font: {
				family: node.attrs.font.replace(/^.*?"(\w+)".*$/, '$1'),
				size:   typeof node.attrs['font-size'] === 'undefined' ? null : node.attrs['font-size']
				}
			};
	}

	/**
	* @param style object from style()
	* @return string
	*/
	function styleToString(style) {
		// TODO figure out what is 'normal'
		return 'font: normal normal normal 10px/normal ' + style.font.family + ( style.font.size === null ? '' : '; font-size: ' + style.font.size + 'px' );
	}

	/**
	* Computes tspan dy using font size. This formula was empircally determined
	* using a best-fit line. Works well in both VML and SVG browsers.
	* @param fontSize number
	* @return number
	*/
	function computeTSpanDy(fontSize, line, lines) {
		if ( fontSize === null ) fontSize = 10;

		//return fontSize * 4.5 / 13
		return fontSize * 4.5 / 13 * ( line - .2 - lines / 2 ) * 3.5;
	}

	var serializer = {
		'text': function(node) {
			style = extractStyle(node);

			var tags = new Array;

			map(node.attrs['text'].split('\n'), function(text, iterable, line) {
                                line = line || 0;
				tags.push(tag(
					'text',
					reduce(
						node.attrs,
						function(initial, value, name) {
							if ( name !== 'text' && name !== 'w' && name !== 'h' ) {
								if ( name === 'font-size') value = value + 'px';

								initial[name] = escapeXML(value.toString());
							}

							return initial;
						},
						{ style: 'text-anchor: middle; ' + styleToString(style) + ';' }
						),
					node.matrix,
					tag('tspan', { dy: computeTSpanDy(style.font.size, line + 1, node.attrs['text'].split('\n').length) }, null, text)
				));
			});

			return tags;
		},
		'path' : function(node) {
			var initial = ( node.matrix.a === 1 && node.matrix.d === 1 ) ? {} : { 'transform' : node.matrix.toString() };

			return tag(
				'path',
				reduce(
					node.attrs,
					function(initial, value, name) {
						if ( name === 'path' ) name = 'd';

						initial[name] = value.toString();

						return initial;
					},
					{}
				),
				node.matrix
				);
		}
		// Other serializers should go here
	};

	R.fn.toSVG = function() {
		var
			paper   = this,
			restore = { svg: R.svg, vml: R.vml },
			svg     = '<svg style="overflow: hidden; position: relative;" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="' + paper.width + '" version="1.1" height="' + paper.height + '">'
			;

		R.svg = true;
		R.vml = false;

		for ( var node = paper.bottom; node != null; node = node.next ) {
			if ( node.node.style.display === 'none' ) continue;

			var attrs = '';

			// Use serializer
			if ( typeof serializer[node.type] === 'function' ) {
				svg += serializer[node.type](node);

				continue;
			}

			switch ( node.type ) {
				case 'image':
					attrs += ' preserveAspectRatio="none"';
					break;
			}

			for ( i in node.attrs ) {
				var name = i;

				switch ( i ) {
					case 'src':
						name = 'xlink:href';

						break;
					case 'transform':
						name = '';

						break;
				}

				if ( name ) {
					attrs += ' ' + name + '="' + escapeXML(node.attrs[i].toString()) + '"';
				}
			}

			svg += '<' + node.type + ' transform="matrix(' + node.matrix.toString().replace(/^matrix\(|\)$/g, '') + ')"' + attrs + '></' + node.type + '>';
		}

		svg += '</svg>';

		R.svg = restore.svg;
		R.vml = restore.vml;

		return svg;
	};
})(window.Raphael);




    







/* import svg plugin from https://github.com/sanojian/raphael-svg-import */
/*
* Raphael SVG Import 0.0.1 - Extension to Raphael JS
*
* Copyright (c) 2009 Wout Fierens
* Licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) license.
* 
* 
* 2011-12-08 modifications by Jonas Olmstead
* - added support for radial and linear gradients
* - added support for paths
* - removed prototype.js dependencies (I can't read that stuff)
* - changed input parameter to svg xml file
* - added support for text elements
* - added support for nested groups
* - added support for transforms and scaling applied to groups
* - svg elements returned as a set
*
*/
Raphael.fn.importSVGStr = function(svgString) { //added by sgurin
	var xmlDoc = null;
	if (window.DOMParser) {
	  parser=new DOMParser();
	  xmlDoc=parser.parseFromString(svgString,"text/xml");
	}
	else {
	  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	  xmlDoc.async=false;
	  xmlDoc.loadXML(svgString);
	} 
	if(xmlDoc)
		return this.importSVG(xmlDoc);
	else return null;
};
Raphael.fn.importSVG = function (svgXML) {
  try {
    
    // create a set to return 
    var m_myNewSet = this.set();
    
    var strSupportedShapes = "|rect|circle|ellipse|path|image|text|polygon|";
    
    // collect all gradient colors
    var linGrads = svgXML.getElementsByTagName("linearGradient");
    var radGrads = svgXML.getElementsByTagName("radialGradient");
    
    
    this.doFill = function(strNode,attr,mNodeName,mNodeValue) {
  	  // check if linear gradient
  	  if (mNodeValue.indexOf("url") == 0) {
  		  var opacity;
  		  var gradID = mNodeValue.substring("url(#".length,mNodeValue.length - 1);
			  for (var l=0;l<radGrads.length;l++)
  		  	  if (radGrads.item(l).getAttribute("id") == gradID) {
  			    	// get stops
  			    	var stop1, stop2;
  			    	for (var st=0;st<radGrads.item(l).childNodes.length;st++)
  			    		if (radGrads.item(l).childNodes.item(st).nodeName == "stop") {
  			    			if (stop1)
  			    				stop2 = radGrads.item(l).childNodes.item(st);
  			    			else
  			    				stop1 = radGrads.item(l).childNodes.item(st);
  			    		}

					if (!stop1)
						return;		// could not parse stops
						
  			    	// TODO: implement radial offset
  			    	// radial gradients not supported for paths, so do linear
  			    	if (strNode == "path")
  			    		attr[mNodeName] = 90 + "-" + stop1.getAttribute("stop-color") 
							+ "-" + stop2.getAttribute("stop-color") 
							+ ":50-" + stop1.getAttribute("stop-color");
  			    	else
						attr[mNodeName] = "r(" + radGrads.item(l).getAttribute("fx") + "," 
							+ radGrads.item(l).getAttribute("fx") + ")" + stop1.getAttribute("stop-color") 
							+ "-" + stop2.getAttribute("stop-color");

  			    	if (stop1.getAttribute("stop-opacity"))
						opacity = stop1.getAttribute("stop-opacity")
  		  	  }
				  
  		  for (var l=0;l<linGrads.length;l++)
  		  	  if (linGrads.item(l).getAttribute("id") == gradID) {
						// get angle
						var b = parseFloat(linGrads.item(l).getAttribute("y2")) - parseFloat(linGrads.item(l).getAttribute("y1"));
						var c = parseFloat(linGrads.item(l).getAttribute("x2")) - parseFloat(linGrads.item(l).getAttribute("x1"));
  			    	var angle = Math.atan(b/c);
  			    	if (c < 0)
  			    		angle = angle - Math.PI;
  			    		
  			    	angle = parseInt(Raphael.deg(angle) + 360) % 360;
  		  		  
  			    	// get stops
  			    	var stop1, stop2;
  			    	for (var st=0;st<linGrads.item(l).childNodes.length;st++)
  			    		if (linGrads.item(l).childNodes.item(st).nodeName == "stop") {
  			    			if (stop1)
  			    				stop2 = linGrads.item(l).childNodes.item(st);
  			    			else
  			    				stop1 = linGrads.item(l).childNodes.item(st);
  			    		}
						
					if (!stop1)
						return;		// could not parse stops

  			    	// TODO: hardcoded offset value of 50
					attr[mNodeName] = angle + "-" + stop1.getAttribute("stop-color") 
						+ "-" + stop2.getAttribute("stop-color") 
						+ ":50-" + stop1.getAttribute("stop-color");
					if (stop1.getAttribute("stop-opacity"))
						opacity = stop1.getAttribute("stop-opacity")
   		  	  }
  		  if (opacity)
  			  attr["opacity"] = opacity;
  	  } else {
  		  attr[mNodeName] = mNodeValue;
  	  }
    	
    };
    
    this.parseElement = function(elShape, myNewSet) {
    	var node = elShape.nodeName;
		
    	if (node == "g") {
    		// this is a group, parse the children and add to set
			var groupSet = this.set();
			
    		for (var o=0;o<elShape.childNodes.length;o++)
    			this.parseElement(elShape.childNodes.item(o), groupSet);
				
			// now apply transforms and attributes to set
			for (var k=0;k<elShape.attributes.length;k++) {
				var m = elShape.attributes[k];
				if (m.nodeName == "transform" && groupSet.transform) {
					var actions = m.nodeValue.split(')');
					for (var a=0;a<actions.length;a++) {
						if (actions[a].indexOf("matrix") == 0) 
							groupSet.transform("m" + actions[a].substring(actions[a].indexOf("(")+1));
						else if (actions[a]) 
							eval("groupSet." + actions[a] + ")");
					}
				}
				else
					groupSet.attr(m.nodeName,m.nodeValue);
			}
			myNewSet.push(groupSet);
			return;
    	}

    	if (node && strSupportedShapes.indexOf("|" + node + "|") >= 0) {
        	    	
	        var attr = { "stroke-width": 0, "fill":"#fff" };
	        var shape;
	        var style;
	        // find the id
	        var nodeID = elShape.getAttribute("id");
	        
	        m_font = "";
            for (var k=0;k<elShape.attributes.length;k++) {
	        	var m = elShape.attributes[k];
	        	  	
	            switch(m.nodeName) {
	              case "stroke-dasharray":
	                attr[m.nodeName] = "- ";
	              break;
	              case "style":
	            	// TODO: handle gradient fills within a style
	                style = m.nodeValue.split(";");
	                for (var l=0;l<style.length;l++)
	                	if (style[l].split(":")[0] == "fill")
	                		this.doFill(node,attr,style[l].split(":")[0],style[l].split(":")[1]);
	                	else
	                		attr[style[l].split(":")[0]] = style[l].split(":")[1];
	              break;
	              case "fill":
	            	  this.doFill(node,attr,m.nodeName,m.nodeValue);
	              break;
	              case "font-size":
	            	  m_font = m.nodeValue + "px " + m_font;
	            	  attr[m.nodeName] = m.nodeValue;
	              break;
	              case "font-family":
	            	  m_font = m_font + "\"" + m.nodeValue + "\"";
	              break;
	              case "x":
	              case "y":
	              case "cx":
	              case "cy":
	              case "rx":
	              case "ry":
	            	  // use numbers for location coords
	            	  attr[m.nodeName] = parseFloat(m.nodeValue);
	              break;
	              case "text-anchor":
	            	  // skip these due to bug in text scaling
	              break;
	              default:
	                attr[m.nodeName] = m.nodeValue;
	              break;
	            }

	          }
	        
	        switch(node) {
	          case "rect":
	        	  if (attr["rx"])
	        		  shape = this.rect(attr["x"],attr["y"],elShape.getAttribute("width")
	        				  	,elShape.getAttribute("height"),attr["rx"]);
	        	  else
	        		  shape = this.rect();
	          break;
	          case "circle":
	        	// changed to ellipse, we are not doing circles today
	            shape = this.ellipse();
	            attr["rx"] = attr["r"];
	            attr["ry"] = attr["r"];
	          break;
	          case "ellipse":
	            shape = this.ellipse();
	          break;
	          case "path":
	            shape = this.path(attr["d"]);
	          break;
	          case "polygon":
	        	// convert polygon to a path
	            var point_string = attr["points"].trim();
	            var aryPoints = point_string.split(" ");
	            var strNewPoints = "M";
	        	for (var i in aryPoints) {
	        		if (i > 0)
	        			strNewPoints += "L";
	        		strNewPoints += aryPoints[i];
	        	}
	        	strNewPoints += "Z";
	        	shape = this.path(strNewPoints);
	          break;
	          case "image":
	            shape = this.image();
	          break;
	          case "text":
	        	  shape = this.text(attr["x"],attr["y"],elShape.text || elShape.textContent);
	        	  shape.attr("font",m_font);
	        	  shape.attr("stroke","none");
	        	  shape.origFontPt = parseInt(attr["font-size"]);
	          break;
	        }
	        
	        // put shape into set 
	        myNewSet.push(shape);
	        	        
			// apply attributes
	        shape.attr(attr);
			
			// apply transforms
            for (var k=0;k<elShape.attributes.length;k++) {
	        	var m = elShape.attributes[k];
	        	  	
	            if (m.nodeName == "transform" && shape.transform) {
					var actions = m.nodeValue.split(')');
					for (var a=0;a<actions.length;a++) {
						if (actions[a].indexOf("matrix") == 0) 
							shape.transform("m" + actions[a].substring(actions[a].indexOf("(")+1));
						else if (actions[a]) 
							eval("shape." + actions[a] + ")");
					  }
				}
			}
			
	    }
    };
    
    var elShape;
    var m_font;
    var elSVG = svgXML.getElementsByTagName("svg")[0];
    elSVG.normalize();
    for (var i=0;i<elSVG.childNodes.length;i++) {
    	elShape = elSVG.childNodes.item(i);
    	
		this.parseElement(elShape, m_myNewSet);
		
     }

  } catch (error) {
    alert("The SVG data you entered was invalid! (" + error + ")");
  }
  
  // return our new set
  return m_myNewSet;
  
};
