// �����࣬�̳�Exception������������������쳣���͵ĸ���

public abstract class AgendaException extends Exception {
	public AgendaException() {
		super();
	}

	/** ��ʾ���������Ϣ */
	public abstract void printInfo();

	public abstract String getInfo();
}
