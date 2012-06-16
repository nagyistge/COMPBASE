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
Raphael.fn.sunburst = function(cx, cy, values, opts) {
	opts = opts || {};
	var paper = this,
	    chart = this.set(),
	    series = this.set(),
	    levelRadii = [];

	function levelRadius(level) {
		if (levelRadii[level])
			return levelRadii[level];

		var levelWidth = opts.onLevelWidth || function(level) {
				return this.levelWidths ? this.levelWidths[level] :
					(this.levelWidth || 50);
			},
			res = 0;

		for (var i = 0; i <= level; i++)
			res += levelWidth.call(opts, i);

		levelRadii[level] = res;
		return res;
	}

	function sector(cx, cy, ri, ro, startAngle, endAngle, params) {
		var sliceAngle = endAngle - startAngle,
		    full = Math.abs(sliceAngle) >= 360;
		if (full)
			endAngle = startAngle + 359.99;

		var large = Math.abs(sliceAngle) > 180,
		    rad = Math.PI / 180,
		    xo1 = cx + ro * Math.cos(-startAngle * rad),
		    yo1 = cy + ro * Math.sin(-startAngle * rad),
		    xo2 = cx + ro * Math.cos(-endAngle * rad),
		    yo2 = cy + ro * Math.sin(-endAngle * rad),
		    xi1 = cx + ri * Math.cos(-startAngle * rad),
		    yi1 = cy + ri * Math.sin(-startAngle * rad),
		    xi2 = cx + ri * Math.cos(-endAngle * rad),
		    yi2 = cy + ri * Math.sin(-endAngle * rad),
		    halfAngle = Math.abs(endAngle + startAngle) / 2,
		    rm = (ro + ri) / 2,
		    xm = cx + rm * Math.cos(-halfAngle * rad),
		    ym = cy + rm * Math.sin(-halfAngle * rad),
		    res = paper.path([
				"M", xi1, yi1,
				"A", ri, ri, 0, +large, 0, xi2, yi2,
				full ? "M" : "L", xo2, yo2,
				"A", ro, ro, 0, +large, 1, xo1, yo1,
				"Z"
			]);
		res.middle = {x: xm, y: ym};
		res.mangle = halfAngle;
		res.ri = ri;
		res.ro = ro;
		return res.attr(params);
	}

	function getDataSeriesFromObj(rootLabel, values) {
		var res = {label: rootLabel, value: 0, children: []},
		    maxDepth = 0,
		    child;
		for (var i in values) {
			if (~~values[i]) {
				res.value += values[i];
				child = {label: i, value: values[i], depth: 0, children: []};
			} else {
				child = getDataSeriesFromObj(i, values[i]);
				res.value += child.value;
			}
			maxDepth = Math.max(maxDepth, child.depth);
			res.children[res.children.length] = child;
		}
		res.depth = maxDepth + 1;
		return res;
	}

	function colorAttr(idx, depth) {
		var idx = idx % (opts.colors.length || opts.gradients.length);

		if (opts.onGradient)
			return {gradient: opts.onGradient.call(opts, idx, depth)};

		return opts.gradients ? {gradient: opts.gradients[idx]} : {
			fill: opts.colors ? opts.colors[idx] : Raphael.getColor()};
	}

	function renderSeries(data, renderTo, level, total, prevAngle, parentIdx) {
		level = level || 0;
		total = total || data.value;
		prevAngle = prevAngle || (opts.offsetAngle || 0);
		parentIdx = parentIdx || 0;
		var startAngle,
		    endAngle = prevAngle,
		    children = data.children,
		    childIdx = 0;
		for (var i = 0; i < children.length; i++) {
			startAngle = endAngle;
			endAngle += children[i].value / total * 360;

			var thisIdx = level == 0 ? childIdx : parentIdx,
			    sect = sector(cx, cy, levelRadius(level), levelRadius(level + 1),
			    startAngle, endAngle,
			    colorAttr(!opts.colorizeByLevel ? thisIdx : level, level)).attr({
						stroke: opts.stroke || "#fff",
						"stroke-width": opts.strokewidth || 1});
			sect.level = level;
			sect.value = children[i].value;
			sect.label = children[i].label;
			renderTo.push(sect);
			renderSeries(children[i], renderTo, level + 1, total, startAngle,
				thisIdx);
			childIdx++;
		}
	}

	Raphael.getColor.reset();
	var data = getDataSeriesFromObj(opts.rootLabel, values);
	renderSeries(data, series);

	function getCallbackContext(sector) {
		return {
			sector: sector,
			cx: cx,
			cy: cy,
			mx: sector.middle.x,
			my: sector.middle.y,
			mangle: sector.mangle
		};
	}

	chart.hover = function(fin, fout) {
		fout = fout || function () {};
		for (var i = 0; i < series.length; i++)
			(function (sector) {
				var o = getCallbackContext(sector);
				sector.mouseover(function () { fin.call(o); });
				sector.mouseout(function () { fout.call(o); });
			})(series[i]);
		return this;
	};

	chart.click = function(f) {
		for (var i = 0; i < series.length; i++)
			(function (sector) {
				var o = getCallbackContext(sector);
				sector.click(function () { f.call(o); });
			})(series[i]);
		return this;
	};

	// TODO: Need access functions for individual rings, sectors and items
	chart.push(series);
	chart.series = series;
	return chart;
};
