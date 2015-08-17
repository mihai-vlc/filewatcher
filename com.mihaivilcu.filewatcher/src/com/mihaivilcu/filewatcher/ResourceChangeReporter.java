package com.mihaivilcu.filewatcher;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.preference.IPreferenceStore;
import com.mihaivilcu.filewatcher.preferences.*;

public class ResourceChangeReporter implements IResourceChangeListener {
	public static ResourceChangeReporter INS = null;

	public void resourceChanged(IResourceChangeEvent event) {
		IPreferenceStore pref = Activator.getDefault().getPreferenceStore();

		String url = pref.getString(MainPreferencePage.PF_URL_KEY);

		IResourceDelta delta = event.getDelta();
		String path = getFullPath(delta);

		switch (event.getType()) {

		case IResourceChangeEvent.PRE_CLOSE:
			if (pref.getBoolean(MainPreferencePage.PF_CHECK_PRE_CLOSE)) {
				RequestHelper.getHTML(url + "?action=preclose&path=" + EncodingUtil.encodeURIComponent(path));
			}
			break;
		case IResourceChangeEvent.PRE_DELETE:
			if (pref.getBoolean(MainPreferencePage.PF_CHECK_PRE_DELETE)) {
				RequestHelper.getHTML(url + "?action=predelete&path=" + EncodingUtil.encodeURIComponent(path));
			}
			break;
		case IResourceChangeEvent.POST_CHANGE:
			if (pref.getBoolean(MainPreferencePage.PF_CHECK_POST_CHANGE)) {
				RequestHelper.getHTML(url + "?action=postchange&path=" + EncodingUtil.encodeURIComponent(path));
			}
			break;
		case IResourceChangeEvent.PRE_BUILD:
			if (pref.getBoolean(MainPreferencePage.PF_CHECK_PRE_BUILD)) {
				RequestHelper.getHTML(url + "?action=prebuild&path=" + EncodingUtil.encodeURIComponent(path));
			}
			break;
		case IResourceChangeEvent.POST_BUILD:
			if (pref.getBoolean(MainPreferencePage.PF_CHECK_POST_BUILD)) {
				RequestHelper.getHTML(url + "?action=postbuild&path=" + EncodingUtil.encodeURIComponent(path));
			}
			break;
		}
	}

	public static String getFullPath(IResourceDelta delta) {
		IPath path = delta.getFullPath();
		IResourceDelta[] tmp = delta.getAffectedChildren();

		// we just take the first child available in order to build a full path
		// maybe in the future we will build all the available deltas
		while (tmp.length > 0) {
			path = tmp[0].getFullPath();
			tmp = tmp[0].getAffectedChildren();
		}

		return path.toString();
	}
}
