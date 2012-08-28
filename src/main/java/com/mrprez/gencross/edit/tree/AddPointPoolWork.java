package com.mrprez.gencross.edit.tree;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.PoolPoint;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.pointPool.PointPoolEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class AddPointPoolWork implements BackgroundWork {
	

	@Override
	public void doInBackground() throws Exception {
		PoolPoint poolPoint = new PoolPoint("", 0);
		PointPoolEditor pointPoolEditor = new PointPoolEditor(GenCrossEditor.getInstance(), poolPoint);
		poolPoint = pointPoolEditor.edit();
		if(poolPoint!=null){
			Personnage personnage = GenCrossEditor.getInstance().getPersonnage();
			personnage.getPointPools().put(poolPoint.getName(), poolPoint);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new ReinitPointPoolPanelsWork();
	}

}
