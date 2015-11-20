package com.bitwise.app.propertywindow.widgets.joinproperty;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class JoinDialog extends Dialog{

	protected JoinDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE |  SWT.WRAP | SWT.APPLICATION_MODAL);
		
	}
	
	@Override
	protected Control createDialogArea(final Composite parent) {
		parent.setSize(1100, 600);
		Composite container = (Composite) super.createDialogArea(parent);
		 
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 6;
		container.setLayout(gridLayout);
		container.getShell().setText("Join Editor");
		
		parent.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				 int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
			        MessageBox messageBox = new MessageBox((Shell) parent, style);
			        messageBox.setText("Information");
			        messageBox.setMessage("Close the shell?");
			        event.doit = messageBox.open() == SWT.YES;
				
			}
		});
		
		Composite com1 = new Composite(parent, SWT.BORDER);
		com1.setBounds(10, 50, 250, 500);
		
		Label label = new Label(com1, SWT.READ_ONLY);
		label.setBounds(10, 10, 60, 20);
		label.setText("Join Type");
		Combo c = new Combo(com1, SWT.READ_ONLY);
		c.setBounds(80, 10, 80, 8);
		c.setItems(new String[]{"Outer", "Inner", "Parameter"});
		c.select(1);
		Label lab = new Label(com1, SWT.READ_ONLY);
		lab.setBounds(180, 40, 60, 20);
		/*String addIconPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/add.png";
		lab.setImage(new Image(null, addIconPath));*/
		lab.setText("Add");
		Composite comGrid = new Composite(com1, SWT.BORDER);
		comGrid.setLayout(new RowLayout());
		Button button = new Button(comGrid, SWT.CHECK);
		button.setLayoutData(new RowData(50, 20));
		Button button1 = new Button(comGrid, SWT.CHECK);
		button1.setLayoutData(new RowData(50, 20));
		//button.setBounds(0, -6, 40, 40);
		Text text = new Text(comGrid, SWT.BORDER);
		text.setLayoutData(new RowData(80, 10));
		comGrid.setBounds(10, 60, 205, 100);
		
		Composite com2 = new Composite(parent, SWT.BORDER);
		com2.setBounds(280, 180, 550, 300);
		Table tab = new Table(com2, SWT.BORDER);
		tab.setBounds(14, 60, 500, 200);
		tab.setLinesVisible(true);
		
		Composite com3 = new Composite(parent, SWT.BORDER);
		com3.setBounds(860, 120, 180, 400);
		Label label1 = new Label(com3, SWT.BORDER);
		label1.setBounds(10, 10, 60, 20);
		label1.setText("copy Of");
		
		Button b1 = new Button(com3, SWT.CHECK);
		b1.setBounds(80, 10, 20, 20);
		
		Table tab2 = new Table(com3, SWT.BORDER);
		tab2.setBounds(14, 100, 140, 200);
		tab2.setLinesVisible(true);
		
		return parent;
	}
	
 public static void main(String[] args) {
	 Display dis = new Display();
	 Shell s = new Shell(dis);
	JoinDialog j=new JoinDialog(s);
	j.createDialogArea(s);
	s.open();
	while (!s.isDisposed()) {
	      if (!dis.readAndDispatch())
	    	  dis.sleep();
	    }
	dis.dispose();
	  }

}
