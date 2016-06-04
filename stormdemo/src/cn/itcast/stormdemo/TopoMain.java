package cn.itcast.stormdemo;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

/**
 * ��֯������������γ�һ�������Ĵ������̣�������ν��topology(������mapreduce�����е�job)
 * ���ҽ���topology�ύ��storm��Ⱥȥ���У�topology�ύ����Ⱥ��ͽ�������ֹ�����У�������Ϊ�����쳣�˳�
 * @author duanhaitao@itcast.cn
 *
 */
public class TopoMain {

	
	public static void main(String[] args) throws Exception {
		
		TopologyBuilder builder = new TopologyBuilder();
		
		//�����ǵ�spout������õ�topology��ȥ 
		//parallelism_hint ��4  ��ʾ��4��excutor��ִ��������
		//setNumTasks(8) ���õ��Ǹ����ִ��ʱ�Ĳ���task������Ҳ����ζ��1��excutor������2��task
		builder.setSpout("randomspout", new RandomWordSpout(), 4).setNumTasks(8);
		
		//����дת��bolt������õ�topology������ָ��������randomspout�������Ϣ
		//.shuffleGrouping("randomspout")�������㺬�壺
		//1��upperbolt������յ�tuple��Ϣһ��������randomspout���
		//2��randomspout�����upperbolt����Ĵ�������taskʵ��֮���շ���Ϣʱ���õķ���������������shuffleGrouping
		builder.setBolt("upperbolt", new UpperBolt(), 4).shuffleGrouping("randomspout");
		
		//����Ӻ�׺��bolt������õ�topology������ָ��������upperbolt�������Ϣ
		builder.setBolt("suffixbolt", new SuffixBolt(), 4).shuffleGrouping("upperbolt");
		
		//��builder������һ��topology
		StormTopology demotop = builder.createTopology();
		
		
		//����һЩtopology�ڼ�Ⱥ������ʱ�Ĳ���
		Config conf = new Config();
		//�������õ�������demotop��ռ�õĲ�λ����Ҳ����worker������
		conf.setNumWorkers(4);
		conf.setDebug(true);
		conf.setNumAckers(0);
		
		
		//�����topology�ύ��storm��Ⱥ����
		StormSubmitter.submitTopology("demotopo", conf, demotop);
		
	}
}
