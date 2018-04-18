package invenio.wf.item.visual;

import invenio.UserSession;
import invenio.algorithms.util.AlgorithmManager;
import invenio.algorithms.util.AlgorithmParameterPanel;
import invenio.ui.controller.MainFrameController;
import invenio.wf.VisualItem;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;

public class AlgorithmParameterPanelItem extends VisualItem {
	private final Class algorithm;
	private AlgorithmParameterPanel paramPanel = null;
	
	public AlgorithmParameterPanelItem(Class algorithm) {
		super();
		this.algorithm = algorithm;
	}

	@Override
	public boolean isInternallyUpdateable() {
		return false;
	}

	@Override
	public boolean isExternallyUpdateable() {
		return true;
	}

	@Override
	public boolean update() {
		AlgorithmManager algorithmManager = UserSession.getInstance().getActiveVisualGraphSession().getAlgorithmManager();
		paramPanel = null;
		
		paramPanel = algorithmManager.runAlgorithm(algorithm);
		
		if(paramPanel != null){
			JDialog paramDialog = new JDialog(MainFrameController.getInstance().getFrame());
			paramDialog.setContentPane(paramPanel);
			//paramDialog.setModalityType(ModalityType.MODELESS);
			paramDialog.setModalityType(ModalityType.APPLICATION_MODAL);
			paramPanel.setOwnerDialog(paramDialog);
			paramPanel.setVisible(true);
			paramDialog.pack();
			paramDialog.setVisible(true);
			suspend();
		}
		
		return false;
	}

	@Override
	public Object getOutput() {
		if (paramPanel != null) {
			return paramPanel.getParameters();
		}
		else
			return null;
	}
}
