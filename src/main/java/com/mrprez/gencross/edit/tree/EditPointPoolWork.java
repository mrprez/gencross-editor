package com.mrprez.gencross.edit.tree;

import java.util.ArrayList;
import java.util.List;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.PoolPoint;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.pointPool.PointPoolEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class EditPointPoolWork implements BackgroundWork {
	private PoolPoint poolPoint;
	
	
	public EditPointPoolWork(PoolPoint poolPoint) {
		super();
		this.poolPoint = poolPoint;
	}

	@Override
	public void doInBackground() throws Exception {
		List<PoolPoint> pointPools = new ArrayList<PoolPoint>();
		Personnage personnage = GenCrossEditor.getInstance().getPersonnage();
		pointPools.addAll(personnage.getPointPools().values());
		PointPoolEditor pointPoolEditor = new PointPoolEditor(GenCrossEditor.getInstance(), poolPoint);
		
		pointPoolEditor.edit();
		personnage.getPointPools().clear();
		for(PoolPoint poolPoint : pointPools){
			personnage.getPointPools().put(poolPoint.getName(), poolPoint);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new ReinitPointPoolPanelsWork();
	}

}
