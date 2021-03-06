import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;
import java.util.*;
import org.apache.zookeeper.AsyncCallback.*;
import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.AsyncCallback.ChildrenCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.*;
import org.slf4j.*;
public class Client implements Watcher {
	ZooKeeper zk;
	String hostPort;
	Client(String hostPort) 
	{
	 this.hostPort = hostPort;
	}
	void startZK() throws Exception
	{
		zk = new ZooKeeper(hostPort, 15000, this);
	}
	String name="task";
String queueCommand(String command) throws Exception 
{
		while (true) 
		{
			try 
			{
				String name = zk.create("/tasks/task-",
				command.getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);
				return name;
				//break;
			} 
			catch (Exception e) 
			{
				throw new Exception(name + " already appears to be running");
			} 
			
		}
}
	public void process(WatchedEvent e)
	 {
	  	System.out.println(e);
	 }
	public static void main(String args[]) throws Exception
	{
		Client c = new Client(args[0]);
		c.startZK();
		String name = c.queueCommand(args[1]);
		System.out.println("Created " + name);
	}
    
}
