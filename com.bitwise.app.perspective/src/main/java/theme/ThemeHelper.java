package theme;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.css.swt.theme.IThemeManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.bitwise.app.perspective.Activator;

public class ThemeHelper {
	private static IThemeEngine engine = null;
	private static Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);

	public static IThemeEngine getEngine() {
		if (engine == null) {
			engine = getThemeEngine();
		}
		return engine;
	}

	private static IThemeEngine getThemeEngine() {
		
//		Enumeration e = (Enumeration) bundle.findEntries( "", "*.css", true );
//		URL url = null;
//		URL cssUrl = null;
//		if ( e != null )
//		{
//			while ( e.hasMoreElements() )
//			{
//				url = (URL) e.nextElement();
//				try
//				{
//					cssUrl = FileLocator.resolve( url );
//				}
//				catch ( IOException e1 )
//				{
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				System.out.println("CSS URL: " + url.toString() );
//				System.out.println( "CSS FILE: " + cssUrl != null ? cssUrl.toString() : "null" );
//			}
//		}
		
		BundleContext context = bundle.getBundleContext();

		ServiceReference ref = context.getServiceReference(IThemeManager.class
				.getName());
		IThemeManager manager = (IThemeManager) context.getService(ref);

		return manager.getEngineForDisplay(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow() == null ? Display.getCurrent()
				: PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getShell().getDisplay());
	}
	

}