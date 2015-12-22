// 抽象类，继承Exception，是这个程序中所有异常类型的父类

public abstract class AgendaException extends Exception {
	public AgendaException() {
		super();
	}

	/** 显示出错具体信息 */
	public abstract void printInfo();

	public abstract String getInfo();
}
