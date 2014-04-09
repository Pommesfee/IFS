package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class IFS_ComponentListener implements ComponentListener {

	IFS_Frame ifsFrame = null;
	
	public IFS_ComponentListener(IFS_Frame ifsFrame) {
		this.ifsFrame = ifsFrame;
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
	}
	

	@Override
	public void componentMoved(ComponentEvent e) {
	}
	

	@Override
	public void componentResized(ComponentEvent e) {
			ifsFrame.getIfsManager().getSelectedIFS().getPoint().reCalculateOffsets(ifsFrame.getCanvas().getWidth(),
					ifsFrame.getCanvas().getHeight());
	}
	

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
	
}
