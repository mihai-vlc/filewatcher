package com.mihaivilcu.filewatcher.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.mihaivilcu.filewatcher.Activator;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MainPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private Text txtURL;
	private Button checkPreClose;
	private Button checkPreDelete;
	private Button checkPreBuild;
	private Button checkPostBuild;
	private Button checkPostChange;

	public static final String PF_URL_KEY = "filewatcher_url";
	public static final String PF_CHECK_PRE_CLOSE = "filewatcher_check_pre_close";
	public static final String PF_CHECK_PRE_DELETE = "filewatcher_check_pre_delete";
	public static final String PF_CHECK_PRE_BUILD = "filewatcher_check_pre_build";
	public static final String PF_CHECK_POST_BUILD = "filewatcher_check_post_close";
	public static final String PF_CHECK_POST_CHANGE = "filewatcher_check_post_change";

	/**
	 * Create the preference page.
	 */
	public MainPreferencePage() {
		setTitle("FileWathcer Preferences");
	}

	@Override
	public Control createContents(Composite parent) {
		// parent composite for this component
		Composite newParent = new Composite(parent, SWT.NONE);

		GridData d1 = new GridData();
		d1.grabExcessHorizontalSpace = true;
		d1.horizontalAlignment = GridData.FILL;
		newParent.setLayoutData(d1);

		GridLayout l1 = new GridLayout();
		l1.numColumns = 1;
		newParent.setLayout(l1);

		// label
		Label lblBCName = new Label(newParent, SWT.NONE);
		lblBCName.setText("Callback URL");
		// text field
		txtURL = new Text(newParent, SWT.BORDER);
		txtURL.setLayoutData(d1);

		new Label(newParent, SWT.NONE).setText("Trigger on:");

		checkPreClose = new Button(newParent, SWT.CHECK);
		checkPreClose.setText("Pre Close");

		checkPreDelete = new Button(newParent, SWT.CHECK);
		checkPreDelete.setText("Pre Delete");

		checkPreBuild = new Button(newParent, SWT.CHECK);
		checkPreBuild.setText("Pre build");

		checkPostBuild = new Button(newParent, SWT.CHECK);
		checkPostBuild.setText("Post build");

		checkPostChange = new Button(newParent, SWT.CHECK);
		checkPostChange.setText("Post Change");

		intializeValues();

		return newParent;
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

	private void intializeValues() {
		IPreferenceStore store = doGetPreferenceStore();
		String url = store.getString(PF_URL_KEY);

		checkPreClose.setSelection(store.getBoolean(PF_CHECK_PRE_CLOSE));
		checkPreDelete.setSelection(store.getBoolean(PF_CHECK_PRE_DELETE));
		checkPreBuild.setSelection(store.getBoolean(PF_CHECK_PRE_BUILD));
		checkPostBuild.setSelection(store.getBoolean(PF_CHECK_POST_BUILD));
		checkPostChange.setSelection(store.getBoolean(PF_CHECK_POST_CHANGE));

		txtURL.setText(url == null ? "" : url);
	}

	private void storeValues() {
		IPreferenceStore store = doGetPreferenceStore();
		String url = txtURL.getText();

		store.setValue(PF_CHECK_PRE_CLOSE, checkPreClose.getSelection());
		store.setValue(PF_CHECK_PRE_DELETE, checkPreDelete.getSelection());
		store.setValue(PF_CHECK_PRE_BUILD, checkPreBuild.getSelection());
		store.setValue(PF_CHECK_POST_BUILD, checkPostBuild.getSelection());
		store.setValue(PF_CHECK_POST_CHANGE, checkPostChange.getSelection());

		store.putValue(PF_URL_KEY, url == null ? "" : url);
		store.setDefault(PF_URL_KEY, url == null ? "" : url);
	}

	private void initializeDefaults() {
		txtURL.setText("http://");
		checkPreClose.setSelection(false);
		checkPreDelete.setSelection(false);
		checkPreBuild.setSelection(false);
		checkPostBuild.setSelection(false);
		checkPostChange.setSelection(false);
	}

	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

	@Override
	public boolean performCancel() {
		intializeValues();
		return super.performCancel();
	}

	@Override
	protected void performDefaults() {
		initializeDefaults();
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		storeValues();
		return super.performOk();
	}
}
