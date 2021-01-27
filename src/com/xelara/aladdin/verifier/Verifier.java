package com.xelara.aladdin.verifier;

public interface Verifier<T> {
	public boolean prove( T field );
}
