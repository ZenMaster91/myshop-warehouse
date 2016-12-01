package com.myshop2.maths;

import com.myshop.model.product.Dimension3D;

public class DimensionsInterpreter {
	
	private Dimension3D dimension = null;
	
	public DimensionsInterpreter(Dimension3D dimension) {
		this.dimension = dimension;
	}
	
	public String toString() {
		return (dimension.getHeight() + "x" + dimension.getWith() + "x" + dimension.getDeep());
	}

}
