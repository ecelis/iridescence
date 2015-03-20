package org.compiere.print;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

import org.compiere.apps.AMenu;
import org.compiere.util.Env;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class SwingViewerProvider implements ReportViewerProvider {

	public void openViewer(ReportEngine re) {
		Viewer viewer = new Viewer(re);
		JFrame top = Env.getWindow(0);
		if (top instanceof AMenu)
			((AMenu)top).getWindowManager().add(viewer);
	}

	@Override
	public void openViewer(JasperPrint jasperPrint, String title, boolean export)
			throws JRException {
		JRViewer _view = new JRViewer(jasperPrint);

		JFrame frame = new JFrame(title);
		frame.getContentPane().add(_view);
		Dimension screenSize = 
            Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(
                screenSize.width / 2,
                screenSize.height / 2
        );

        frame.setVisible(true);
		
	}

}
