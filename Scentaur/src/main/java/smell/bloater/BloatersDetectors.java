package smell.bloater;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BloatersDetectors extends VoidVisitorAdapter<Void> implements Bloatable {
	
	public int threshold;
	@Override
	public boolean isClassEmpty(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThreshold(int value) {
		this.threshold = value;
	}
}
