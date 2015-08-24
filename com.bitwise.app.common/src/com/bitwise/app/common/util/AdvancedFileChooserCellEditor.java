package com.bitwise.app.common.util;

import java.text.MessageFormat;

import junit.framework.Assert;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;

public class AdvancedFileChooserCellEditor extends CellEditor {

	/**
	 * The editor control.
	 */
	private Composite editor;

	/**
	 * The button.
	 */
	private Button button;

	private Composite parent;

	/**
	 * The text control; initially <code>null</code>.
	 */
	protected Text text;

	private ModifyListener modifyListener;

	/**
	 * State information for updating action enablement
	 */
	private boolean isselection = false;
	private boolean isDeleteable = false;
	private boolean isSelectable = false;

	/**
	 * Default TextCellEditor style specify no borders on text widget as cell
	 * outline in table already provides the look of a border.
	 */
	private static final int defaultStyle = SWT.SINGLE;

	/**
	 * Creates a new text string cell editor with no control The cell editor
	 * value is the string itself, which is initially the empty string.
	 * Initially, the cell editor has no cell validator.
	 * 
	 * @since 2.1
	 */
	public AdvancedFileChooserCellEditor() {
		setStyle(defaultStyle);
	}

	/**
	 * Creates a new text string cell editor parented under the given control.
	 * The cell editor value is the string itself, which is initially the empty
	 * string. Initially, the cell editor has no cell validator.
	 * 
	 * @param parent
	 *            the parent control
	 */
	public AdvancedFileChooserCellEditor(Composite parent) {
		this(parent, defaultStyle);
		this.parent = parent;
	}

	/**
	 * Creates a new text string cell editor parented under the given control.
	 * The cell editor value is the string itself, which is initially the empty
	 * string. Initially, the cell editor has no cell validator.
	 * 
	 * @param parent
	 *            the parent control
	 * @param style
	 *            the style bits
	 * @since 2.1
	 */
	public AdvancedFileChooserCellEditor(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
	}

	/**
	 * Checks to see if the "deleteable" state (can delete/ nothing to delete)
	 * has changed and if so fire an enablement changed notification.
	 */
	private void checkDeleteable() {
		boolean oldIsDeleteable = isDeleteable;
		isDeleteable = isDeleteEnabled();
		if (oldIsDeleteable != isDeleteable) {
			fireEnablementChanged(DELETE);
		}
	}

	/**
	 * Checks to see if the "selectable" state (can select) has changed and if
	 * so fire an enablement changed notification.
	 */
	private void checkSelectable() {
		boolean oldIsSelectable = isSelectable;
		isSelectable = isSelectAllEnabled();
		if (oldIsSelectable != isSelectable) {
			fireEnablementChanged(SELECT_ALL);
		}
	}

	/**
	 * Checks to see if the selection state (selection / no selection) has
	 * changed and if so fire an enablement changed notification.
	 */
	private void checkselection() {
		boolean oldIsselection = isselection;
		isselection = text.getSelectionCount() > 0;
		if (oldIsselection != isselection) {
			fireEnablementChanged(COPY);
			fireEnablementChanged(CUT);
		}
	}

	/**
	 * Internal class for laying out the dialog.
	 */
	private class DialogCellLayout extends Layout {
		public void layout(Composite editor, boolean force) {
			Rectangle bounds = editor.getClientArea();
			Point size = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			if (text != null)
				text.setBounds(0, 0, bounds.width - size.x, bounds.height);
			button.setBounds(bounds.width - size.x, 0, size.x, bounds.height);
		}

		public Point computeSize(Composite editor, int wHint, int hHint,
				boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
				return new Point(wHint, hHint);
			Point contentsSize = text.computeSize(SWT.DEFAULT, SWT.DEFAULT,
					force);
			Point buttonSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT,
					force);
			// Just return the button width to ensure the button is not clipped
			// if the label is long.
			// The label will just use whatever extra width there is
			Point result = new Point(buttonSize.x, Math.max(contentsSize.y,
					buttonSize.y));
			return result;
		}
	}

	/**
	 * Creates the button for this cell editor under the given parent control.
	 * <p>
	 * The default implementation of this framework method creates the button
	 * display on the right hand side of the dialog cell editor. Subclasses may
	 * extend or reimplement.
	 * </p>
	 * 
	 * @param parent
	 *            the parent control
	 * @return the new button control
	 */
	protected Button createButton(Composite parent) {
		Button result = new Button(parent, SWT.DOWN);
		result.setText("..."); //$NON-NLS-1$
		return result;
	}

	
	 
	protected Control createControl(Composite parent) {
		Font font = parent.getFont();
		Color bg = parent.getBackground();

		editor = new Composite(parent, getStyle());
		editor.setFont(font);
		editor.setBackground(bg);
		editor.setLayout(new DialogCellLayout());

		text = new Text(editor, getStyle());
		text.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				handleDefaultselection(e);
			}
		});
		text.addKeyListener(new KeyAdapter() {
			// hook key pressed - see PR 14201
			public void keyPressed(KeyEvent e) {
				keyReleaseOccured(e);

				// as a result of processing the above call, clients may have
				// disposed this cell editor
				if ((getControl() == null) || getControl().isDisposed())
					return;
				checkselection(); // see explaination below
				checkDeleteable();
				checkSelectable();
			}
		});
		text.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE
						|| e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
				}
			}
		});
		// We really want a selection listener but it is not supported so we
		// use a key listener and a mouse listener to know when selection
		// changes
		// may have occured
		text.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				checkselection();
				checkDeleteable();
				checkSelectable();
			}
		});

		text.setFont(parent.getFont());
		text.setBackground(parent.getBackground());
		text.setText("");//$NON-NLS-1$
		text.addModifyListener(getModifyListener());

		button = createButton(editor);
		button.setFont(font);

		button.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.character == '\u001b') { // Escape
					fireCancelEditor();
				}
			}
		});

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Object newValue = openDialogBox(editor);
				if (newValue != null) {
					boolean newValidState = isCorrect(newValue);
					if (newValidState) {
						markDirty();
						doSetValue(newValue);
					} else {
						// try to insert the current value into the error
						// message.
						setErrorMessage(MessageFormat.format(getErrorMessage(),
								new Object[] { newValue.toString() }));
					}
					// fireApplyEditorValue();
					focusLost();
				}
			}
		});

		setValueValid(true);

		return editor;
	}

	/**
	 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(Con trol)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		// Constucts a custom fileChooser
		FileDialog fDialog = new FileDialog(parent.getShell(), SWT.OPEN);
		

		String value = (String) getValue();

		if ((value != null) && (value.length() > 0)) {
			fDialog.setFileName(value);
		}
		String path = fDialog.open();

		// Returns a path, a string, what you want
		return path;
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> framework method returns the text string.
	 * 
	 * @return the text string
	 */
	protected Object doGetValue() {
		return text.getText();
	}

	
	protected void doSetFocus() {
		if (text != null) {
			text.selectAll();
			text.setFocus();
			checkselection();
			checkDeleteable();
			checkSelectable();
		}
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> framework method accepts a text string (type
	 * <code>String</code>).
	 * 
	 * @param value
	 *            a text string (type <code>String</code>)
	 */
	protected void doSetValue(Object value) {
		Assert.assertTrue(text != null && (value instanceof String));
		text.removeModifyListener(getModifyListener());
		text.setText((String) value);
		text.addModifyListener(getModifyListener());
	}

	/**
	 * Processes a modify event that occurred in this text cell editor. This
	 * framework method performs validation and sets the error message
	 * accordingly, and then reports a change via
	 * <code>fireEditorValueChanged</code>. Subclasses should call this method
	 * at appropriate times. Subclasses may extend or reimplement.
	 * 
	 * @param e
	 *            the SWT modify event
	 */
	protected void editOccured(ModifyEvent e) {
		String value = text.getText();
		if (value == null)
			value = "";//$NON-NLS-1$
		Object typedValue = value;
		boolean oldValidState = isValueValid();
		boolean newValidState = isCorrect(typedValue);
		if (typedValue == null && newValidState)
			Assert.assertTrue("Validator isn't limiting the cell editor's type range", false);//$NON-NLS-1$
		if (!newValidState) {
			// try to insert the current value into the error message.
			setErrorMessage(MessageFormat.format(getErrorMessage(),
					new Object[] { value }));
		}
		valueChanged(oldValidState, newValidState);
	}

	/**
	 * Since a text editor field is scrollable we don't set a minimumSize.
	 */
	public LayoutData getLayoutData() {
		return new LayoutData();
	}

	/**
	 * Return the modify listener.
	 */
	private ModifyListener getModifyListener() {
		if (modifyListener == null) {
			modifyListener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					editOccured(e);
				}
			};
		}
		return modifyListener;
	}

	/**
	 * Handles a default selection event from the text control by applying the
	 * editor value and deactivating this cell editor.
	 * 
	 * @param event
	 *            the selection event
	 * 
	 * @since 3.0
	 */
	protected void handleDefaultselection(SelectionEvent event) {
		// same with enter-key handling code in keyReleaseOccured(e);
		fireApplyEditorValue();
		deactivate();
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method returns <code>true</code> if the current
	 * selection is not empty.
	 */
	public boolean isCopyEnabled() {
		if (text == null || text.isDisposed())
			return false;
		return text.getSelectionCount() > 0;
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method returns <code>true</code> if the current
	 * selection is not empty.
	 */
	public boolean isCutEnabled() {
		if (text == null || text.isDisposed())
			return false;
		return text.getSelectionCount() > 0;
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method returns <code>true</code> if there is a
	 * selection or if the caret is not positioned at the end of the text.
	 */
	public boolean isDeleteEnabled() {
		if (text == null || text.isDisposed())
			return false;
		return text.getSelectionCount() > 0
				|| text.getCaretPosition() < text.getCharCount();
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method always returns <code>true</code>.
	 */
	public boolean isPasteEnabled() {
		if (text == null || text.isDisposed())
			return false;
		return true;
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method always returns <code>true</code>.
	 */
	public boolean isSaveAllEnabled() {
		if (text == null || text.isDisposed())
			return false;
		return true;
	}

	/**
	 * Returns <code>true</code> if this cell editor is able to perform the
	 * select all action.
	 * <p>
	 * This default implementation always returns <code>false</code>.
	 * </p>
	 * <p>
	 * Subclasses may override
	 * </p>
	 * 
	 * @return <code>true</code> if select all is possible, <code>false</code>
	 *         otherwise
	 */
	public boolean isSelectAllEnabled() {
		if (text == null || text.isDisposed())
			return false;
		return text.getCharCount() > 0;
	}

	/**
	 * Processes a key release event that occurred in this cell editor.
	 * <p>
	 * The <code>TextCellEditor</code> implementation of this framework method
	 * ignores when the RETURN key is pressed since this is handled in
	 * <code>handleDefaultselection</code>. An exception is made for Ctrl+Enter
	 * for multi-line texts, since a default selection event is not sent in this
	 * case.
	 * </p>
	 * 
	 * @param keyEvent
	 *            the key event
	 */
	protected void keyReleaseOccured(KeyEvent keyEvent) {
		if (keyEvent.character == '\r') { // Return key
		// Enter is handled in handleDefaultselection.
		// Do not apply the editor value in response to an Enter key event
		// since this can be received from the IME when the intent is -not-
		// to apply the value.
		// See bug 39074 [CellEditors] [DBCS] canna input mode fires bogus event
		// from Text Control
		//
		// An exception is made for Ctrl+Enter for multi-line texts, since
		// a default selection event is not sent in this case.
			if (text != null && !text.isDisposed()
					&& (text.getStyle() & SWT.MULTI) != 0) {
				if ((keyEvent.stateMask & SWT.CTRL) != 0) {
					super.keyReleaseOccured(keyEvent);
				}
			}
			return;
		}
		super.keyReleaseOccured(keyEvent);
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method copies the current selection to the
	 * clipboard.
	 */
	public void performCopy() {
		text.copy();
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method cuts the current selection to the
	 * clipboard.
	 */
	public void performCut() {
		text.cut();
		checkselection();
		checkDeleteable();
		checkSelectable();
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method deletes the current selection or, if there
	 * is no selection, the character next character from the current position.
	 */
	public void performDelete() {
		if (text.getSelectionCount() > 0)
			// remove the contents of the current selection
			text.insert(""); //$NON-NLS-1$
		else {
			// remove the next character
			int pos = text.getCaretPosition();
			if (pos < text.getCharCount()) {
				text.setSelection(pos, pos + 1);
				text.insert(""); //$NON-NLS-1$
			}
		}
		checkselection();
		checkDeleteable();
		checkSelectable();
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method pastes the the clipboard contents over the
	 * current selection.
	 */
	public void performPaste() {
		text.paste();
		checkselection();
		checkDeleteable();
		checkSelectable();
	}

	/**
	 * The <code>TextCellEditor</code> implementation of this
	 * <code>CellEditor</code> method selects all of the current text.
	 */
	public void performSelectAll() {
		text.selectAll();
		checkselection();
		checkDeleteable();
	}
}