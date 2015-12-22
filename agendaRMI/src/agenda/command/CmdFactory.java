// 命令工厂接口
package agenda.command;

public interface CmdFactory {
	// 只有一个方法成员，功能就是产生命令对象
	public Command createCommand();

}
