package overbodig;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicDouble extends Number implements Comparable<AtomicDouble> {
	private static final long serialVersionUID = 1L;
	private AtomicReference<Double> atomicReference;
	public AtomicDouble(Double doubleValue) {
		atomicReference = new AtomicReference<Double>(doubleValue);
	}

	@Override
	public int compareTo(AtomicDouble o) {
		return Double.compare(this.doubleValue(), o.doubleValue());
	}

	@Override
	public double doubleValue() {
		return atomicReference.get().doubleValue();
	}

	@Override
	public float floatValue() {
		return atomicReference.get().floatValue();
	}

	@Override
	public int intValue() {
		return atomicReference.get().intValue();
	}

	@Override
	public long longValue() {
		return atomicReference.get().longValue();
	}
	public boolean compareAndSet(double updatedValue) {
		boolean returnFlag = false;
		if (atomicReference.compareAndSet(atomicReference.get(), new Double(updatedValue))) {
			returnFlag = true;
		}
		return returnFlag;
	}
}
