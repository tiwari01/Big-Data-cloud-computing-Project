import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher;
public class Master1 implements org.apache.zookeeper.Watcher {
ZooKeeper zk;
String hostPort;
Master1(String hostPort) {
this.hostPort = hostPort;
}
void startZK() throws Exception{
zk = new ZooKeeper(hostPort, 15000, this);
}
public void process(WatchedEvent e) {
System.out.println(e);
}
public static void main(String args[])
throws Exception {
Master1 m = new Master1(args[0]);
m.startZK();
// wait for a bit
Thread.sleep(60000);
}
}
