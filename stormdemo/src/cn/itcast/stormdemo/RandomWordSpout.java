package cn.itcast.stormdemo;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomWordSpout extends BaseRichSpout{

	private SpoutOutputCollector collector;
	
	//ģ��һЩ����
	String[] words = {"iphone","xiaomi","mate","sony","sumsung","moto","meizu"};
	
	//���ϵ�����һ���������tuple��Ϣ
	//�������Ǹ�spout����ĺ����߼�
	@Override
	public void nextTuple() {

		//���Դ�kafka��Ϣ�������õ�����,�����������Ǵ�words�����������ѡһ����Ʒ�����ͳ�ȥ
		Random random = new Random();
		int index = random.nextInt(words.length);
		
		//ͨ��������õ�һ����Ʒ��
		String godName = words[index];
		
		
		//����Ʒ����װ��tuple��������Ϣ����һ�����
		collector.emit(new Values(godName));
		
		//ÿ����һ����Ϣ������500ms
		Utils.sleep(500);
		
		
	}

	//��ʼ����������spout���ʵ����ʱ����һ��
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

		this.collector = collector;
		
		
	}

	//������spout������ͳ�ȥ��tuple�е����ݵ��ֶ���
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

		declarer.declare(new Fields("orignname"));
		
	}

}
