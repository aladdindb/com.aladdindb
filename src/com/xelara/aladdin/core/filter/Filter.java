package com.xelara.aladdin.core.filter;

public interface Filter<T> {
	public boolean prove( T field );
}
