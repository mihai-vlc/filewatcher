package com.mihaivilcu.filewatcher;

import org.eclipse.core.resources.*;
import org.eclipse.ui.IStartup;

public class OnStartup implements IStartup {
	public void earlyStartup() {

		if (ResourceChangeReporter.INS != null)
			return;
		ResourceChangeReporter.INS = new ResourceChangeReporter();

		ResourcesPlugin.getWorkspace().addResourceChangeListener(ResourceChangeReporter.INS,
				IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.PRE_BUILD
						| IResourceChangeEvent.POST_BUILD | IResourceChangeEvent.POST_CHANGE);
	}
}
