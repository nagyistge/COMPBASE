package de.unipotsdam.kompetenzmanager.shared.util;

public class QueryUtil {
	public boolean like(final String str, String expr)
	  {
	    final String[] parts = expr.split("%");
	    final boolean traillingOp = expr.endsWith("%");
	    expr = "";
	    for (int i = 0, l = parts.length; i < l; ++i)
	    {
	      final String[] p = parts[i].split("\\\\\\?");
	      if (p.length > 1)
	      {
	        for (int y = 0, l2 = p.length; y < l2; ++y)
	        {
	          expr += p[y];
	          if (i + 1 < l2) expr += ".";
	        }
	      }
	      else
	      {
	        expr += parts[i];
	      }
	      if (i + 1 < l) expr += "%";
	    }
	    if (traillingOp) expr += "%";
	    expr = expr.replace("?", ".");
	    expr = expr.replace("%", ".*");
	    return str.matches(expr);
	}
	
	public Boolean myLike(String compare, String with) {		
		return compare.toLowerCase().contains(with.toLowerCase()) || with.toLowerCase().contains(compare.toLowerCase())  ; // like "%gita%"
	}

}
